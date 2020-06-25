package kr.or.ddit.vochat;

import java.io.Serializable;

public class EnterRoomData extends SendData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4922231538744733483L;
	
	int roomID;

	public EnterRoomData() {
	}
	
	
	public EnterRoomData(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomID() {
		return roomID;
	}


	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
}
