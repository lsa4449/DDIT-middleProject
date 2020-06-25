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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.vo.EBookVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ebookDetailController implements Initializable {
	
	Stage stage;


    @FXML
    private Button read_btn;

//    @FXML
//    private TextArea ta1;
//
//    @FXML
//    private TextArea ta2;

    @FXML
    private Button next_btn;

    @FXML
    private MediaView mediaView;
    
    //Media media;
    
   // MediaPlayer player;

    String tempname;

    String mediaPath;
    
   // long totalTime;
    
   // long currentTime;



	EBookVO ebook = DataEBook.eBook;


	@FXML 
	private ImageView sound;


	@FXML 
	private ImageView next;
	
	@FXML
	private Label ta1;
	
	@FXML
	private Label ta2;


	/*
	 * public String getTextFromFile(File txtFile){
	 * 
	 * 
	 * String text;
	 * 
	 * BufferedReader br = null; try{ br = new BufferedReader(new
	 * InputStreamReader(new FileInputStream(txtFile))); String line;
	 * 
	 * while((line = br.readLine()) != null){ text.appendText(line + "\n"); } }
	 * catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
	 * e) { e.printStackTrace(); } }
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image image1 = new Image("file:src/images/sound.png");
		sound.setImage(image1);

		Image image2 = new Image("file:src/images/right.png");
		next.setImage(image2);

		/*

		 try {
	           Scanner s = new Scanner(new File("d:/"+ ebook.geteBookText()+".txt")).useDelimiter("\\s+");
	           while (s.hasNext()) {
	               if (s.hasNextInt()) { // check if next token is an int
	            	   
						
						// tf1.appendText(s.nextInt() + " "); // display the found integer
						 
	            	   String line = s.nextLine();
	                   tf1.appendText(line);
	                   ta1.appendText(line);
	            	   
	                   if(line.length()==0) {
	                	   break;
	                   }
	               } else {
	                  tf1.appendText(s.next() + " "); // else read the next token
	                  ta1.appendText(s.next() + " ");
	                  String line = s.nextLine();
	                   if(line.length()==0) {
	                	   break;
	                   }
	               }
	           }
	       } catch (FileNotFoundException ex) {
	           System.err.println(ex);
	       }
	*/
		FileInputStream fin = null;  //변수선언
		FileInputStream fin2 = null;
	      InputStreamReader isr = null;
	      InputStreamReader isr2 = null;

		try {
				
			  fin = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook1.txt"); //생성
			  fin2 = new FileInputStream("C:\\Users\\재호\\Desktop\\ebook\\ebook2.txt");
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
		
		next.setOnMouseClicked(e->{
			
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("ebookDetail1.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)next.getScene().getWindow();
			stage.setTitle("2쪽");
			stage.setScene(scene);
		});
		 
		sound.setOnMouseClicked(e->{
			
			        String clientId = "y9cwmxc7sd";//애플리케이션 클라이언트 아이디값";
			        String clientSecret = "4cjp28J5EIEqdieUlY2n86oJRPNYcYzXsWEvecI1";//애플리케이션 클라이언트 시크릿값";
			        try {
			            String text = URLEncoder.encode("교실 뒤로 조명이 꺼져버리다.미국의 두 지점을 잇는 중앙 아메리칸 남쪽 끝에는 가난한 어부들의 도시 푸에르토 카이미토가 있다.이곳에서 태어난 사람들을 만날 때 당신은 색안경을 써도좋다어부가 아니라면, 선박을 수리하거나생선 가공 공장, 또는 시장에생선으로 연결되어 있고 모두가 생선을 먹으며 버틴다.", "UTF-8"); // 13자
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
			                tempname = "ebook1";  //Long.valueOf(new Date().getTime()).toString();
			                //File f = new File("C:\\Users\\재호\\Desktop\\audio\\" + tempname + ".mp3");
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
			        
			        //
			        //String url = "file:/C:/Users/재호/Desktop/audio/" + tempname + ".mp3";
			
			        String url = "file:/D:/audio/ebook1.mp3";
			        Media a = new Media(url);
			        MediaPlayer mediaPlayer = new MediaPlayer(a);
			        mediaView.setMediaPlayer(mediaPlayer);
			        mediaPlayer.play();
			        
			        /*
			        //생성된 mp3파일 올리기
			        //생성된 오디오 파일을 선택해서 올림
			        FileChooser fc = new FileChooser();
			        fc.setTitle("음악 파일 선택");
			        fc.setInitialDirectory(new File("C:\\Users\\재호\\Desktop\\audio\\" + tempname + ".mp3")); // 기본 경로
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
			        //playList.setText(musicName);
			 
			        // 미디어 등록 - 플레이어 등록 - 뷰에 등록
			        media = new Media(mediaPath);
			        player = new MediaPlayer(media);
			        player.setAutoPlay(false); // 자동실행 false, play 버튼을 누를 시 음악이 재생되게끔
			        mediaView = new MediaView(player);
			        
			        //----- 이제 실행
			        
			        currentTime = 0;
			        totalTime = 0;
			        player.play();
			        player.setRate(1); // 재생속도 기본 (1), 디폴트값
			        totalTime = (long) player.getTotalDuration().toSeconds(); // 노래가 몇초짜리인지 반환
			        System.out.println(totalTime);
			 
			        // 슬라이더를 움직이기 위한 스레드 생성
			        // 스레드가 별개로 돌아가기 때문에 재생하고 창을 종료하면 계속 재생되는 상태이다.
			        Thread th = new Thread(()-> {
			            while(currentTime < totalTime) { // 재생시간에 따라 스레드 종료
			                currentTime = (long)player.getCurrentTime().toSeconds();
			                Platform.runLater(()->{
			                    
			                });
			                // sleep 0.3초를 주어서 좀 더 안정감있게 slidebar를 제어
			                // 없다면 계산이 너무 빠르기 때문에 불안정
			                try {
			                    Thread.sleep(300);
			                } catch (InterruptedException e2) {
			                    e2.printStackTrace();
			                }
			            }
			            player.stop();
			        });
			        th.start();


			    */
		});


		
	}
}
