package kr.or.ddit.view.community;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class selectallmain extends Application{
	public static Stage s;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("selCom.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("커뮤니티게시판");
		primaryStage.setScene(scene);
		primaryStage.show();
		s = primaryStage;
	}
	public static void main(String[] args) {
		launch();
	}
}
