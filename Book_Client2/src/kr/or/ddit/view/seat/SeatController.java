package kr.or.ddit.view.seat;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.service.seat.ISeatService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SeatRentalVO;
import kr.or.ddit.vo.SeatVO;
import javafx.scene.control.Label;

public class SeatController implements Initializable {

	@FXML
	private ImageView seat3;

	@FXML
	private ImageView seat4;

	@FXML
	private ImageView seat1;

	@FXML
	private ImageView seat2;

	@FXML
	private ImageView seat8;

	@FXML
	private ImageView seat7;

	@FXML
	private ImageView seat6;

	@FXML
	private ImageView seat5;

	@FXML
	private ImageView seat12;

	@FXML
	private ImageView seat11;

	@FXML
	private ImageView seat10;

	@FXML
	private ImageView seat9;

	@FXML
	private ImageView seat16;

	@FXML
	private ImageView seat15;

	@FXML
	private ImageView seat14;

	@FXML
	private ImageView seat13;

	@FXML
	private ImageView seat20;

	@FXML
	private ImageView seat19;

	@FXML
	private ImageView seat18;

	@FXML
	private ImageView seat17;

	@FXML
	private ImageView seat24;

	@FXML
	private ImageView seat23;

	@FXML
	private ImageView seat22;

	@FXML
	private ImageView seat21;

	@FXML
	private ImageView seat28;

	@FXML
	private ImageView seat27;

	@FXML
	private ImageView seat26;

	@FXML
	private ImageView seat25;

	@FXML
	private ImageView seat39;

	@FXML
	private ImageView seat40;

	@FXML
	private ImageView seat37;

	@FXML
	private ImageView seat38;

	@FXML
	private ImageView seat32;

	@FXML
	private ImageView seat31;

	@FXML
	private ImageView seat30;

	@FXML
	private ImageView seat29;

	@FXML
	private ImageView seat36;

	@FXML
	private ImageView seat35;

	@FXML
	private ImageView seat34;

	@FXML
	private ImageView seat33;

	@FXML
	private Button btnResult;

	@FXML
	private Button btnCancle;

	@FXML Label mySeat;
	
	ImageView[] imageArr = new ImageView[40];

	Registry reg;
	ISeatService service;
	
	MemberVO vo;
	SeatRentalVO seatRentalVO;
	
	boolean oneCheck = true;
	
	String clickedCurrentImage = "";
	
	SeatVO seatVO;
	
	String[] seat_status;
	
	boolean checkOfImageView = false;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (ISeatService) reg.lookup("SeatService");
			System.out.println("SeatController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		imageArr = new ImageView[] { seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11,
				seat12, seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22, seat23, seat24,
				seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32, seat33, seat34, seat35, seat36, seat37,
				seat38, seat39, seat40 };
		
