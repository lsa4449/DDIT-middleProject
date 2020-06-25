package kr.or.ddit.view.seat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeatLoad extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("seat.fxml"));
		Scene scene = new Scene(parent);
		
		primaryStage.setTitle("도서관 열람심 예약입니다.");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
