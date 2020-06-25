package kr.or.ddit.view.usedbookboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserBookBoardLoader extends Application {
	public static Stage pStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		Parent root = FXMLLoader.load(getClass().getResource("userBookBoard.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("중고 거래 게시판");
		primaryStage.setScene(scene);
		primaryStage.show();
		pStage = primaryStage;
		*/
	}

	public static void main(String[] args) {
		launch(args);
	}
}


