package kr.or.ddit.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.deal.IDealService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.DealVO;
import com.jfoenix.controls.JFXButton;

public class myDealMainController implements Initializable {
	
	@FXML TableColumn<DealVO, Integer> no;
	@FXML TableColumn<DealVO, Integer> groupNo;
	@FXML TableColumn<DealVO, String> mem1;
	@FXML TableColumn<DealVO, String> mem2;
	@FXML TableColumn<DealVO, Integer> money;
	@FXML TableView<DealVO> table;
	@FXML JFXButton backBtn;  //뒤돌아가기 버튼
	
	
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
	
	
	
	private ObservableList<DealVO> db;
	private Registry reg;
	IDealService dealService;
	List<DealVO> list = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			dealService = (IDealService) reg.lookup("dealService");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		groupNo.setCellValueFactory(new PropertyValueFactory<>("dealGroupNum"));
		no.setCellValueFactory(new PropertyValueFactory<>("dealNum"));
		mem1.setCellValueFactory(new PropertyValueFactory<>("memNum"));
		mem2.setCellValueFactory(new PropertyValueFactory<>("memNum2"));
		//mem1.setCellValueFactory(new PropertyValueFactory<>("memId"));
		//mem2.setCellValueFactory(new PropertyValueFactory<>("memId2"));
		money.setCellValueFactory(new PropertyValueFactory<>("account"));
		
		db = FXCollections.observableArrayList();
		
		try {
			list = dealService.selectAllDeal(Session.loginUser.getMemNum());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(DealVO vo : list) {
			db.add(vo);
			db.setAll(list);
			table.setItems(db);
			
		}
		
		
		
		
		//뒤로가기버튼
				backBtn.setOnAction(e ->{
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage/myPageMain.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Scene scene = new Scene(root);
					Stage stage = (Stage) logo.getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				});
				
				
				
				
				/* 메뉴바 이동 경로 */
				logo.setOnMouseClicked(e -> {

					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/main/Main.fxml"));
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
		
	}
	
	
	

