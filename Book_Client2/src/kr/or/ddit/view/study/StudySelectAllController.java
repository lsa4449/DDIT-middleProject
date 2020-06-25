package kr.or.ddit.view.study;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.service.community.ICommunityService;
import kr.or.ddit.service.study.IStudyService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.community.selectallmain;
import kr.or.ddit.view.questions.DataQ;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.ComboBox;

public class StudySelectAllController implements Initializable {
	private ObservableList<BoardVO> b, currentPageData;
	private ObservableList<MemberVO> a;
	private Registry reg;
	IStudyService service;
	private int from, to, itemsForPage;
	Parent parent = null;
	List<BoardVO> list = null;
	List<BoardVO> insert = null;

	@FXML
	TableView<BoardVO> table;
	@FXML
	TableColumn<BoardVO, String> boardCount;
	@FXML
	TableColumn<BoardVO, String> boardDate;
	@FXML
	TableColumn<MemberVO, String> name;
	@FXML
	TableColumn<BoardVO, String> boardTitle;
	// 번호
	@FXML
	TableColumn<BoardVO, Integer> boardNum;
	@FXML
	TableColumn<BoardVO, Integer> memNum;
	//number
	@FXML 
	TableColumn<BoardVO, Integer> boardNum1;
	@FXML
	Pagination pagination;
	@FXML
	Button write;
	@FXML
	Button selbutton;
	@FXML
	TextField select;
	@FXML 
	ComboBox menuBtn;

	public void setdate(int cnt) {// 초기데이터값 세팅
		try {
			List<BoardVO> list = (List<BoardVO>) service.detailStudy(cnt);

			ObservableList<BoardVO> list2 = FXCollections.observableArrayList(list);
			table.setItems(list2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boardNum1.setCellValueFactory(new PropertyValueFactory<>("boardGroupNum"));
		boardNum.setCellValueFactory(new PropertyValueFactory<>("boardNum"));
		boardTitle.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));
		memNum.setCellValueFactory(new PropertyValueFactory<>("memNum"));
		boardDate.setCellValueFactory(new PropertyValueFactory<>("boardDate"));
		boardCount.setCellValueFactory(new PropertyValueFactory<>("boardCount"));

		b = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IStudyService) reg.lookup("studyService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		if(Session.loginUser.getAuthority().equals("0")) {
			write.setDisable(false);
		}else if(Session.loginUser.getAuthority().equals("1")){
			write.setDisable(false);			
		}else {
			write.setDisable(true);
		}
		
		List<BoardVO> list = new ArrayList<BoardVO>();

		try {
			list = service.BoardStudy();
			int listSize = list.size();
			System.out.println(listSize);
			for (int i = 0; i < listSize; i++) {
				System.out.println(list.get(i).getBoardTitle());
			}

		} catch (RemoteException e) {
			System.out.println("selectAll 메소드 에러");
			e.printStackTrace();
		}

		// 상세조회
		table.setOnMouseClicked(e -> {
			int cnt = 0;
			BoardVO vo = new BoardVO();
			vo = table.getSelectionModel().getSelectedItem();
			
			DataStudy.board = vo;
			System.out.println(vo.getBoardCount());
			System.out.println("board Num" + vo.getBoardNum());
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
				root = FXMLLoader.load(getClass().getResource("detailStudy.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});

		menuBtn.getItems().add("제목");
		menuBtn.getItems().add("전체");
		menuBtn.setValue("전체");
		
		selbutton.setOnAction(e ->{
			
			searchList();
			
		});	
		
		// 페이징
		itemsForPage = 5;
		int totalDataCnt = b.size();
		int totalPageCnt = totalDataCnt % itemsForPage == 0 ? totalDataCnt / itemsForPage
				: totalDataCnt / itemsForPage + 1;

		pagination.setPageCount(totalPageCnt);

		pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				table.setItems(getTableViewData(from, to));

				return table;
			}

			private ObservableList<BoardVO> getTableViewData(int from, int to) {
				currentPageData = FXCollections.observableArrayList();
				int totSize = b.size();
				for (int i = from; i <= to && i < totSize; i++) {
					currentPageData.add(b.get(i));
				}

				return currentPageData;
			}
		});
		write.setOnAction(e -> {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("writeStudy.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});

	}
	public void searchList() {
		
		String searchTitle = null;
		searchTitle = select.getText();
		
		List<BoardVO> vo;
		try {
			b.clear();
			vo = service.selectStudy(searchTitle);
			System.out.println("vo 확인 : " + vo);
			System.out.println(searchTitle);
			b.addAll(vo);
			table.setItems(b);
			
			//System.out.println(vo.getBoardNum());
			System.out.println("검색된 데이터 : ");
			//System.out.println(vo.getBoardTitle());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

}
