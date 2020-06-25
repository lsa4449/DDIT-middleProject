package kr.or.ddit.view.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.login.LoginController;
import javafx.scene.control.MenuItem;

public class controller implements Initializable {

	LoginController con;


	@FXML
    private MenuBar mButton3;

    @FXML
    private MenuItem notice;

    @FXML
    private MenuItem buy;

    @FXML
    private MenuItem community;

    @FXML
    private MenuItem QnA;

    @FXML
    private MenuItem QnA1;

    @FXML
    private MenuBar mButton4;

    @FXML
    private MenuItem wannaBook;

    @FXML
    private MenuItem reserBook;

    @FXML
    private MenuItem seat;

    @FXML
    private MenuItem monthBook;
    
    @FXML
    private MenuItem kakaomap;

    @FXML
    private MenuItem chat;

    @FXML
    private MenuBar mButton5;

    @FXML
    private MenuItem usedBook1;

    @FXML
    private MenuBar mButton1;

    @FXML
    private MenuItem searchBook;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView search;

    @FXML
    private MenuBar mButton2;

    @FXML
    private Button btnLogin;

    @FXML
    private Button signup_btn;

    @FXML
    private ImageView wannaBook_image;

    @FXML
    private ImageView ebook_btn;

    @FXML
    private ImageView chatbot_btn;

    @FXML
    private ImageView chat_btn;

    @FXML
    private MediaView media;

    @FXML
    private ImageView map;

    @FXML
    private ImageView play;

    @FXML
    private ImageView stop;

    @FXML
    private Label city;

    @FXML
    private Label state;

    @FXML
    private Label tempe;

    @FXML
    private ImageView arrow;
    
    @FXML
    private MenuItem writeBook_btn;

    @FXML
    private Label userName_label;


	@FXML MenuItem studyRoom;
/*
	public void visible() {
		if (menubar.isVisible()) {
			menubar.setVisible(false);
		} else {
			menubar.setVisible(true);
		}
	}
*/
	public void dialog(String path, String title) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		// 2.모달창 여부 설정
		// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
		dialog.initModality(Modality.APPLICATION_MODAL);

		dialog.setTitle(title);

