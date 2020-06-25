package kr.or.ddit.view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

	public static Scene mainScene;
	public static Stage mainStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		 * Parent root = (Parent)loader.load();
		 */

		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

		mainScene = new Scene(root);

		mainScene.getStylesheets().add(getClass().getResource("menubar.css").toExternalForm());

		mainStage = primaryStage;
		mainStage.setTitle("메인화면");
		mainStage.setScene(mainScene);

		mainStage.show();

		System.out.println("application실행");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
