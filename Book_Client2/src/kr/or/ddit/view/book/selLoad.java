package kr.or.ddit.view.book;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.session.Session;

public class selLoad extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Session.loginUser.setMemNum(1);
		Parent root = FXMLLoader.load(getClass().getResource("selectBook.fxml"));
		
		Scene scene = new Scene(root);

		primaryStage.setTitle("도서관리");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
