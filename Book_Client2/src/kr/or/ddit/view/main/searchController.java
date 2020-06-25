package kr.or.ddit.view.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.ParserAdapter;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.SearchVO;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class Item {
	public String Title = "";
	public String Link = "";
}

class AladdinOpenAPIHandler extends DefaultHandler {
	public List<Item> Items;
	private Item currentItem;
	private boolean inItemElement = false;
	private String tempValue;

	public AladdinOpenAPIHandler() {
		Items = new ArrayList<Item>();
	}

	public void startElement(String namespace, String localName, String qName, Attributes atts) {
		if (localName.equals("item")) {
			currentItem = new Item();
			inItemElement = true;
		} else if (localName.equals("title")) {
			tempValue = "";
		} else if (localName.equals("link")) {
			tempValue = "";
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempValue = tempValue + new String(ch, start, length);
	}

	public void endElement(String namespaceURI, String localName, String qName) {
		if (inItemElement) {
			if (localName.equals("item")) {
				Items.add(currentItem);
				currentItem = null;
				inItemElement = false;
			} else if (localName.equals("title")) {
				currentItem.Title = tempValue;
			} else if (localName.equals("link")) {
				currentItem.Link = tempValue;
			}
		}
	}

	public void parseXml(String xmlUrl) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		ParserAdapter pa = new ParserAdapter(sp.getParser());
		pa.setContentHandler(this);
		pa.parse(xmlUrl);
	}
}

public class searchController implements Initializable {
	
	@FXML
	ImageView search;
	@FXML
	JFXTextField text;
	@FXML
	TableView<BookVO> table;
	@FXML
	TableColumn<BookVO, String> title;
	
	ObservableList<BookVO> tv;
	
	private static final String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?";

	public static String GetUrl(String searchWord) throws Exception {
		Map<String, String> hm = new HashMap<String, String>();
		hm.put("ttbkey", "ttblsa44491923001");
		hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
		hm.put("QueryType", "Title");
		hm.put("MaxResults", "30");
		hm.put("start", "1");
		hm.put("SearchTarget", "Book");
		hm.put("output", "xml");

		StringBuffer sb = new StringBuffer();
		Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String val = hm.get(key);
			sb.append(key).append("=").append(val).append("&");
		}
		System.out.println("sb : " + sb);
		System.out.println(BASE_URL + sb.toString());
		return BASE_URL + sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String url = GetUrl("고양이");
		AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
		api.parseXml(url);
		for (Item item : api.Items) {
			System.out.println(item.Title);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		title.setCellValueFactory(new PropertyValueFactory<>("bookName"));

		Image image1 = new Image("file:src/images/search.png");
		search.setImage(image1);
		tv = FXCollections.observableArrayList();
		
		search.setOnMouseClicked(e -> {

			String url = null;
			try {
				url = GetUrl(text.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
				try {
					api.parseXml(url);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
				tv.clear();
				
			for (Item item : api.Items) {
				System.out.println(item.Title);
				
				BookVO vo = new BookVO();
				vo.setBookName(item.Title);
				
				tv.addAll(vo);
				table.setItems(tv);
				
				
			}
			
			System.out.println(tv.size());
		});
	}
}
