package kr.or.ddit.service.studyroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.studyroom.IStudyRoomDao;
import kr.or.ddit.dao.studyroom.StudyRoomDaoImpl;
import kr.or.ddit.vo.MyStudyRoomVO;
import kr.or.ddit.vo.StudyRoomRentalVO;
import kr.or.ddit.vo.StudyRoomVO;

public class StudyRoomServiceImpl extends UnicastRemoteObject implements IStudyroomService{

	// StudyRoomServiceImpl에서 쓸 값을 가져오기 위해 dao 변수 선언
	private IStudyRoomDao dao; 
	private static IStudyroomService service;
	
	private StudyRoomServiceImpl() throws RemoteException{
		dao = StudyRoomDaoImpl.getInstance();
	}
	
	public static IStudyroomService getInstance() throws RemoteException{
		if(service == null) {
			service = new StudyRoomServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<StudyRoomVO> selectStudyRoom() throws RemoteException {
		return dao.selectStudyRoom();
	}

	@Override
	public int updateStudyRoomReserve_Cancel(StudyRoomVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateStudyRoomReserve_Cancel(vo);
	}

	@Override
	public int updateStudyRoomRental(StudyRoomRentalVO studyRoomRentalVO) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateStudyRoomRental(studyRoomRentalVO);
	}

	@Override
	public int updateMyStudyRoom(MyStudyRoomVO vo) throws RemoteException {
		
		return dao.updateMyStudyRoom(vo);
	}

	
}
