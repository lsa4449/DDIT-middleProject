package kr.or.ddit.view.questions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class selectmain extends Application{
public static Stage e;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("selQuestion.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("문의사항");
		primaryStage.setScene(scene);
		primaryStage.show();
		e = primaryStage;
	}
	public static void main(String[] args) {
		launch();
	}
}
