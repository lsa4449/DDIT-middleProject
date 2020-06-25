package kr.or.ddit.view.javaFxChatBot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatBotController implements Initializable {

	@FXML
	private JFXTextField textUserr;

	@FXML
	private JFXButton btnSend;

	@FXML
	private JFXButton btnClose;

	@FXML
	private VBox vbox1;

	@FXML
	private VBox vbox2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
		String accessKey = "92213794-8ece-4db2-b6e1-10d47bcfb212"; // 발급받은 API Key
		String domain = "ChatBot"; // 도메인 명
		String access_method = "internal_data"; // 도메인 방식 // internal_data 가 머지?
		String method = "open_dialog"; // method 호출 방식
		Gson gson = new Gson();

		Map<String, Object> request = new HashMap<>();
		Map<String, String> argument = new HashMap<>();

////////////////////////// OPEN DIALOG //////////////////////////

		argument.put("name", domain);
		argument.put("access_method", access_method);
		argument.put("method", method);

		request.put("access_key", accessKey);
		request.put("argument", argument);

		URL url;
		Integer responseCode = null;
		String responBody = null;
		try {
			url = new URL(openApiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(gson.toJson(request).getBytes("UTF-8"));
			wr.flush(); // 쓰레기 뿌리기
			wr.close();

			responseCode = con.getResponseCode();
			InputStream is = con.getInputStream();
			byte[] buffer = new byte[is.available()];
			int byteRead = is.read(buffer);
			responBody = new String(buffer);

			System.out.println("[responseCode] " + responseCode);
			System.out.println("[responBody]");
			System.out.println(responBody);

			//
			JSONParser parser = new JSONParser();

			try {

				JSONObject jsonFile = (JSONObject) parser.parse(responBody);
				System.out.println("result : " + jsonFile.get("result"));

				JSONObject return_object = (JSONObject) jsonFile.get("return_object");

				System.out.println("method : " + return_object.get("method"));
				System.out.println("uuid : " + return_object.get("uuid"));

				JSONObject result = (JSONObject) return_object.get("result");
				System.out.println("system_text : " + result.get("system_text"));
				System.out.println("state : " + result.get("state"));
				System.out.println("message : " + result.get("message"));

				System.out.println();
				System.out.println("———————————————————————");
				System.out.println();

				String text = (String) result.get("system_text");
				Label label = new Label(text);
				VBox vbox_1_1 = new VBox();
				HBox hbox_1_1 = new HBox();
				HBox hbox_1_2 = new HBox();

				Image image1 = new Image("file:src/images/robot.png");
				ImageView imageView1 = new ImageView(image1);
				hbox_1_1.getChildren().add(imageView1);
				hbox_1_2.getChildren().add(label);
				vbox_1_1.getChildren().addAll(hbox_1_1, hbox_1_2);
				vbox1.getChildren().add(vbox_1_1);

				// 메시지 보내기
				btnSend.setOnAction(e -> {

					String result_str = chat(return_object.get("uuid").toString(), textUserr.getText());

					try {
						Label label2 = new Label(textUserr.getText());
						VBox vbox_2_1 = new VBox();
						HBox hbox_2_1 = new HBox();
						HBox hbox_2_2 = new HBox();
						Image image2 = new Image("file:src/images/person.png");
						ImageView imageView2 = new ImageView(image2);
						hbox_2_1.getChildren().add(label2);
						hbox_2_1.setStyle("-fx-alignment: center-right");
						hbox_2_2.getChildren().add(imageView2);
						hbox_2_2.setStyle("-fx-alignment: center-right");
						vbox_2_1.getChildren().addAll(hbox_2_2, hbox_2_1);
						vbox_2_1.setStyle("-fx-alignment: center-right");
						textUserr.clear();
						vbox1.getChildren().add(vbox_2_1);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					System.out.println(return_object.get("uuid").toString());
					System.out.println("최종 결과 : " + result_str.toString());
					try {
						JSONObject responseObject = (JSONObject) parser.parse(result_str);

//						// return_object 라는 키 가져온다.
						JSONObject returnObject = (JSONObject) responseObject.get("return_object");
						System.out.println(returnObject);

						JSONObject resultObject = (JSONObject) returnObject.get("result");
						System.out.println(resultObject);

						System.out.println(resultObject.get("system_text"));

						Label label3 = new Label((String) resultObject.get("system_text"));
						VBox vbox_3_1 = new VBox();
						HBox hbox_3_1 = new HBox();
						HBox hbox_3_2 = new HBox();

						Image image3 = new Image("file:src/images/robot.png");
						ImageView imageView3 = new ImageView(image3);

						hbox_3_1.getChildren().add(imageView3);
						hbox_3_2.getChildren().add(label3);
						vbox_3_1.getChildren().addAll(hbox_3_1, hbox_3_2);

						vbox1.getChildren().addAll(vbox_3_1);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					btnClose.setOnAction(e1 -> {

						System.out.println("종료"); // 여기서 종료
						close_chat(return_object.get("uuid").toString());
						Stage stage = new Stage();
						stage = (Stage) btnClose.getScene().getWindow();
						stage.close();
						

					}); // btnClose

				});// btnSend

//                System.out.println("종료");
//                close_chat(return_object.get("uuid").toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// main

	private String chat(String uuid, String text) {
		String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
		String accessKey = "92213794-8ece-4db2-b6e1-10d47bcfb212"; // 발급받은 API Key
		String method = "dialog"; // method 호출 방식
		Gson gson = new Gson();

		Map<String, Object> request = new HashMap<>();
		Map<String, String> argument = new HashMap<>();

		////////////////////////// OPEN DIALOG //////////////////////////

		argument.put("uuid", uuid);
		argument.put("method", method);
		argument.put("text", text);

		request.put("access_key", accessKey);
		request.put("argument", argument);

		URL url;
		Integer responseCode = null;
		String responBody = null;
		try {
			url = new URL(openApiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(gson.toJson(request).getBytes("UTF-8"));
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			InputStream is = con.getInputStream();
			byte[] buffer = new byte[is.available()];
			int byteRead = is.read(buffer);
			responBody = new String(buffer);

			System.out.println("여기서 dialog");
			System.out.println("[responseCode] " + responseCode);
			System.out.println("[responBody]");
			System.out.println(responBody);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responBody;
	}

//////////////close 해줘야 한다.
	private String close_chat(String uuid) {
		String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
		String accessKey = "92213794-8ece-4db2-b6e1-10d47bcfb212"; // 발급받은 API Key
		String method = "close_dialog"; // method 호출 방식
		Gson gson = new Gson();

		Map<String, Object> request = new HashMap<>();
		Map<String, String> argument = new HashMap<>();

////////////////////////// OPEN DIALOG //////////////////////////

		argument.put("uuid", uuid);
		argument.put("method", method);

		request.put("access_key", accessKey);
		request.put("argument", argument);

		URL url;
		Integer responseCode = null;
		String responBody = null;
		try {
			url = new URL(openApiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(gson.toJson(request).getBytes("UTF-8"));
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			InputStream is = con.getInputStream();
			byte[] buffer = new byte[is.available()];
			int byteRead = is.read(buffer);
			responBody = new String(buffer);

			System.out.println("[responseCode] " + responseCode);
			System.out.println("[responBody]");
			System.out.println(responBody);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responBody;
	}
	
    @FXML
    void btnSendMethod(MouseEvent event) {

    }

}
