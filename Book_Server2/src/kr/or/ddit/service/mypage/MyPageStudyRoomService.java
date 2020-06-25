package kr.or.ddit.service.mypage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.mypage.IMyPageStudyRoomDao;
import kr.or.ddit.dao.mypage.MyPageStudyRoomDaoImpl;
import kr.or.ddit.vo.MyStudyRoomSelect_JoinVO;

public class MyPageStudyRoomService extends UnicastRemoteObject implements IMyPageStudyRoomService{

	
	private IMyPageStudyRoomDao dao;
	private static IMyPageStudyRoomService service;
	
	private MyPageStudyRoomService() throws RemoteException{
		dao = MyPageStudyRoomDaoImpl.getInstance();
	}
	
	public static IMyPageStudyRoomService getInstance() throws RemoteException{
		if(service == null) {
			service = new MyPageStudyRoomService();
		}
		return service;
	}
	
	
	@Override
	public List<MyStudyRoomSelect_JoinVO> selectMyStudyRoom(int Num) throws RemoteException {

		return dao.selectMyStudyRoom(Num);
	}

}
