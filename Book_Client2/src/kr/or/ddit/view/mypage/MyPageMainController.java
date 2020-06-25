package kr.or.ddit.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class MyPageMainController implements Initializable {
	
	@FXML ImageView point;
	Parent root;
	@FXML AnchorPane imageMain;
	@FXML ImageView deal;
	@FXML ImageView member;
	@FXML ImageView studyRoom;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		point.setOnMouseClicked(e->{
			
			try {
				root = FXMLLoader.load(getClass().getResource("mypointPage.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		});
		
		
		
		deal.setOnMouseClicked(e->{
			
			try {
				root = FXMLLoader.load(getClass().getResource("mydeal.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		});
		
		
		
		member.setOnMouseClicked(e->{
			
			try {
				root = FXMLLoader.load(getClass().getResource("myinfo.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		});
		
		studyRoom.setOnMouseClicked(e->{
			try {
				root = FXMLLoader.load(getClass().getResource("myStudyRoom.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		});
	}
	
	

	@FXML public void shopping() {
		
	}

	@FXML public void member() {
		
	}

	@FXML public void deal() {
		
	}

	@FXML public void recentBook() {
		
	}

	@FXML public void book() {
		
	}

	@FXML public void seat() {
		
	}

	

	@FXML public void comment() {
		
	}

	@FXML public void hat() {
		
	}

	@FXML public void oard() {
		
	}

	@FXML public void chat() {
		
	}

	@FXML public void board() {}
}
