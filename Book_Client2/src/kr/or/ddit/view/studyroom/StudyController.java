package kr.or.ddit.view.studyroom;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.service.studyroom.IStudyroomService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyStudyRoomVO;
import kr.or.ddit.vo.StudyRoomRentalVO;
import kr.or.ddit.vo.StudyRoomVO;

public class StudyController implements Initializable {

	private Registry reg;
	IStudyroomService service;

	@FXML
	ImageView search;
	@FXML
	ImageView logo;
	@FXML
	ImageView studyroom1;
	@FXML
	ImageView studyroom2;
	@FXML
	ImageView studyroom3;
	@FXML
	ImageView studyroom4;
	@FXML
	ImageView studyroom5;
	@FXML
	ImageView studyroom6;
	
	StudyRoomVO vo;
	StudyRoomRentalVO rentalVO = new StudyRoomRentalVO();
	
	MemberVO vovo = Session.loginUser;

	String studyRoomStateReserveOk[] = new String[6]; // 예약할 수 있는 상태의 사진 이름
	String studyRoomStateReserved[] = new String[studyRoomStateReserveOk.length]; // 예약된 상태의 사진 이름
	ImageView studyRoomName[]; // 스터디룸의 이름 버튼

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studyRoomName = new ImageView[] {studyroom1, studyroom2, studyroom3, studyroom4, studyroom5, studyroom6}; // 스터디룸의 이름 버튼
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IStudyroomService) reg.lookup("studyRoomService");
			System.out.println("StudyController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		  

		// 이름을 주는 과정
		for (int i = 0; i < studyRoomStateReserveOk.length; i++) {
			studyRoomStateReserveOk[i] = "file:src/images/studyroom" + (i + 1) + "_1.png"; // 예약할 수 있는 상태
			studyRoomStateReserved[i] = "file:src/images/studyroom" + (i + 1) + ".png"; // 예약된 상태
		}

//		Image image = new Image("file:src/images/1.png");
//		studyroom1.setImage(image);
		
		List<StudyRoomVO> voList = new ArrayList<StudyRoomVO>();
		try {
			voList = service.selectStudyRoom();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 상태 출력
		for (int i = 0; i < voList.size(); i++) {
			StudyRoomVO vo = voList.get(i);
			if(vo.getStudyRoomState().equals("0")) { // 0 이 대기 상태
				Image image2 = new Image(studyRoomStateReserveOk[i]);
					
					studyRoomName[i].setImage(image2); 
				
			}else {
				Image image2 = new Image(studyRoomStateReserved[i]);
				studyRoomName[i].setImage(image2);
			}
			
		}
		
		
// 스터디룸 클릭해서 될 수 예약이 될 수 있도록 한다.		
		voList = new ArrayList<StudyRoomVO>();
		try {
			voList = service.selectStudyRoom();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rentalVO.setMemNum(vovo.getMemNum()); // 로그인 유저의 회원 번호 rentalVO에 넣기
		
		for (int i = 0; i < studyRoomName.length; i++) {
			 
			 vo = voList.get(i);
			 
			
			
			studyRoomName[i].setOnMouseClicked(new EventHandler() {
				
				

				@Override
				public void handle(Event event) {
//					// TODO Auto-generated method stub
//					if(vo.getStudyRoomState().equals("0")) {
//						Image image = new Image(studyRoomStateReserveOk[i]);
//					}else {
//						
//					}
					System.out.print("if 문에 접속하기 전 : ");
					ImageView textImageViewTarget = (ImageView) event.getTarget();
					System.out.println("현재 상태 : " + vo.getStudyRoomState());
					System.out.println(textImageViewTarget.getId());
					System.out.println("로그인 유저의 회원 번호 : " + rentalVO.getMemNum());
					
					// 넌 할 수있어! 넌 완벽해!!! -마니또-
					if(vo.getStudyRoomState().equals("0")) {
						System.out.println("현재 상태가 0이다. 바꿔주면 1로 바뀌어야한다.");
						System.out.println("현재 상태 : " + vo.getStudyRoomState());
					
						ImageView image = (ImageView) event.getTarget(); // 현재 눌린 이미지
						vo.setStudyRoomState("1"); // 이미지 상태를 바꿔 주기
						String subId = image.getId().substring(image.getId().lastIndexOf("m") + 1); // 눌린 이미지의 숫자 가져오기
						System.out.println("subId : " + subId); 
						vo.setStudyRoomNum(Integer.parseInt(subId)); // 스터디룸 번호 지정
						
						System.out.println("dao에 들어가기 전 : getStudyRoomNum : " + vo.getStudyRoomNum()); // 항상 6
						System.out.println("dao에 들어가기 전 : getStudyRoomState : "+vo.getStudyRoomState());
						
						System.out.println("ID : " + image.getId());
						
						// 로그인 유저를 넣어서 스터디 룸 대여 바꾸기
						rentalVO.setStudyRoomRentalNum(vo.getStudyRoomNum());
						rentalVO.setStudyRoomNum(vo.getStudyRoomNum());
						rentalVO.setMemNum(vovo.getMemNum());
						try {// 여기에서 업데이트
							service.updateStudyRoomRental(rentalVO);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						
						
						
						try {
							System.out.println("0 -> 1 : " + service.updateStudyRoomReserve_Cancel(vo));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Image imageId = new Image("file:src/images/" + image.getId() + ".png"); // 이미지 객체 생성
						image.setImage(imageId); // 이미지 처리
						
						
						
					}else if(vo.getStudyRoomState().equals("1")){
						System.out.println("현재 상태가 1이다. 바꿔주면 0로 바뀌어야한다.");
						System.out.println("현재 상태 : " + vo.getStudyRoomState());
					
						ImageView image = (ImageView) event.getTarget(); // 현재 눌린 이미지뷰는?
						vo.setStudyRoomState("0"); // 스터디룸의 상태 변경해주기
						String subId = image.getId().substring(image.getId().lastIndexOf("m") + 1); // 눌린 이미지의 숫자 가져오기
						System.out.println("subId : " + subId); 
						vo.setStudyRoomNum(Integer.parseInt(subId)); // 스터디룸 번호 지정
						
						System.out.println("dao에 들어가기 전 : getStudyRoomNum : " + vo.getStudyRoomNum()); // 항상 6
						System.out.println("dao에 들어가기 전 : getStudyRoomState : "+vo.getStudyRoomState());
						

						System.out.println("ID : " + image.getId());
						
						// 로그인 유저를 넣어서 스터디 룸 대여 바꾸기
						rentalVO.setStudyRoomRentalNum(vo.getStudyRoomNum());
						rentalVO.setStudyRoomNum(vo.getStudyRoomNum());
						rentalVO.setMemNum(vovo.getMemNum());
						System.out.println(vovo.getMemNum());
						
						// 나의 스터디룸에 정보 넣기
						MyStudyRoomVO myStudyRoomVO = new MyStudyRoomVO();
						myStudyRoomVO.setMyStudyRoomNum(vo.getStudyRoomNum());
						myStudyRoomVO.setStudyRoomNum(vo.getStudyRoomNum());
						myStudyRoomVO.setStudyRoomRentalNum(vo.getStudyRoomNum());
						
						try {// 여기에서 업데이트
							service.updateStudyRoomRental(rentalVO);
							service.updateMyStudyRoom(myStudyRoomVO); // 나의 스터디룸에 정보 넣기
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							System.out.println("1 -> 0 : " + service.updateStudyRoomReserve_Cancel(vo));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Image imageId = new Image("file:src/images/" + image.getId() + "_1.png"); // 이미지 객체 생성
						image.setImage(imageId); // 이미지 처리
						
						
						

					}
					
					
					
					
					
					
				}// handle
				
			});// setOnMouseClicked
		}


	}

}
