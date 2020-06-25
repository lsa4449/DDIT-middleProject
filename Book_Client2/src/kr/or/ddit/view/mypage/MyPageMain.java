package kr.or.ddit.view.mypage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;



public class MyPageMain extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("myPageMain.fxml"));
	      Scene scene = new Scene(root);
	      primaryStage.setTitle("메인 화면");
	      primaryStage.setScene(scene);
	      primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
