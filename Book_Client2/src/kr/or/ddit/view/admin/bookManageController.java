package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class bookManageController implements Initializable {
	Stage stage;
	

	  @FXML
	    private ImageView logo;

	    @FXML
	    private ImageView bookAll_btn;

	    @FXML
	    private ImageView bookStatus_btn;

	    @FXML
	    private ImageView bookRcommend_btn;

	    @FXML
	    private ImageView bookWant_btn;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logo.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("adminMain.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)logo.getScene().getWindow();
			stage.setTitle("회원 관리");
			stage.setScene(scene);
		});
		
		bookAll_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("bookList.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)bookAll_btn.getScene().getWindow();
			stage.setTitle("전체 도서 관리");
			stage.setScene(scene);
		});
		
		// 에러 아마도 bookStatus.fxml 이 로드가 안되서/
		bookStatus_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("bookStatus.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)bookStatus_btn.getScene().getWindow();
			stage.setTitle("현황 보기");
			stage.setScene(scene);
		});
	}
}
