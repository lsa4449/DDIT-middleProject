package kr.or.ddit.view.admin;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class memStopController implements Initializable {


    @FXML
    private Button nomal_btn;

    @FXML
    private Button good_btn;

    @FXML
    private Button warning_btn;

    @FXML
    private Button stop_btn;
    
    @FXML
    private Button cancel_btn;

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	
	//회원 상태 정지
	stop_btn.setOnMouseClicked(e->{
		int cnt=0;
		try {
			cnt = memManageController.adminService.updateMemberStateStop(AdminData.vo.getMemNum());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage stage = (Stage) stop_btn.getScene().getWindow();
		stage.close();
	});
	
	//회원 상태 일반
		nomal_btn.setOnMouseClicked(e->{
			int cnt=0;
			try {
				cnt = memManageController.adminService.updateMemberStateNomal(AdminData.vo.getMemNum());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) stop_btn.getScene().getWindow();
			stage.close();
		});
		
		//회원 상태 경고
		warning_btn.setOnMouseClicked(e->{
			int cnt=0;
			try {
				cnt = memManageController.adminService.updateMemberStateWarning(AdminData.vo.getMemNum());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) stop_btn.getScene().getWindow();
			stage.close();
		});
		
		//회원 상태 우수
		good_btn.setOnMouseClicked(e->{
			int cnt=0;
			try {
				cnt = memManageController.adminService.updateMemberStateGood(AdminData.vo.getMemNum());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) stop_btn.getScene().getWindow();
			stage.close();
		});
	

	
	//취소창누르면 걍 창닫히기
	cancel_btn.setOnMouseClicked(e->{
		Stage stage = (Stage) cancel_btn.getScene().getWindow();
		stage.close();
	});
	
}
}
