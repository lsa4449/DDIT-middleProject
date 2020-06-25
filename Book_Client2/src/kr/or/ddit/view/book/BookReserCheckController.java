package kr.or.ddit.view.book;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import kr.or.ddit.service.book.IBookService;
import kr.or.ddit.service.bookRental.IBookRentalService;
import kr.or.ddit.vo.BookVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BookReserCheckController implements Initializable {

	private IBookService service;
	private IBookRentalService rService;
	private Registry reg;
	private ObservableList<BookVO> data;
	Parent parent = null;

	@FXML
	Button reserBtn;
	@FXML
	Button cancelBtn;
	@FXML
	JFXTextArea title;
	@FXML
	JFXTextArea author;
	@FXML
	JFXTextArea publisher;
	@FXML
	JFXTextArea isbn;
	@FXML
	JFXTextArea translator;
	@FXML
	JFXTextArea price;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		title.setStyle("-fx-alignment: center;");
		author.setStyle("-fx-alignment: center;");
		publisher.setStyle("-fx-alignment: center;");
		isbn.setStyle("-fx-alignment: center;");
		translator.setStyle("-fx-alignment: center;");
		price.setStyle("-fx-alignment: center;");

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IBookService) reg.lookup("bookService");
			rService = (IBookRentalService) reg.lookup("bookRentalService");
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
		isbn.setText(DataFlow.book.getIsbn());

		// 등록
		reserBtn.setOnAction(e -> {

			int cnt = 0;
			int cnt2 = 0;

			try {
				cnt = service.afterReserBook(DataFlow.book.getBookNum());
				cnt2 = rService.insertBookRental(DataFlow.bookRentalVO);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(cnt == 1) {
				infoMsg("작업결과 >>", "도서 예약 완료!");
			} else {
				System.out.println("실패.. 컨디션 난조");
			}

			title.clear();
			author.clear();
			publisher.clear();
			isbn.clear();
			translator.clear();
			price.clear();

			Stage stage = (Stage) reserBtn.getScene().getWindow();
			stage.close();
			
//			Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
//			stage.close();
			
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

		});

		cancelBtn.setOnAction(e -> {

			Stage stage = (Stage) cancelBtn.getScene().getWindow();
			stage.close();
			
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

		});

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}

}
