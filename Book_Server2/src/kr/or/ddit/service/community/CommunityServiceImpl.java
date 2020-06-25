package kr.or.ddit.service.community;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.community.CommunityDaoImpl;
import kr.or.ddit.dao.community.ICommunityDao;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.RippleVO;

public class CommunityServiceImpl extends UnicastRemoteObject implements ICommunityService{

	private ICommunityDao dao;
	
	private static ICommunityService service;
	
	private CommunityServiceImpl() throws RemoteException {
		super();
		dao = CommunityDaoImpl.getInstance();
	}
	
	public static ICommunityService getInstance() throws RemoteException {
		if(service== null) {
			service = new CommunityServiceImpl();
		}
		return service;
	}

	@Override
	public List<BoardVO> selectBoard(String boardTitle) throws RemoteException {
		
		return dao.selectBoard(boardTitle);
	}

	@Override
	public List<BoardVO> Board() throws RemoteException {
		
		return dao.Board();
	}
	@Override
	public BoardVO detailBoard(int cnt) throws RemoteException {
		
		return dao.detailBoard(cnt);
	}

	@Override
	public int insertBoard(BoardVO boardVo) throws RemoteException {
	
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) throws RemoteException {
		
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNum) throws RemoteException {
		
		return dao.deleteBoard(boardNum);
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
