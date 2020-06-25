package kr.or.ddit.view.book;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.vo.BookVO;

public class BookInsertController implements Initializable {

	private ObservableList<BookVO> data;
	private Registry reg;
	private IBookService service;

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
	TextField state;
	@FXML
	TextField category;
	@FXML
	TextField isbn;
	@FXML
	Button regBtn;

	// String num = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
//		for (int i = 0; i < 13; i++) {
//			int random = (int) (Math.random() * 10);
//			num += random;
//		}
		
		 //rmi lookup
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IBookService) reg.lookup("bookService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		regBtn.setOnAction(e -> {
			if (title.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
					|| isbn.getText().isEmpty() || translator.getText().isEmpty() || price.getText().isEmpty() 
					|| category.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			BookVO b = new BookVO();
			
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
				cnt = service.insertBook(b);
				
				if(cnt == 1) {
					System.out.println("성공");
				}
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
			infoMsg("작업결과 >>", "정보 추가 완료!");
			
			Stage stage = (Stage) regBtn.getScene().getWindow();
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
