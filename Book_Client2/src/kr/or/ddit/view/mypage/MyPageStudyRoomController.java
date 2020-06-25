package kr.or.ddit.view.mypage;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.service.mypage.IMyPageStudyRoomService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyStudyRoomSelect_JoinVO;

public class MyPageStudyRoomController implements Initializable{


	@FXML TableView tv;
	@FXML TableColumn tcName;
	@FXML TableColumn tcReserve;
	@FXML TableColumn tcReturn;
	@FXML TableColumn tcRoom;
	
	private IMyPageStudyRoomService service;
	private Registry reg;
	
	MemberVO vo = Session.loginUser;
	
	private ObservableList<MyStudyRoomSelect_JoinVO> tableList;
	
	List<MyStudyRoomSelect_JoinVO> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMyPageStudyRoomService) reg.lookup("myPageStudyRoomService");
			System.out.println("rmi성공");
		}catch (Exception e) {
			System.out.println("myPageStudyRoomService lookup오류");
			e.printStackTrace();
		}
		
		tcName.setCellValueFactory(new PropertyValueFactory<>("adMyStudyRoomName"));
		tcReserve.setCellValueFactory(new PropertyValueFactory<>("adMyStudyRoomReserveTime"));
		tcReturn.setCellValueFactory(new PropertyValueFactory<>("adMyStudyRoomReturnTime"));
		tcRoom.setCellValueFactory(new PropertyValueFactory<>("adMyStudyRoomNum"));
		
		
	tableList = FXCollections.observableArrayList();
	list = new ArrayList<MyStudyRoomSelect_JoinVO>();
	
	try {
		list = service.selectMyStudyRoom(vo.getMemNum());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for (MyStudyRoomSelect_JoinVO myStudyRoomSelect_JoinVO : list) {
		tableList.add(myStudyRoomSelect_JoinVO);
	}
	
	tv.setItems(tableList);
	
	
		
		
		
		
		
		
		
		
	}

}
