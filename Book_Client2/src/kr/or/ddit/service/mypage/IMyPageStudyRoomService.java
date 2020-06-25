package kr.or.ddit.service.mypage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MyStudyRoomSelect_JoinVO;

public interface IMyPageStudyRoomService extends Remote{
	
	/**
	 * join을 사용하여 내가 예약한 스터디룸의 목록을 확인 할 수 있다.
	 * @param MyStudyRoomSelect_JoinVO vo
	 * @return List<MyStudyRoomSelect_JoinVO>
	 */
	public List<MyStudyRoomSelect_JoinVO> selectMyStudyRoom(int Num) throws RemoteException; 
}
