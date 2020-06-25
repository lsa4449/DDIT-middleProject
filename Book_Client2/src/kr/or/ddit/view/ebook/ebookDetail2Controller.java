package kr.or.ddit.view.ebook;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ebookDetail2Controller implements Initializable {
	
		Stage stage;


		   @FXML
		    private ImageView previous_btn;

		    @FXML
		    private ImageView next_btn;

		    @FXML
		    private ImageView write_btn;

		    @FXML
		    private ImageView read_btn;

		    @FXML
		    private Label ta1;

		    @FXML
		    private Label ta2;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image image1 = new Image("file:src/images/write.png");
		write_btn.setImage(image1);
		
		Image image2 = new Image("file:src/images/right.png");
		next_btn.setImage(image2);
		
		Image image3 = new Image("file:src/images/left.png");
		previous_btn.setImage(image3);
		
		Image image4 = new Image("file:src/images/sound.png");
		read_btn.setImage(image4);
		
		
		
		// TODO Auto-generated method stub
		FileInputStream fin = null;  //변수선언
		FileInputStream fin2 = null;
	      InputStreamReader isr = null;
	      InputStreamReader isr2 = null;

		try {
				
			  fin = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook5.txt"); //생성
			  fin2 = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook6.txt");
			  isr = new InputStreamReader(fin, "UTF-8");
			  isr2 = new InputStreamReader(fin2, "UTF-8");

			  
			int c; // 읽어온 데이터를 저장할 변수
			
			
			String aa= "";
			while((c=isr.read()) != -1) {
				// 읽어온 자료 출력하기
				// ta1.appendText((char)c+"");
				// ta1.setText(char(c)+"");
				aa += (char)c; 
				
				System.out.print((char)c);
			}
			ta1.setText(aa); 
			int d;
			String bb="";
			while((d=isr2.read()) != -1) {
				//ta2.appendText((char)d+"");
				bb += (char)d;
			}
			
			ta2.setText(bb);
			isr.close();
			fin.close();
			isr2.close();
			fin2.close();
			
		}catch (FileNotFoundException e) {
			System.out.println("지정한 파일이 없습니다.");
		}catch (IOException e) {
			System.out.println("알수 없는 입출력 오류입니다.");
		}
		
		
		//책쓰기 씬으로 전환
		write_btn.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("ebookWrite.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)write_btn.getScene().getWindow();
			stage.setTitle("2쪽");
			stage.setScene(scene);
		});
		
		previous_btn.setOnMouseClicked(e->{
			
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("ebookDetail1.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)next_btn.getScene().getWindow();
			stage.setTitle("2쪽");
			stage.setScene(scene);
		});
		 
		
		read_btn.setOnMouseClicked(e->{
			
			        String clientId = "y9cwmxc7sd";//애플리케이션 클라이언트 아이디값";
			        String clientSecret = "4cjp28J5EIEqdieUlY2n86oJRPNYcYzXsWEvecI1";//애플리케이션 클라이언트 시크릿값";
			        try {
			            String text = URLEncoder.encode("ebook 서비스 입니다 어떤걸 도와드릴까요", "UTF-8"); // 13자
			            String apiURL = "https://naveropenapi.apigw.ntruss.com/voice-premium/v1/tts";
			            URL url = new URL(apiURL);
			            HttpURLConnection con = (HttpURLConnection)url.openConnection();
			            con.setRequestMethod("POST");
			            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			            // post request
			            String postParams = "speaker=nara&volume=0&speed=0&pitch=0&emotion=0&format=mp3&text=" + text;
			            con.setDoOutput(true);
			            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			            wr.writeBytes(postParams);
			            wr.flush();
			            wr.close();
			            int responseCode = con.getResponseCode();
			            BufferedReader br;
			            if(responseCode==200) { // 정상 호출
			                InputStream is = con.getInputStream();
			                int read = 0;
			                byte[] bytes = new byte[1024];
			                // 랜덤한 이름으로 mp3 파일 생성
			                String tempname = "ebook1";  //Long.valueOf(new Date().getTime()).toString();
			                File f = new File("D:/audio/" + tempname + ".mp3");
			                f.createNewFile();
			                OutputStream outputStream = new FileOutputStream(f);
			                while ((read =is.read(bytes)) != -1) {
			                    outputStream.write(bytes, 0, read);
			                }
			                is.close();
			                
			                System.out.println("실행완료");
			            } else {  // 오류 발생
			                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			                String inputLine;
			                StringBuffer response = new StringBuffer();
			                while ((inputLine = br.readLine()) != null) {
			                    response.append(inputLine);
			                }
			                br.close();
			                System.out.println(response.toString());
			            }
			        } catch (Exception e1) {
			            System.out.println(e1);
			        }
			    
		});
	}
}
