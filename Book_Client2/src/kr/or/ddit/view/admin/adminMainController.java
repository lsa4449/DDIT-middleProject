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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class adminMainController implements Initializable {
	
		
		Stage stage;
		
		 @FXML
		    private ImageView logo;

		    @FXML
		    private ImageView member_img;

		    @FXML
		    private ImageView board_img;

		    @FXML
		    private ImageView book_img;

		 
	  
	    
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//로고 버튼 누르면 메인 페이지로
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
		
		
		//member관리 창으로 변환
		member_img.setOnMouseClicked(e->{
			
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("memManage.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)member_img.getScene().getWindow();
			stage.setTitle("회원 관리");
			stage.setScene(scene);
		});
		
	
		board_img.setOnMouseClicked(e->{
			
			//게사판 관리 창으로 변환
				Parent root = null;
				try {
					root = (Parent)FXMLLoader.load(getClass().getResource("boardManage.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene scene =new Scene(root);
				
				stage = (Stage)board_img.getScene().getWindow();
				stage.setTitle("게시판 관리");
				stage.setScene(scene);
		});
		
		book_img.setOnMouseClicked(e->{
			
			//게사판 관리 창으로 변환
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("bookManage.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)board_img.getScene().getWindow();
			stage.setTitle("도서 관리");
			stage.setScene(scene);
		});
	
	}

}
