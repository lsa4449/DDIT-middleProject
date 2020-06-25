package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.view.book.DataFlow;
import kr.or.ddit.vo.BookVO;
import com.jfoenix.controls.JFXButton;

public class BookSelectController implements Initializable {

	private ObservableList<BookVO> tv, currentPageData;
	private Registry reg;
	private int from, to, itemsForPage;
	IBookService service;
	List<BookVO> list = null;
	List<BookVO> search = null;
	Stage stage;

	@FXML
	TableView table;
	@FXML
	TableColumn no;
	@FXML
	TableColumn tTitle;
	@FXML
	TableColumn tAuthority;
	@FXML
	TableColumn tpublisher;
	@FXML
	TextField seachText;
	@FXML
	ImageView searchBtn;
	@FXML
	Button tRegBtn;
	@FXML
	Pagination pn; // 페이징버튼
	@FXML 
	JFXButton backBtn;  //뒤로가기버튼
	
	
	
	/* 메뉴 버튼들 */
	@FXML
	private ImageView logo;// 로고

	@FXML
	private MenuBar mButton1; // 도서관 이용 버튼

	@FXML
	private MenuBar mButton2; // 도서관 소개 버튼

	@FXML
	private MenuBar mButton3; // 게시판 버튼

	@FXML
	private MenuBar mButton4; // 신청 / 참여 버튼

	@FXML
	private MenuBar mButton5; // 독서 문화 활동 버튼

	@FXML
	private MenuItem wannaBook; // 희망도서 신청

	@FXML
	private MenuItem reserBook; // 도서대여

	@FXML
	private MenuItem seat; // 좌석

	@FXML
	private MenuItem studyRoom; // 스터디룸

	@FXML
	private MenuItem monthBook; // 이달의 도서

	@FXML
	private MenuItem chat; // 채팅

	@FXML
	private MenuItem usedBook; // 중고서적구매

	@FXML
	private MenuItem buy; // 삽니다

	@FXML
	private MenuItem sell; // 팝니다

	@FXML
	private MenuItem searchBook; // 도서 검색

	@FXML
	private MenuItem notice; // 공지사항

	@FXML
	private MenuItem QnA; // 문의사항

	@FXML
	private MenuItem community; // 커뮤니티
	
	
	
	
	public void dialog(String path, String title) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		// 2.모달창 여부 설정
		// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
		dialog.initModality(Modality.APPLICATION_MODAL);

		dialog.setTitle(title);

		// 4. 자식창에 나타날 컨테이너객체 생성
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 5. Scene 객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene 객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정
		dialog.show();

	}
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		no.setCellValueFactory(new PropertyValueFactory<>("bookNum"));
		tTitle.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		tAuthority.setCellValueFactory(new PropertyValueFactory<>("author"));
		tpublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		tv = FXCollections.observableArrayList();
		no.setStyle("-fx-alignment: center;");
		tTitle.setStyle("-fx-alignment: center;");
		tAuthority.setStyle("-fx-alignment: center;");
		tpublisher.setStyle("-fx-alignment: center;");

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

		
		// 등록
		tRegBtn.setOnAction(e -> {
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("insertBook.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();

			Stage stage = (Stage) table.getScene().getWindow();
			stage.close();
		});

		// 수정
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Stage dialog = new Stage(StageStyle.UTILITY);

				BookVO selectBook = (BookVO) table.getSelectionModel().getSelectedItem();
				DataFlow2.book = selectBook;

				
				System.out.println(DataFlow2.book.getBookNum());

				// 2.모달창 여부 설정
				// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
				dialog.initModality(Modality.APPLICATION_MODAL);

				// 3. 부모창 지정
				dialog.initOwner(table.getScene().getWindow());
				dialog.setTitle("추가");

				// 4. 자식창에 나타날 컨테이너객체 생성
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("updateBook.fxml"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				// 5. Scene 객체 생성해서 컨테이너 객체 추가
				Scene scene = new Scene(parent);

				// 6. Stage객체에 Scene 객체 추가
				dialog.setScene(scene);
				dialog.setResizable(false); // 크기 고정
				dialog.show();

				
			}
		});

		// 검색
		searchBtn.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event event) {

				BookVO searchBook = new BookVO();
				searchBook.setBookName(seachText.getText());

				List<BookVO> searchBook2 = new ArrayList<BookVO>();

				try {
					searchBook2 = (List<BookVO>) service.selectSearchBook(searchBook);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				tv.clear();
				tv = FXCollections.observableArrayList(searchBook2);

				table.setItems(tv);

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
		
		
		
		//뒤로가기버튼
				backBtn.setOnAction(e ->{
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/bookManage.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Scene scene = new Scene(root);
					Stage stage = (Stage) logo.getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				});
				
				
				
				
				
				/* 메뉴바 이동 경로 */
				logo.setOnMouseClicked(e -> {

					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/adminMain.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Scene scene = new Scene(root);
					Stage stage = (Stage) logo.getScene().getWindow();
					stage.setScene(scene);
					stage.show();

				});
				
				
				notice.setOnAction(e -> {
					dialog("/kr/or/ddit/view/notice/noticeMain.fxml", "notice");
				});

				studyRoom.setOnAction(e -> {
					// ㅠㅠ
				});

				QnA.setOnAction(e -> {
					dialog("/kr/or/ddit/view/questions/selQuestion.fxml", "QnA");
				});

				community.setOnAction(e -> {
					dialog("/kr/or/ddit/view/community/selCom.fxml", "Community");
				});

				wannaBook.setOnAction(e -> {
					dialog("/kr/or/ddit/view/wannabook/Wannabook.fxml", "WannaBook");
				});

				reserBook.setOnAction(e -> {
					dialog("/kr/or/ddit/view/book/reserBook.fxml", "reserveBook");
				});

				searchBook.setOnAction(e -> {
					dialog("/kr/or/ddit/view/book/selectBook.fxml", "selectBook");
				});

				studyRoom.setOnAction(e -> {
					dialog("/kr/or/ddit/view/studyroom/studyRoom.fxml", "StudyRoom");
				});
				usedBook.setOnAction(e -> {
					dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
				});

				buy.setOnAction(e -> {
					dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
				});

				monthBook.setOnAction(e -> {
					dialog("/kr/or/ddit/view/monthBook/SelectMonthBook.fxml", "monthBook");
				});
				seat.setOnAction(e -> {
					// ㅜㅠ
				});

	}

}
