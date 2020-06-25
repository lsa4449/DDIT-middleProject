package kr.or.ddit.view.study;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class studymain extends Application{
	public static Stage t;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("selStudy.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("도서관리");
		primaryStage.setScene(scene);
		primaryStage.show();
		t = primaryStage;
		
	}
	public static void main(String[] args) {
		launch();
	}
}