		seatRentalVO = new SeatRentalVO();
		vo = new MemberVO();
		vo = Session.loginUser;
		try {
			seatRentalVO = service.selectMemberNum(vo.getMemNum());
			
			if(seatRentalVO == null) {
				seatRentalVO = new SeatRentalVO();
				seatRentalVO.setMemNum(0);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println(vo.getMemNum());
		if(vo.getMemNum() == seatRentalVO.getMemNum()) {
			mySeat.setText(Integer.toString(seatRentalVO.getSeatNum()));
		}
		
		List<SeatVO> seatList = new ArrayList<SeatVO>(); // seat 테이블의 정보를 가져오기 위해 객체 생성

		try {
			seatList = service.selectCurrentStatus();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 좌석의 상태에 대해서 이미지뷰 객체에 값을 저장한다.
		for (int i = 0; i < imageArr.length; i++) {
			imageArr[i].setUserData(seatList.get(i).getSeatState());
			System.out.println("imageArr[" + i + "] : " + imageArr[i].getUserData());
		}
		
		seat_status = new String[imageArr.length]; // 내가 이 페이지에서 고른 상태를 저장한다.
		
		for (int i = 0; i < seat_status.length; i++) {
			seat_status[i] = seatList.get(i).getSeatState();
		} // 현재 어떤 값이 들어 있는지 저장

		updateSeatView(); // 현재 상태 보여주기
		
		vo = new MemberVO();
		// 로그인
		vo = Session.loginUser;
		
		// 어떤 imageView가 클릭되었는지 알게 하는 것 // 한개만 클릭 되도록 한다.
		for (int i = 0; i < imageArr.length; i++) {
			
			imageArr[i].setOnMouseClicked(e->{
				ImageView imageTarget = (ImageView) e.getTarget();
				String imageId = imageTarget.getId();
				
				clickedCurrentImage = imageId;
					if (imageTarget.getUserData().equals("0") && oneCheck) {
						oneCheck = false;
						imageTarget.setUserData("1");
						System.out.println("imageTarget.setUserData(1) : " + imageTarget.getUserData());
						
					} else if (imageTarget.getUserData().equals("1") && !oneCheck) {
						oneCheck = true;
						imageTarget.setUserData("0");
						System.out.println("imageTarget.setUserData(0) : " + imageTarget.getUserData());
					}else {
						errMsg("오류", "좌석은 한개만 선택이 가능합니다.");
					}

				
				System.out.println("=====================================================");
				
				
				updateCurrentStatusImage();
			});
		}
		
		// 예약버튼 -> 한 아이디는 하나의 좌석만 예약이 된다.
		btnResult.setOnAction(e->{
			// 현재 로그인된 회원번호가 있으면 예약을 할수 없게 한다. if 문을 사용한다.
			boolean check = true;
			try {
				seatRentalVO = service.selectMemberNum(vo.getMemNum());
				
				if(seatRentalVO == null) {
					seatRentalVO = new SeatRentalVO();
					seatRentalVO.setMemNum(0);
				}
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			
			// 예약을 하려면 취소 버튼을 눌러야한다.
			System.out.println(vo.getMemNum());
			if(vo.getMemNum() == seatRentalVO.getMemNum()) { // 비교하고 값이 있으면  alert 창을 띄우고 return; 
				errMsg("오류", "이미 예약한 좌석이 있습니다.");
				return;
				
			}else {
				SeatRentalVO tempSeatRentalVO = new SeatRentalVO();
				String temp = clickedCurrentImage.substring(clickedCurrentImage.lastIndexOf("t") + 1);
				tempSeatRentalVO.setSeatNum(Integer.parseInt(temp));
				tempSeatRentalVO.setMemNum(vo.getMemNum());
				seatVO = new SeatVO();
				seatVO.setSeatNum(Integer.parseInt(temp));
				seatVO.setSeatState("1");
				
				
				int cnt = 0;
				//좌석을 예매하는 쿼리 작성
				try {
					cnt = service.updateRentalSeat(tempSeatRentalVO); // 대여 테이블 업데이트
					System.out.println("tempSeatRentalVO" + tempSeatRentalVO.getSeatNum());
					cnt = service.updateSeatStatus(seatVO); // 좌석 상태 테이블 업데이트
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(cnt == 1) {
					okMsg("성공", "좌석 예매 성공 ~~");
					mySeat.setText(temp);
				}else {
					errMsg("실패", "좌석 예매 실패 !!");
				}
				
				updateSeatView(); // 현재 상태 보여주기
			}
			
			
			
			
		});
		
		// 취소버튼 -> 취소 버튼을 누르면 현재 자신의 예약된 좌석을 취소한다. 
		btnCancle.setOnAction(e->{
			// 취소 버튼을 누르면 현재 좌석 상태를 0으로 바꾼다.
			// 현재 멤버가 가지고 있는 좌석번호를 조회하고, 좌석번호의 상태를 0 으로 초기화 시킨다.
			SeatRentalVO selectSeatVORentalUser = new SeatRentalVO();
			try {
				selectSeatVORentalUser = service.selectMemberNum(vo.getMemNum());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			System.out.println(selectSeatVORentalUser.toString());
			SeatRentalVO tempSeatRentalVO = new SeatRentalVO();
			String temp = clickedCurrentImage.substring(clickedCurrentImage.lastIndexOf("t") + 1);
			tempSeatRentalVO.setSeatNum(selectSeatVORentalUser.getSeatNum()); // TODO
			seatVO = new SeatVO();
			seatVO.setSeatNum(selectSeatVORentalUser.getSeatNum());
			seatVO.setSeatState("0");
			int cnt = 0;
			//좌석을 취소하는 쿼리 작성
			try {
				cnt = service.updateCancleRentalSeat(tempSeatRentalVO);
				cnt = service.updateSeatStatus(seatVO);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(cnt == 1) {
				okMsg("성공", "좌석 취소 성공~~");
				mySeat.setText("없습니다.");
			}else {
				errMsg("실패", "좌석 취소 실패 !!");
			}
			updateSeatView(); // 현재 상태 보여주기
			
		});

	}// initialize
	
///////////////////////////////////////////////////////////////////////////////////////	
	// 자리상태 새로 고침
	private void updateSeatView() {
		
		
		List<SeatVO> seatList = new ArrayList<SeatVO>();
		
		try {
			seatList = service.selectCurrentStatus();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 현재 상태 보여주기
				for (int i = 0; i < imageArr.length; i++) {
					SeatVO seat = seatList.get(i);
					System.out.println(seat.getSeatState() + " " + seat.getSeatNum());
					if (seat.getSeatState().equals("1")) {
						Image image = new Image("file:src/images/blackSeat/darkSeat" + (i + 1) + ".png");
						imageArr[i].setImage(image);
					} else {
						Image image = new Image("file:src/images/whiteSeat/whiteSeat" + (i + 1) + ".png");
						imageArr[i].setImage(image);
					}
				} // for
	}// updateView
	
	// 오류 메시지 창
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}
	
	// 성공 메시지 창
		private void okMsg(String headerText, String msg) {
			Alert errAlert = new Alert(AlertType.INFORMATION);
			errAlert.setTitle("성공~~");
			errAlert.setHeaderText(headerText);
			errAlert.setContentText(msg);
			errAlert.showAndWait();

		}
		
		private void updateCurrentStatusImage() {
			
			
			for (int i = 0; i < imageArr.length; i++) {
				if (imageArr[i].getUserData().equals("0")) {
					Image image = new Image("file:src/images/whiteSeat/whiteSeat" + (i + 1) + ".png");
					imageArr[i].setImage(image);
				}else if(imageArr[i].getUserData().equals("1")){
					Image image = new Image("file:src/images/blackSeat/darkSeat" + (i + 1) + ".png");
					imageArr[i].setImage(image);
				}
			}
		}

}
