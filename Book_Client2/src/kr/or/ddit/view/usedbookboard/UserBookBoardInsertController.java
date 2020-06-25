package kr.or.ddit.view.usedbookboard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.service.usedbookboard.IUserBookBoardService;
import kr.or.ddit.session.Session;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.main.controller;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.PriceVO;

public class UserBookBoardInsertController implements Initializable{

	@FXML Label writerDate;
	@FXML Label total;
	@FXML TextField title;
	@FXML TextField file;
	@FXML JFXButton fileInsBtn;
	@FXML JFXButton fileDelBtn;
	@FXML JFXButton backBtn;
	@FXML JFXButton insert;
	@FXML TextArea content;
	@FXML JFXComboBox boardChoice;
	@FXML Label priceLabel;
	@FXML TextField price;

	private Registry reg;
	IUserBookBoardService service;
	String str = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IUserBookBoardService) reg.lookup("userBookBoardService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		priceLabel.setVisible(false);
		price.setVisible(false);
		
		System.out.println(Session.loginUser.getMemNum());
		//콤보박스로 팝니다, 삽니다 게시판 선택 
		boardChoice.setValue("삽니다");
		boardChoice.getItems().add("팝니다");
		boardChoice.getItems().add("삽니다");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		Date time = new Date();
		String time1 = format.format(time);
		writerDate.setText(time1);
		
		fileInsBtn.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File selectFile = fileChooser.showOpenDialog(null);
			 if (selectFile != null) {
				 str = selectFile.getPath();
			 }
			 file.setText(str);	 
		});
		
		fileDelBtn.setOnAction(e->{
			str = null;
		});
		
		boardChoice.setOnAction(e->{
			String temp = boardChoice.getValue().toString();
			if(temp.equals("팝니다")) {
			priceLabel.setVisible(true);
			price.setVisible(true);
			}else {
				priceLabel.setVisible(false);
				price.setVisible(false);
			}
		});
		
		insert.setOnAction(e->{
			int cnt = 0;
			//int cnt2 = 0;
			//int cnt3 = 0;
			int boardNum = 0;
			
			BoardVO bvo = new BoardVO();
			bvo.setBoardTitle(title.getText());
			bvo.setBoardContent(content.getText());
			bvo.setMemNum(Session.loginUser.getMemNum());
			
			String temp = boardChoice.getValue().toString();
			if(temp.equals("팝니다")) {
				bvo.setBoardKindNum(6);
			}else {
				bvo.setBoardKindNum(1);
			}
	
			try {
				cnt = service.insertUserBookBoard(bvo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				boardNum = service.searchboardNum();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (str != null) {
				FilesVO fvo = new FilesVO();
				fvo.setFileName(str);
				fvo.setBoardNum(boardNum);
				
				try {
					cnt = service.insertfile(fvo);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//팝니다 게시판의 경우, 가격 지정 
			if(temp.equals("팝니다")) {
				PriceVO pvo = new PriceVO();
				pvo.setBoardNum(boardNum);
				pvo.setTradeMoney(price.getText());
				
				try {
					cnt = service.insertBookBoardBuyPrice(pvo);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if(cnt > 0) {
				MessageFactory.infoMsg("작성완료", "작성성공", "게시글 작성를 완료하였습니다.");
				
				/*
				FXMLLoader loader = new FXMLLoader(getClass().getResource("userBookBoard.fxml"));
				Parent parent = null;
			    try{
		            parent = loader.load();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			    controller.pStage.getScene().setRoot(parent);
			    */
				
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
				
				
			}
		
			
		});
	
		
}
}
