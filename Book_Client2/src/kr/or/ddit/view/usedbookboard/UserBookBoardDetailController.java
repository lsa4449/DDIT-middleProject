package kr.or.ddit.view.usedbookboard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.service.join.IJoinService;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.service.usedbookboard.IUserBookBoardService;
import kr.or.ddit.session.Session;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.main.controller;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.PriceVO;

public class UserBookBoardDetailController implements Initializable {
	@FXML 
	JFXTextField title;
	@FXML 
	JFXTextField memname;
	@FXML 
	JFXTextField data;
	@FXML 
	JFXTextArea content;
    @FXML 
    JFXButton listBtn;
    @FXML 
    JFXButton deleteBtn;
    @FXML
    JFXButton updateBtn;
    @FXML
    ImageView image;
    @FXML
    Label menuTitle;
    @FXML
    JFXButton check;
    @FXML
    JFXButton checkfile;
    @FXML
    JFXButton buyButton;
    @FXML
    Label priceLabel;
    @FXML
    Label price;
    
    private Registry reg;
	IUserBookBoardService service;
    BoardVO bvo = new BoardVO();
    ArrayList<FilesVO> list = new ArrayList<>();
    String str = null;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Session.loginUser.setAuthority("00");
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IUserBookBoardService) reg.lookup("userBookBoardService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		check.setVisible(false);
		checkfile.setVisible(false);
		buyButton.setVisible(false);
		priceLabel.setVisible(false);
		
		
		bvo = DataBoard.board;
		int mem1 = Session.loginUser.getMemNum();
		int mem2 = bvo.getMemNum();
		
		System.out.println("mem1:" + mem1);
		System.out.println("mem2:" + mem2);
		
		if (!Session.loginUser.getAuthority().equals("0")) {
			if (mem1 != mem2) {
				updateBtn.setVisible(false);
				deleteBtn.setVisible(false);
			}
		}

		title.setText(bvo.getBoardTitle());
		memname.setText(Integer.toString(bvo.getMemNum()));
		content.setText(bvo.getBoardContent());
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String to = transFormat.format(bvo.getBoardDate());
		data.setText(to);
		
		title.setEditable(false);
		memname.setEditable(false);
		content.setEditable(false);
		data.setEditable(false);
		
		if(bvo.getBoardKindNum() == 1) {
			menuTitle.setText("삽니다");
		}else if(bvo.getBoardKindNum() == 6) {
			menuTitle.setText("팝니다");
			buyButton.setVisible(true);
			priceLabel.setVisible(true);
		}
		if(Session.loginUser.getMemNum()==mem2) {buyButton.setVisible(false);}  //로그인세션의 회원번호랑 게시판 올린 사람의 회원번호랑 같으면 구매하기 버튼이 안보이게
		//파일 가져오기 
		try {
			list = (ArrayList<FilesVO>) service.selectFile(bvo.getBoardNum());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.size());
		
		if(list.size() != 0) {
			String str = list.get(0).getFileName();
			try {
				FileInputStream fis = new FileInputStream(str);
				BufferedInputStream bis = new BufferedInputStream(fis);
				Image img = new Image(bis);
				image.setImage(img);
				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(bvo.getBoardKindNum() == 6) {
			PriceVO pvo = null;
			
			System.out.println(bvo.getBoardNum());
	
			try {
				pvo = service.selectPrice(bvo.getBoardNum());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(pvo.getTradeMoney());
			price.setText(pvo.getTradeMoney());
			
		}
		
		
		//삭제 버튼 누르기 
		deleteBtn.setOnAction(e -> {
			int cnt = 0;
			if(list.size() != 0) {
				try {
					System.out.println(DataBoard.board.getBoardNum());
					cnt = service.deletefile(DataBoard.board.getBoardNum());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(bvo.getBoardKindNum() == 6) {
			try {
				cnt = service.deletePrice(DataBoard.board.getBoardNum());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			}
			
			
			try {
				System.out.println(DataBoard.board.getBoardNum());
				cnt = service.deleteUsedBookBoard(DataBoard.board.getBoardNum());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if (cnt > 0) {
				MessageFactory.infoMsg("삭제완료", "삭제성공", "게시글 삭제를 완료하였습니다.");
				title.clear();
				content.clear();
			
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
				
			} else {
				MessageFactory.errMsg("삭제불가", "삭제실패", "게시글 삭제를 실패했습니다.");
			}
		});
		
		updateBtn.setOnAction(e ->{
			check.setVisible(true);
			deleteBtn.setVisible(false);
			updateBtn.setVisible(false);
			title.setEditable(true);
			content.setEditable(true);
			checkfile.setVisible(true);
			
		});

		checkfile.setOnAction(e ->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File selectFile = fileChooser.showOpenDialog(null);
			 if (selectFile != null) {
				 str = selectFile.getPath();
				 
				 try {
						FileInputStream fis = new FileInputStream(str);
						BufferedInputStream bis = new BufferedInputStream(fis);
						Image img = new Image(bis);
						image.setImage(img);
						try {
							bis.close();
							fis.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
			
					} catch (FileNotFoundException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				 
				}
			
		});
		
				check.setOnAction(e ->{
					int cnt = 0;
					int cnt2 = 0;
					
					System.out.println(str);
					if(str != null) {
						FilesVO vo = new FilesVO();
						vo.setFileName(str);
						vo.setBoardNum(DataBoard.board.getBoardNum());
						System.out.println("str" + str);
						System.out.println("BoardNum" + DataBoard.board.getBoardNum());
						try {
							cnt = service.updatefile(vo);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					if (title.getText().isEmpty() || content.getText().isEmpty()) {
						MessageFactory.errMsg("수정불가", "수정실패", "빈 항목이 있습니다.");
						return;
					}

					BoardVO vo = new BoardVO();
					vo.setBoardTitle(title.getText());
					vo.setBoardContent(content.getText());
					vo.setBoardNum(DataBoard.board.getBoardNum());
					
					try {
						cnt2 = service.updateUserBookBoard(vo);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println(cnt);
					System.out.println(cnt2);
					if (cnt > 0  || cnt2 > 0 ){
						MessageFactory.infoMsg("수정완료", "수정성공", "게시글 수정를 완료하였습니다.");
					}
					
					
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
				
				listBtn.setOnAction(e ->{	
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
			
				buyButton.setOnAction(e->{
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("userBookBuy.fxml"));
						Scene scene = new Scene(root);
						Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
						stage.setScene(scene);
						stage.show();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
				});
				
				
	}	
    
	}

