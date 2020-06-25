package kr.or.ddit.view.mypage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class kakaopayController implements Initializable {

	@FXML
	WebView kakaoPay;
	
	private JSObject javascriptConnector;

	/** for communication from the Javascript engine. */
	private JavaConnector javaConnector = new JavaConnector();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		final WebEngine webEngine = kakaoPay.getEngine();
//		Scene scene = new Scene(kakaoPay, 520, 470);
//		Stage stage = (Stage) kakaoPay.getScene().getWindow();
//
//		stage = new Stage();
//		stage.setScene(scene);
//		stage.show();
		// System.out.println(DataFlow.p.getNowPoint());
		webEngine.load("http://localhost:8181/Book_Client/kakaopay.jsp?nowPoint=" + DataFlow.p.getNowPoint());

	}

	public class JavaConnector {
		/**
		 * called when the JS side wants a String to be converted.
		 *
		 * @param value the String to convert
		 */
		public void toLowerCase(String value) {
			if (null != value) {
				javascriptConnector.call("showResult", value.toLowerCase());
			}
		}

		public void sendAddr(String nowPoint) {
			System.out.println("자바스크립트에서 보내온 메시지 출력 : " + nowPoint + "P 등록 완료!");
			// pointText.setText(nowPoint);

		}

	}
}
