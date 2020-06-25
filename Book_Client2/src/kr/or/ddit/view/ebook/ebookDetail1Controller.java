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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ebookDetail1Controller implements Initializable {
	
		Stage stage;
		
		Media media;
		
	    MediaPlayer player;


	    @FXML
	    private ImageView previous_btn;

	    @FXML
	    private ImageView next_btn;

	    @FXML
	    private ImageView read_btn;

	    @FXML
	    private Label ta1;

	    @FXML
	    private Label ta2;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		Image image2 = new Image("file:src/images/right.png");
		next_btn.setImage(image2);
		
		Image image3 = new Image("file:src/images/left.png");
		previous_btn.setImage(image3);
		
		Image image4 = new Image("file:src/images/sound.png");
		read_btn.setImage(image4);
		
		
		 FileInputStream fin = null;  //변수선언
		 FileInputStream fin2 = null;
	      InputStreamReader isr = null;
	      InputStreamReader isr2 = null;

		try {
				
			  fin = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook3.txt"); //생성
			  fin2 = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook4.txt");
			  isr = new InputStreamReader(fin, "UTF-8");
			  isr2 = new InputStreamReader(fin2, "UTF-8");

			  
			int c; // 읽어온 데이터를 저장할 변수
			
			String aa="";
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
		
		next_btn.setOnMouseClicked(e->{
			
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("ebookDetail2.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)next_btn.getScene().getWindow();
			stage.setTitle("2쪽");
			stage.setScene(scene);
		});
		
		previous_btn.setOnMouseClicked(e->{

			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("ebookDetail.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)previous_btn.getScene().getWindow();
			stage.setTitle("1쪽");
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
			        /*
			        
			        //생성된 오디오 파일을 선택해서 올림
			        FileChooser fc = new FileChooser();
			        fc.setTitle("음악 파일 선택");
			        fc.setInitialDirectory(new File("C:/")); // 기본 경로
			        // 확장자 필터링(선택)
			        ExtensionFilter filter = new ExtensionFilter("audio", "*.mp3");
			        fc.getExtensionFilters().add(filter);
			 
			 
			        File selectedFile = fc.showOpenDialog(null); // 창 위치 및 불러운 파일 삽입
			        mediaPath = selectedFile.toURI().toString();
			        System.out.println("선택한 경로 : " + mediaPath);
			 
			        // playList에 재생할 파일명 출력
			        String[] token = mediaPath.split("/");
			        // 공백의 경우 %20으로 표시되어 공백으로 바꿔주고 넣는다.
			        String musicName = token[token.length-1].replaceAll("%20",  " ");
			        // 리스트에 삽입
			        playList.setText(musicName);
			 
			        // 미디어 등록 - 플레이어 등록 - 뷰에 등록
			        media = new Media(mediaPath);
			        player = new MediaPlayer(media);
			        player.setAutoPlay(false); // 자동실행 false, play 버튼을 누를 시 음악이 재생되게끔
			        mediaView = new MediaView(player);


			*/
			    
		});
	}
}
