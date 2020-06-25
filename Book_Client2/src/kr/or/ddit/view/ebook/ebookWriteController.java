package kr.or.ddit.view.ebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ebookWriteController implements Initializable{
	Stage stage;

    @FXML
    private Button write_btn;

    @FXML
    private Label ta1;

    @FXML
    private Label ta2;
    
    String responeBody = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		write_btn.setOnMouseClicked(e->{
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("글쓰기");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("ebookWriteStart.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.showAndWait();
			
			
			//-------------------------------------------
			//생성한 오디오파일 읽기
			  String clientId = "y9cwmxc7sd";             // Application Client ID";
		        String clientSecret = "4cjp28J5EIEqdieUlY2n86oJRPNYcYzXsWEvecI1";     // Application Client Secret";

		        try {
		            String imgFile = "D:/audio/write.wav";
		            File voiceFile = new File(imgFile);

		            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
		            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
		            URL url = new URL(apiURL);

		            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		            conn.setUseCaches(false);
		            conn.setDoOutput(true);
		            conn.setDoInput(true);
		            conn.setRequestProperty("Content-Type", "application/octet-stream");
		            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
		            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

		            OutputStream outputStream = conn.getOutputStream();
		            FileInputStream inputStream = new FileInputStream(voiceFile);
		            byte[] buffer = new byte[4096];
		            int bytesRead = -1;
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outputStream.write(buffer, 0, bytesRead);
		            }
		            outputStream.flush();
		            inputStream.close();
		            BufferedReader br = null;
		            int responseCode = conn.getResponseCode();
		            if(responseCode == 200) { // 정상 호출
		                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            } else {  // 오류 발생
		                System.out.println("error!!!!!!! responseCode= " + responseCode);
		                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            }
		            String inputLine;

		            if(br != null) {
		                StringBuffer response = new StringBuffer();
		              //  responeBody = new String(response);
		              // JSONParser parser = new JSONParser();
		                
		               // JSONObject jsonFile = (JSONObject) parser.parse(responeBody);
		               // JSONObject return_object = (JSONObject) jsonFile.get("return_object");
		              //  System.out.println(return_object.get("text"));
		                
		                while ((inputLine = br.readLine()) != null) {
		                    response.append(inputLine);
		                }
		                br.close();
		                System.out.println(response.toString());
		                
		                ta1.setText(response.toString());
		            } else {
		                System.out.println("error !!!");
		            }
		        } catch (Exception e2) {
		            System.out.println(e2);
		        }
			
		});
	}
}
