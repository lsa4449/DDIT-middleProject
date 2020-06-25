package kr.or.ddit.view.wannabook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WannabookLoader extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("wannaBook.fxml"));
	      Scene scene = new Scene(root);
	      primaryStage.setTitle("희망도서");
	      primaryStage.setScene(scene);
	      primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
