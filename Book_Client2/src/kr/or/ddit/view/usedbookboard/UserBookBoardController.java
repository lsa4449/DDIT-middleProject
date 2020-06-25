package kr.or.ddit.view.usedbookboard;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.service.usedbookboard.IUserBookBoardService;
import kr.or.ddit.session.Session;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.main.controller;
import kr.or.ddit.vo.BoardVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

public class UserBookBoardController implements Initializable {
	
	static Stage stage = new Stage();
	
	@FXML
	JFXComboBox menuBtn; // 전체, 제목 버튼?
	@FXML
	JFXButton titleBtn; // 제목검색버튼
	@FXML
	TextField titleText; // 제목쓰는 입력란
	@FXML
	TableView<BoardVO> table; // 공지사항 테이블박스
	@FXML
	TableColumn<BoardVO, Integer> no; // 게시글 번호
	@FXML
	TableColumn<BoardVO, Integer> boardGroupNum; // 게시글 번호
	@FXML
	TableColumn<BoardVO, String> title; // 게시글 제목
	@FXML
	TableColumn<BoardVO, String> date; // 게시글 날짜
	@FXML
	TableColumn<BoardVO, Integer> count; // 게시글 조회수
	@FXML 
	TableColumn<BoardVO, Integer> write;
	@FXML 
	TableColumn<BoardVO, Integer> groupNo;
	@FXML
	JFXButton insertBtn; // 등록버튼
	@FXML
	JFXButton deleteBtn; // 삭제버튼
	@FXML
	Pagination pagination; // 페이징
	@FXML
	JFXButton cellBtn; // 팝니다버튼
	@FXML
	JFXButton buyBtn; // 삽니다버튼
	@FXML
	JFXButton updateBtn; // 수정버튼
	@FXML
	JFXButton backBtn; //뒤로가기 버튼 
	
	
	
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
	

	private ObservableList<BoardVO> ob, currentPageData;
	
	private Registry reg;
	IUserBookBoardService service;
	 Parent root=null;
	List<BoardVO> list = null;
	int ButtonCheck = 0;

	private int from, to, itemsForPage;
	//private ObservableList<BoardVO> allTableData, currentPageData;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
       
		//버튼숨기기 
		if(!Session.loginUser.getAuthority().equals("0")) {
			backBtn.setVisible(false);
		}
		
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IUserBookBoardService) reg.lookup("userBookBoardService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		groupNo.setCellValueFactory(new PropertyValueFactory<>("boardGroupNum"));
		title.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));
		date.setCellValueFactory(new PropertyValueFactory<>("boardDate"));
		count.setCellValueFactory(new PropertyValueFactory<>("boardCount"));
		no.setCellValueFactory(new PropertyValueFactory<>("boardNum"));
		write.setCellValueFactory(new PropertyValueFactory<>("memNum"));
		
		menuBtn.getItems().add("제목");
		menuBtn.getItems().add("작성자");
		menuBtn.setValue("제목");
		
		ob = FXCollections.observableArrayList();
		
		try {
			list = service.selectAll(1);
		} catch (RemoteException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		for (BoardVO vo : list) {
			ob.add(vo);
			ob.setAll(list);
			table.setItems(ob);
		}
		
		buyBtn.setOnAction(e ->{
			try {
				list = service.selectAll(6);
				for (BoardVO vo : list) {
					ob.clear();
					ob.add(vo);
					ob.setAll(list);
					table.setItems(ob);
					ButtonCheck = 6;
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		});
		
		cellBtn.setOnAction(e -> {
			try {
				list = service.selectAll(1);
				for (BoardVO vo : list) {
					ob.clear();
					ob.add(vo);
					ob.setAll(list);
					table.setItems(ob);
					ButtonCheck = 1;
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		titleBtn.setOnAction(e ->{
			if(titleText == null) {
				return;
			}
			String textBtnText = (String) menuBtn.getSelectionModel().getSelectedItem();
			System.out.println(textBtnText);
			String searchTitle = titleText.getText().trim();
			System.out.println(searchTitle);
			
			if(textBtnText.equals("제목")) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardTitle(searchTitle);
				bvo.setBoardKindNum(ButtonCheck);
				try {
					List<BoardVO> list = null;
					list = service.selectTitleUserBookBoard(bvo);
					if(list.size() == 0) {
						MessageFactory.errMsg("에러!", "결과없음", "찾으시는 단어의 결과가 존재하지 않습니다");
						return; 
					}
					for (BoardVO vo : list) {
						ob.clear();
						ob.add(vo);
						ob.setAll(list);
						table.setItems(ob);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(textBtnText.equals("작성자")) {
				try {
					List<BoardVO> list = null;
					if(ButtonCheck == 6) {
						list = service.selectMemUserBookBuyBoard(searchTitle);
					}else if(ButtonCheck == 1){
						list = service.selectMemUserBookCellBoard(searchTitle);
					}
					if(list.size() == 0) {
						MessageFactory.errMsg("에러!", "결과없음", "찾으시는 단어의 결과가 존재하지 않습니다");
						return; 
					}
					for (BoardVO vo : list) {
						ob.clear();
						ob.add(vo);
						ob.setAll(list);
						table.setItems(ob);
					}
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		//페이징버튼
		itemsForPage = 5; // 한페이에 보여줄 항목 수 설정
		int totalDataCnt = ob.size();
		int totalPageCnt = totalDataCnt % itemsForPage == 0 ? 
				totalDataCnt / itemsForPage : totalDataCnt / itemsForPage + 1;
		
		pagination.setPageCount(totalPageCnt); // 전체 페이지수 설정
		
							// 방법1 Callback타입의 익명객체 생성
		pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				table.setItems(getTableViewData(from, to));
				
				return table;
			}

			private ObservableList<BoardVO> 
					getTableViewData(int from, int to) 
			{
				currentPageData = FXCollections.observableArrayList();
				int totSize = ob.size();
				for(int i = from; i <= to && i < totSize; i++) {
					currentPageData.add(ob.get(i));
				}
				
				return currentPageData;
			}
		});
		
		table.setOnMouseClicked(e ->{
			int cnt = 0;
			BoardVO vo = new BoardVO();
			vo = table.getSelectionModel().getSelectedItem();
			
			DataBoard.board = vo;
			System.out.println(vo.getBoardCount());
			System.out.println("board Num" + vo.getBoardNum());
			System.out.println("memNum" + vo.getMemNum());
			try {
				cnt = service.upboardCount(vo);
				System.out.println("조회수");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(cnt > 0) {
				System.out.println("조회수 증가 성공");
	
			}
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("userBookBoardDetail.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		
		insertBtn.setOnAction(e -> {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("InsertUserBookBoard.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		
		
		//뒤로가기버튼
				backBtn.setOnAction(e ->{
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/boardManage.fxml"));
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
				root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/adminMain.fxml"));
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
