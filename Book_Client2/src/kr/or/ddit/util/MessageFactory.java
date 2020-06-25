package kr.or.ddit.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MessageFactory {

    /**
     *
     * @param title 글 머리 제목
     * @param headerText
     * @param msg 메시지
     * @return 
     */
    public static ButtonType ReturninfoMsg(String title, String headerText, String msg) {
    	Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(headerText);
        info.setContentText(msg);
        return info.showAndWait().get();
    }
    
    public static void infoMsg(String title, String headerText, String msg) {
        Alert errAlert = new Alert(Alert.AlertType.INFORMATION);
        errAlert.setTitle(title);
        errAlert.setHeaderText(headerText);
        errAlert.setContentText(msg);
        errAlert.showAndWait();
    }
    
    public static void errMsg(String title, String headerText, String msg) {
        Alert errAlert = new Alert(Alert.AlertType.ERROR);
        errAlert.setTitle(title);
        errAlert.setHeaderText(headerText);
        errAlert.setContentText(msg);
        errAlert.showAndWait();
    }


}
