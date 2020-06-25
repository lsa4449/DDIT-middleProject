package kr.or.ddit.view.book;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BookVO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class BookUpdateController implements Initializable {

	private IBookService service;
	private ObservableList<BookVO> data;
	private Registry reg;
	Parent parent = null;

	@FXML
	TextField title;
	@FXML
	TextField author;
	@FXML
	TextField publisher;
	@FXML
	TextField translator;
	@FXML
	TextField price;
	@FXML
	Button updateBtn;
	@FXML
	Button xBtn;
	@FXML
	TextField state;
	@FXML
	TextField category;
	@FXML
	TextField isbn;
	@FXML
	Button deleteBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Session.loginUser.setAuthority("00");
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IBookService) reg.lookup("bookService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		title.setText(DataFlow.book.getBookName());
		author.setText(DataFlow.book.getAuthor());
		publisher.setText(DataFlow.book.getPublisher());
		translator.setText(DataFlow.book.getTranslator());
		price.setText(DataFlow.book.getPrice());
		category.setText(DataFlow.book.getCategoryNum() + "");
		isbn.setText(DataFlow.book.getIsbn());

		// authority가 관리자(0)이 아닐 때 버튼 사용 금지
		if (!Session.loginUser.getAuthority().equals("0")) {
			updateBtn.setVisible(false);
			deleteBtn.setVisible(false);
		}
		
		

		// 수정
		updateBtn.setOnAction(e -> {

			if (title.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
					|| isbn.getText().isEmpty() || translator.getText().isEmpty() || price.getText().isEmpty()
					|| category.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			try {
				parent = FXMLLoader.load(getClass().getResource("selectBook.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			BookVO b = new BookVO();

			b.setBookNum(DataFlow.book.getBookNum());
			b.setBookName(title.getText());
			b.setAuthor(author.getText());
			b.setPublisher(publisher.getText());
			b.setIsbn(isbn.getText());
			b.setTranslator(translator.getText());
			b.setPrice(price.getText());
			b.setCategoryNum(Integer.parseInt(category.getText()));

			data = FXCollections.observableArrayList(b);

			int cnt = 0;

			try {
				cnt = service.updateBook(b);
			} catch (RemoteException e1) {
					
				e1.printStackTrace();
			}
			
			if(cnt==1)
				
			infoMsg("작업결과 >>", "정보 수정 완료!");
			else {
				System.out.println("실패.. 컨디션 난조");
			}


			Stage stage = (Stage) updateBtn.getScene().getWindow();
			stage.close();

			try {
				// 부모창 정보 불러오기
				Parent root = FXMLLoader.load(getClass().getResource("selectBook.fxml")); 

				// 씬에 레이아웃 추가
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}
		});

		// 삭제
		deleteBtn.setOnAction(e -> {

			int cnt = 0;

			try {
				cnt = service.deleteBook(DataFlow.book.getBookNum());
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			infoMsg("작업결과 >>", "정보 삭제 완료!");

			Stage stage = (Stage) deleteBtn.getScene().getWindow();
			stage.close();
			
			try {
				// 부모창 정보 불러오기
				Parent root = FXMLLoader.load(getClass().getResource("selectBook.fxml")); 

				// 씬에 레이아웃 추가
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}

		});
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}
}
