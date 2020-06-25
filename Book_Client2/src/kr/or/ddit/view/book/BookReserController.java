package kr.or.ddit.view.book;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BookRentalVO;
import kr.or.ddit.vo.BookVO;

public class BookReserController implements Initializable {

	@FXML
	ImageView search;
	@FXML
	ImageView searchBtn;
	@FXML
	TableView table;
	@FXML
	TableColumn no;
	@FXML
	TableColumn title;
	@FXML
	TableColumn authority;
	@FXML
	TableColumn publisher;
	@FXML
	TableColumn<BookVO, ImageView> reserState = new TableColumn<BookVO, ImageView>();
	@FXML
	TextField seachText;
	@FXML
	Button reserBtn;
	@FXML
	Pagination pn;
	@FXML
	JFXButton login_btn;
	@FXML
	JFXButton signup_btn;

	private ObservableList<BookVO> tv, currentPageData;
	private int from, to, itemsForPage;
	private Registry reg;
	IBookService service;
	List<BookVO> list = null;

	// TableView<CustomImage> tableview = new TableView<CustomImage>();
	// ObservableList<CustomImage> imgList = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Session.loginUser.setAuthority("00");

		no.setCellValueFactory(new PropertyValueFactory<>("bookNum"));
		title.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		authority.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		reserState.setCellValueFactory(new PropertyValueFactory<BookVO, ImageView>("bookState"));
		// reserState.setCellValueFactory(new PropertyValueFactory<CustomImage,
		// BookVO>("bookState"));

		tv = FXCollections.observableArrayList();
		no.setStyle("-fx-alignment: center;");
		title.setStyle("-fx-alignment: center;");
		authority.setStyle("-fx-alignment: center;");
		publisher.setStyle("-fx-alignment: center;");
		reserState.setStyle("-fx-alignment: center;");

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IBookService) reg.lookup("bookService");
		} catch (Exception e) {
			System.out.println("lookup에러");
			e.printStackTrace();
		}

		try {
			list = service.bookList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (BookVO vo : list) {
			tv.add(vo);
		}

		table.setItems(tv);

		// ImageView icon = new ImageView();

//		imgList.addAll(img1,img2);
//		table.getColumns().add(reserState);
//		table.setItems(imgList);

		if (reserState.getText().equals("o")) {

			// icon.setImage(img);
			// Image img = new Image("file:src/images/check.png");
			reserState.setGraphic(new ImageView(new Image(("file:src/images/check.png"))));

		}

		// 예약
		reserBtn.setOnAction(e -> {
			if (!Session.loginUser.getAuthority().equals("1")) {
				err("Login", "Login Error", "로그인이 필요한 서비스 입니다.");
			} else {
				BookVO selectBook = (BookVO) table.getSelectionModel().getSelectedItem();
				DataFlow.book = selectBook;
				BookRentalVO rentalvo = new BookRentalVO();
				rentalvo.setBookNum(selectBook.getBookNum()); 
				rentalvo.setState(selectBook.getBookState());
				rentalvo.setMemNum(Session.loginUser.getMemNum());
				DataFlow.bookRentalVO = rentalvo;
				Stage stage = (Stage) reserBtn.getScene().getWindow();
				stage.close();

				if (selectBook.getBookState().equals("x")) {

					Stage dialog = new Stage(StageStyle.UTILITY);
					// System.out.println(DataFlow.book.getBookNum());

					// 2.모달창 여부 설정
					// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
					dialog.initModality(Modality.APPLICATION_MODAL);

					// 3. 부모창 지정
					dialog.initOwner(table.getScene().getWindow());
					dialog.setTitle("추가");

					// 4. 자식창에 나타날 컨테이너객체 생성
					Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("reserCheckBook.fxml"));
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					// 5. Scene 객체 생성해서 컨테이너 객체 추가
					Scene scene = new Scene(parent);

					// 6. Stage객체에 Scene 객체 추가
					dialog.setScene(scene);
					dialog.setResizable(false); // 크기 고정
					dialog.show();

				} else {
					Alert infoAlert = new Alert(AlertType.INFORMATION);
					infoAlert.setTitle("대여");
					infoAlert.setHeaderText("이미 대여되어 있는 도서 입니다.");
					infoAlert.setContentText("다른 도서를 선택하여 주세요.");
					infoAlert.showAndWait();

					try {
						// 부모창 정보 불러오기
						Parent root = FXMLLoader.load(getClass().getResource("reserBook.fxml"));

						// 씬에 레이아웃 추가
						Scene sc = new Scene(root);
						stage.setScene(sc);
						stage.show();

					} catch (Exception e1) {
						System.out.println("오류내용" + e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});

		// 페이징버튼
		itemsForPage = 10; // 한페이에 보여줄 항목 수 설정
		int totalDataCnt = tv.size();
		int totalPageCnt = totalDataCnt % itemsForPage == 0 ? totalDataCnt / itemsForPage
				: totalDataCnt / itemsForPage + 1;

		pn.setPageCount(totalPageCnt); // 전체 페이지수 설정

		// 방법1 Callback타입의 익명객체 생성
		pn.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				table.setItems(getTableViewData(from, to));

				return table;
			}

			private ObservableList<BookVO> getTableViewData(int from, int to) {
				currentPageData = FXCollections.observableArrayList();
				int totSize = tv.size();
				for (int i = from; i <= to && i < totSize; i++) {
					currentPageData.add(tv.get(i));
				}

				return currentPageData;
			}
		});
		//
		

	}

	public void err(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	

	/*
	 * public class CustomImage {
	 * 
	 * private ImageView image;
	 * 
	 * CustomImage(ImageView img) { this.image = img; }
	 * 
	 * public void setImage(ImageView value) { image = value; }
	 * 
	 * public ImageView getImage() { return image; } }
	 */

}
