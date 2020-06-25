package kr.or.ddit.vochat;
import java.io.Serializable;

public class CreateRoomData extends SendData implements Serializable{
	
	private static final long serialVersionUID = 8747297324051364160L;
	
	String  roomname;

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public CreateRoomData(String roomName) { // 생성자 변경
		super();
		this.roomname= roomName;
	}
	
}
