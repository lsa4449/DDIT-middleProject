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

public class boardManageController implements Initializable {
	
	
	Stage stage;
	
	@FXML
    private ImageView logo;

	    @FXML
	    private ImageView notice_btn;

	    @FXML
	    private ImageView question_btn;

	    @FXML
	    private ImageView team_btn;

	    @FXML
	    private ImageView usedbook_btn;
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
		
		//공지사항 이미지 클릭하면 공지사항 창으로 씬 바뀜
		notice_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/notice/noticeMain.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene = new Scene(root);
			
			stage = (Stage)notice_btn.getScene().getWindow();
			stage.setTitle("공지사항");
			stage.setScene(scene);
		});
		
		//문의사항 이미지 클릭하면 문의사항ㄱ ㅔ시판
		question_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/questions/selQuestion.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene = new Scene(root);
			
			stage = (Stage)notice_btn.getScene().getWindow();
			stage.setTitle("문의사항 게시판");
			stage.setScene(scene);
		});
		
		// 커뮤니티 게시판
		team_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/coummunity/selCom.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene = new Scene(root);
			
			stage = (Stage)notice_btn.getScene().getWindow();
			stage.setTitle("커뮤니티 게시판");
			stage.setScene(scene);
		});
		
		// 중고 서적 게시판
		usedbook_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene = new Scene(root);
			
			stage = (Stage)notice_btn.getScene().getWindow();
			stage.setTitle("중고서적 게시판");
			stage.setScene(scene);
		});
	}
}
