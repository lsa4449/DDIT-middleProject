package kr.or.ddit.service.study;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.study.IStudyDao;
import kr.or.ddit.dao.study.StudyDaoImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class StudyServiceImpl extends UnicastRemoteObject implements IStudyService{
	
	private IStudyDao dao;
	
	private static IStudyService service;
	
	private StudyServiceImpl() throws RemoteException {
		super();
		dao = StudyDaoImpl.getInstance();
	}
	
	public static IStudyService getInstance() throws RemoteException {
		if(service== null) {
			service = new StudyServiceImpl();
		}
		return service;
	}

	@Override
	public List<BoardVO> selectStudy(String boardTitle) throws RemoteException {
		
		return dao.selectStudy(boardTitle);
	}

	@Override
	public List<BoardVO> BoardStudy() throws RemoteException {
		
		return dao.BoardStudy();
	}

	@Override
	public BoardVO detailStudy(int cnt) throws RemoteException {
		
		return dao.detailStudy(cnt);
	}

	@Override
	public int insertStudy(BoardVO boardVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertStudy(boardVo);
	}

	@Override
	public int updateStudy(BoardVO boardVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateStudy(boardVo);
	}

	@Override
	public int deleteStudy(int boardNum) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deleteStudy(boardNum);
	}

	/**
	 * 조회수 증가
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int upboardCount(BoardVO vo) throws RemoteException{
		return dao.upboardCount(vo);
	}

}