		// 4. 자식창에 나타날 컨테이너객체 생성
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 5. Scene 객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene 객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정
		dialog.show();

	}

	public void err(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void name() {
		if (Session.loginUser.getName() != null) {
			btnLogin.setText(Session.loginUser.getName() + "님 환영합니다.");
			btnLogin.setText("내 페이지");
			signup_btn.setText("로그아웃");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		name();
		
		// 로그인이 안되어 있을 때는 00값 삽입
		if(Session.loginUser.getAuthority()==null) {
			Session.loginUser.setAuthority("00");
		}
		
		
		System.out.println("이니셜 라이즈 수행");

		// 동영상 실행
//		String url = "https://youtu.be/yhXT3hBaDoU";
		String url = "file:/C:/동영상/movie.mp4";

		Media mediaView = new Media(url);
		MediaPlayer mediaPlayer = new MediaPlayer(mediaView);
		media.setMediaPlayer(mediaPlayer);
		media.setFitHeight(1000);
		media.setFitWidth(500);

		mediaPlayer.stop();

		Image image1 = new Image("file:src/images/play.png");
		play.setImage(image1);

		Image image2 = new Image("file:src/images/stop.png");
		stop.setImage(image2);

		play.setOnMouseClicked(e -> {

			mediaPlayer.play();
		});

		stop.setOnMouseClicked(e -> {

			mediaPlayer.stop();
		});

		Image image3 = new Image("file:src/images/next.png");
		arrow.setImage(image3);

		Image image4 = new Image("file:src/images/daejeon.png");
		map.setImage(image4);

		// 날씨 api로 경도 위도를 통해 지역 날씨를 알려줌
		try {
			String lon = "127.4201068652042"; // 경도
			String lat = "36.32491232653325"; // 위도

			// OpenAPI call하는 URL
			String urlstr = "http://api.openweathermap.org/data/2.5/weather?" + "lat=" + lat + "&lon=" + lon
					+ "&appid=3666a84d4f684b8b27ea0ef4ffdf8dd7";
			URL url2 = new URL(urlstr);
			BufferedReader bf;
			String line;
			String result = "";

			// 날씨 정보를 받아온다.
			bf = new BufferedReader(new InputStreamReader(url2.openStream()));

			// 버퍼에 있는 정보를 문자열로 변환.
			while ((line = bf.readLine()) != null) {
				result = result.concat(line);
			}

			// 문자열을 JSON으로 파싱
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

			// 지역 출력
			System.out.println("지역 : " + jsonObj.get("name"));
			String city2 = (String) jsonObj.get("name");

			// javaFx에 지역 출력
			city.setText(city2);

			// 날씨 출력
			JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
			JSONObject obj = (JSONObject) weatherArray.get(0);

			System.out.println("날씨 : " + obj.get("main"));
			String state2 = (String) obj.get("main");

			// javaFX에 날씨 출력
			state.setText(state2);

			// 온도 출력(절대온도라서 변환 필요)
			JSONObject mainArray = (JSONObject) jsonObj.get("main");
			double ktemp = Double.parseDouble(mainArray.get("temp").toString());
			double temp = ktemp - 273.15;

			System.out.printf("온도 : %.2f\n", temp);
			Double d = Double.parseDouble(String.format("%.2f", temp));
			String temp2 = Double.toString(d);

			// javaFx에 온도 출력!!!
			tempe.setText(temp2);

			bf.close();

		} catch (Exception e) {

		}

	

		chat_btn.setOnMouseClicked(e -> {

			if (Session.loginUser.getName() == null) {
				err("로그인 에러", "로그인 에러다아", "로그인이 필요한 서비스 입니다.");
			} else {
				Stage dialog = new Stage(StageStyle.UTILITY);

				// 2.모달창 여부 설정
				// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다 -> 벗 나는 쓸꺼얌
				dialog.initModality(Modality.NONE);

				dialog.setTitle("chat");

				// 4. 자식창에 나타날 컨테이너객체 생성
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/chat/Main.fxml"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				// 5. Scene 객체 생성해서 컨테이너 객체 추가
				Scene scene = new Scene(parent);

				// 6. Stage객체에 Scene 객체 추가
				dialog.setScene(scene);
				dialog.setResizable(false); // 크기 고정
				dialog.show();

			}

		});
		
		writeBook_btn.setOnAction(e->{
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("ebookWrite");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				// parent = FXMLLoader.load(getClass().getResource(path));

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/ebook/ebookWrite2.fxml"));
				parent = loader.load();
				// LoginController con = loader.getController();
				// con.setController(this);

			} catch (IOException e2) {
				e2.printStackTrace();
			}
			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.showAndWait();
		});

		btnLogin.setOnMouseClicked(e -> {

			if (btnLogin.getText().equals("로그인")) {
				Stage dialog = new Stage(StageStyle.UTILITY);

				// 2.모달창 여부 설정
				// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
				dialog.initModality(Modality.APPLICATION_MODAL);

				dialog.setTitle("chat");

				// 4. 자식창에 나타날 컨테이너객체 생성
				Parent parent = null;
				try {
					// parent = FXMLLoader.load(getClass().getResource(path));

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/login/login2.fxml"));
					parent = loader.load();
					// LoginController con = loader.getController();
					// con.setController(this);

				} catch (IOException e2) {
					e2.printStackTrace();
				}
				// 5. Scene 객체 생성해서 컨테이너 객체 추가
				Scene scene = new Scene(parent);

				// 6. Stage객체에 Scene 객체 추가
				dialog.setScene(scene);
				dialog.setResizable(false); // 크기 고정
				dialog.showAndWait();
				name();
				userName_label.setText(Session.loginUser.getName()+"님 환영합니다.");
			} else {
				dialog("/kr/or/ddit/view/mypage/myPageMain.fxml", "myPage");
			}

		});

		// 도서 검색 버튼 클릭
		search.setOnMouseClicked(e -> {
			dialog("AllsearchMain.fxml", "bookSearch");
		});

		signup_btn.setOnMouseClicked(e -> {
			if(signup_btn.getText().equals("회원가입")) {
				dialog("/kr/or/ddit/view/join/Join.fxml", "Join");
			}else if(signup_btn.getText().equals("로그아웃")) {
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("main.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Session.loginUser = null;
				btnLogin.setText("로그인");
				signup_btn.setText("회원가입");
				userName_label.setText("");
				Scene scene = new Scene(root);
				Stage stage = (Stage) logo.getScene().getWindow();
				stage.setScene(main.mainScene);
				stage.show();

				
			}
			
		});
		ebook_btn.setOnMouseClicked(e -> {
			dialog("/kr/or/ddit/view/ebook/ebook.fxml", "ebook");
		});

		chatbot_btn.setOnMouseClicked(e -> {
			dialog("/kr/or/ddit/view/javaFxChatBot/chatBot.fxml", "chatBot");
		});

		
		
		
		
		
		/* 메뉴바 이동 경로 */
	
		logo.setOnMouseClicked(e -> {

			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("main.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Scene scene = new Scene(root);
			Stage stage = (Stage) logo.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		});
		
		
		notice.setOnAction(e -> {
			dialog("/kr/or/ddit/view/notice/noticeMain.fxml", "notice");
		});
/*
		studyRoom.setOnAction(e -> {
			// ㅠㅠ
		});
*/
		QnA.setOnAction(e -> {
			dialog("/kr/or/ddit/view/questions/selQuestion.fxml", "QnA");
		});

		community.setOnAction(e -> {
			dialog("/kr/or/ddit/view/community/selCom.fxml", "Community");
		});

		wannaBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/wannabook/Wannabook.fxml", "WannaBook");
		});

		reserBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/book/reserBook.fxml", "reserveBook");
		});

		searchBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/book/selectBook.fxml", "selectBook");
		});

		wannaBook_image.setOnMouseClicked(e->{
			dialog("/kr/or/ddit/view/wannabook/wannaBook.fxml", "selectBook");
		});
		/*
		studyRoom.setOnAction(e -> {
			dialog("/kr/or/ddit/view/studyroom/studyRoom.fxml", "StudyRoom");
		});
		usedBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});
*/	
		buy.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});

		monthBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/monthBook/SelectMonthBook.fxml", "monthBook");
		});
		seat.setOnAction(e -> {
			dialog("/kr/or/ddit/view/seat/seat.fxml", "seat");
		});
		
		kakaomap.setOnAction(e -> {
			dialog("/kr/or/ddit/view/map/kakaoMap.fxml", "kakaomap");
		});
		studyRoom.setOnAction(e->{
			dialog("/kr/or/ddit/view/studyroom/studyRoom.fxml", "studyRoom");
		});
//		buy_usedbook_btn.setOnMouseClicked(e->{
//		    	// ㅠㅠ
//		    });

//끝나는 괄호
	}
}
