package kr.or.ddit.view.notice;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import kr.or.ddit.service.notice.INoticeService;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.usedbookboard.UserBookBoardLoader;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class NoticeInsertController implements Initializable {

	private ObservableList<BoardVO> data;
	private Registry reg;
	private INoticeService service;

	@FXML
	JFXButton insBtn; // 글 등록버튼
	@FXML
	TextField title; // 글 제목 입력란
	@FXML
	TextArea content; // 글 내용 입력란
	@FXML
	TextField file; // 첨부파일 추가버튼 누르면 자동으로 들어가는 입력란?
	@FXML
	JFXButton fileInsBtn; // 첨부파일 추가 버튼
	@FXML
	JFXButton fileDelBtn; // 첨부파일 삭제 버튼
	@FXML
	JFXButton backBtn; // 공지사항 메인으로 돌아가는 버튼
	@FXML
	ImageView imgView = new ImageView();
	@FXML
	ImageView img;
	
	
	/* 메뉴 버튼들 */
	@FXML
	private ImageView logo;// 로고

	@FXML
	private MenuBar mButton1; // 도서관 이용 버튼

	@FXML
	private MenuBar mButton2; // 도서관 소개 버튼

	@FXML
	private MenuBar mButton3; // 게시판 버튼

	@FXML
	private MenuBar mButton4; // 신청 / 참여 버튼

	@FXML
	private MenuBar mButton5; // 독서 문화 활동 버튼

	@FXML
	private MenuItem wannaBook; // 희망도서 신청

	@FXML
	private MenuItem reserBook; // 도서대여

	@FXML
	private MenuItem seat; // 좌석

	@FXML
	private MenuItem studyRoom; // 스터디룸

	@FXML
	private MenuItem monthBook; // 이달의 도서

	@FXML
	private MenuItem chat; // 채팅

	@FXML
	private MenuItem usedBook; // 중고서적구매

	@FXML
	private MenuItem buy; // 삽니다

	@FXML
	private MenuItem sell; // 팝니다

	@FXML
	private MenuItem searchBook; // 도서 검색

	@FXML
	private MenuItem notice; // 공지사항

	@FXML
	private MenuItem QnA; // 문의사항

	@FXML
	private MenuItem community; // 커뮤니티
	
	
	
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
	
	
	
	
	
	private NoticeController noticeCon;
	
	

	public void setNoticeCon(NoticeController noticeCon) {
		this.noticeCon = noticeCon;
	}

	BoardVO b = new BoardVO();
	FilesVO f = new FilesVO();
   
	int cnt = 0;

	private Window primaryStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (INoticeService) reg.lookup("noticeService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// 등록버튼
		insBtn.setOnAction(e -> {
			if (title.getText().isEmpty() || content.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			int cnt = 0;
			int cnt2 = 0;
	

			
			// 1. attachFile폴더에 첨부된 파일 저장
			if (file == null) {
				System.out.println("파일은 NULL이다...................................................");
			}
			if (file != null) {
				File dir = new File("C:\\attachFile"); // 경로 지정
				if (!dir.exists()) // 경로에 폴더가 존재하지 않으면
					dir.mkdirs(); // 물리적 폴더를 생성(MakeDirectory)

				// 파일 생성
				/* File file22 = new FileInputStream(file); */
				String str = file.getText();

				File file33 = new File(dir, str.substring(str.lastIndexOf("\\") + 1)); // 경로, 파일
				f.setFileName(str.substring(str.lastIndexOf("\\") + 1));

				int c; // 읽어온 데이터를 저장할 변수
				try {

					FileOutputStream fos = new FileOutputStream(file33); // 복사할 대상
					FileInputStream fis = new FileInputStream(file.getText()); // 가져올 대상
					System.out.println("1111111111111111111111111=>" + file33.toString());
					System.out.println("2222222222222222222222222=>" + file.getText());
					
					// 읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다.
					while ((c = fis.read()) != -1) {
						// 읽어온 자료 출력하기
						fos.write(c);
					}
					fis.close();
					fos.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				b.setBoardTitle(title.getText());
				b.setBoardContent(content.getText());
				f.setFileName(str.substring(str.lastIndexOf("\\") + 1));

				data = FXCollections.observableArrayList(b);

				System.out.println("cnt");
				try {
					cnt = service.insertNotice(b, f);

					System.out.println("cnt");
					if (cnt == 1) {
						System.out.println("성공");
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				infoMsg("작업결과 >>", "정보 추가 완료!");

				title.clear();
				content.clear();
				file.clear();

				Stage stage = (Stage) insBtn.getScene().getWindow();
				stage.close();

				
				
				
				
				/////////////////////////
				if(cnt > 0 || cnt2 > 0) {
					MessageFactory.infoMsg("작성완료", "작성성공", "게시글 작성를 완료하였습니다.");
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("noticeMain.fxml"));
					Parent parent = null;
					
				    try{
			            parent = loader.load();
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }  
				    noticeMain.pStage.getScene().setRoot(parent);    
				}
				/////////////////////////

			}
			
			noticeCon.searchList();
			
		});


		// 파일 열기 창
		fileInsBtn.setOnAction(e1 -> {

			FileChooser fileChooser = new FileChooser();
			// 확장자별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3"), new ExtensionFilter("All Files", "*.*"));
			File uploadFile = fileChooser.showOpenDialog(primaryStage);

			try {
				if (uploadFile != null) {

					File dir = uploadFile.getParentFile();
					fileChooser.setInitialDirectory(dir);

					// 첨부파일란의 경로를 보여주고..
					file.setText(uploadFile.getPath().replaceAll("\n", "\r\n"));
				}
			} catch (Exception ex) {

			}

		});

		// X버튼 :목록
		backBtn.setOnAction(e -> {
			noticeCon.searchList();
			Stage stage = (Stage) backBtn.getScene().getWindow();
			
			
/////////////////////////
			int cnt = 0;
			int cnt2 = 0;
			
	if(cnt > 0 || cnt2 > 0) {
		MessageFactory.infoMsg("작성완료", "작성성공", "게시글 작성를 완료하였습니다.");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("noticeMain.fxml"));
		Parent parent = null;
	    try{
            parent = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	    noticeMain.pStage.getScene().setRoot(parent);
	}
/////////////////////////
			
			stage.close();
			
		});
		
		
		
		
		
		/* 메뉴바 이동 경로 */
		
		logo.setOnMouseClicked(e -> {

			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("kr/or/ddit/view/main/main.fxml"));
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

		studyRoom.setOnAction(e -> {
			// ㅠㅠ
		});

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

		studyRoom.setOnAction(e -> {
			dialog("/kr/or/ddit/view/studyroom/studyRoom.fxml", "StudyRoom");
		});
		usedBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});

		buy.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});

		monthBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/monthBook/SelectMonthBook.fxml", "monthBook");
		});
		seat.setOnAction(e -> {
			// ㅜㅠ
		});
		
		
		
		
		
		
		
	}

	/**
	 * 파일저장메소드
	 * 
	 * @param file
	 */
	private void saveFile(File file) {
		try {
			FileWriter writer = null;
			writer = new FileWriter(file);
			writer.write(file.getPath().replaceAll("\n", "\r\n"));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
		
	}
	
	
	
	

}
