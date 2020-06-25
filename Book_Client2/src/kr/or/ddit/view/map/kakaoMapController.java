package kr.or.ddit.view.map;

import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class kakaoMapController implements Initializable {

    public WebView WebView_Fx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = WebView_Fx.getEngine();
//        webEngine.load("http://localhost:8181/Book_Client/kakaoMap.html");
        webEngine.load("https://www.google.com/maps/d/edit?mid=1nI7H6wScebk23KpCrdCj9Q52nvieiX2F&ll=35.63700755911095%2C127.0955535&z=7");
    }
}
