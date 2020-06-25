package kr.or.ddit.view.usedbookboard;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kr.or.ddit.service.deal.IDealService;
import kr.or.ddit.service.mypage.IMyPagePointService;
import kr.or.ddit.service.usedbookboard.IUserBookBoardService;
import kr.or.ddit.session.Session;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.main.controller;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PointCategoryVO;
import kr.or.ddit.vo.PriceVO;

public class UserBookBuyController implements Initializable {

	@FXML
	JFXButton buy;
	@FXML
	JFXButton cancel;
	@FXML
	Label price;

	private Registry reg;
	IUserBookBoardService boardService;
	IDealService dealService;
	IMyPagePointService pointService;
	
	BoardVO bvo = new BoardVO();
	PointCategoryVO pcvo = new PointCategoryVO();
	
	ButtonType btnType;
	String nowPoint = null;
	String totalPoint = null;
	String tradeMoney = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			boardService = (IUserBookBoardService) reg.lookup("userBookBoardService");
			dealService = (IDealService) reg.lookup("dealService");
			pointService = (IMyPagePointService)reg.lookup("myPagePointService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		bvo = DataBoard.board;
		
		PriceVO pvo = null;
		try {
			pvo = boardService.selectPrice(bvo.getBoardNum());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tradeMoney = pvo.getTradeMoney();
		price.setText(tradeMoney);
		
		cancel.setOnAction(e -> {
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("userBookBoard.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		buy.setOnAction(e->{
			System.out.println("누름");
			btnType = MessageFactory.ReturninfoMsg("결제확인", "결제 확인 여부", "결제를 하시겠습니까?");
			if(btnType == ButtonType.OK) {
				int cnt = 0;
				
				System.out.println("num1" + Session.loginUser.getMemNum());
				System.out.println("num2" + bvo.getMemNum());
				//수신, 송신 거래인 내역 입력
				
				
				try {
					System.out.println("송수신인 입력 시작");
					cnt = dealService.insertDeal(Session.loginUser.getMemNum(), bvo.getMemNum(), Integer.parseInt(tradeMoney));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(cnt>0) {
					System.out.println("deal Insert성공");
				}
				
				pcvo.setMemNum(Session.loginUser.getMemNum());
				pcvo.setTransPoint(tradeMoney);
		
				System.out.println("시행");
				int cnt2 = 0;
				int cnt3 = 0;
				try {
					cnt2 = pointService.insertTransPoint(pcvo);
					
					if(cnt2 == 1) {
						 MessageFactory.ReturninfoMsg("결제", "결제 확인 ", "결제가 완료되었습니다.");
						System.out.println("거래 값 넣기 성공");
						
						//딜 삭제 
						cnt3 = boardService.deletePrice(bvo.getBoardNum());
						cnt3 = boardService.deletefile(bvo.getBoardNum());
						cnt3 = boardService.deleteUsedBookBoard(bvo.getBoardNum());
						
						if(cnt3 > 0) {
							System.out.println("삭제 완료");
						}
					}
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//기존에 있던 마일리지 차감 
				/*
				pcvo = pointService.selectNowPoint(Session.loginUser.getMemNum());
				nowPoint = pcvo.getNowPoint();
				
				int sum = Integer.parseInt(nowPoint) - Integer.parseInt(tradeMoney); 
				
				if(sum > 0) {
					int cnt2 = 0;
					
					PointCategoryVO pcvo = new PointCategoryVO();
					pcvo.setMemNum(Session.loginUser.getMemNum());
					pcvo.setNowPoint(Integer.toString(sum));
					
						try {
							cnt2 = pointService.updatePoint(pcvo);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					if(cnt2>0) {
						MessageFactory.infoMsg("결제완료", "결제성공", "결제를 완료하였습니다.");
					}
					
				}else {
					//포인트 부족, 충전알림
					MessageFactory.errMsg("결제불가", "결제실패", "잔액이 부족합니다.");
				}
				*/
				
				
				
				
				
				
				
				
				
				
				
			}
			
		});
		
	}
	
	
	
	

}
