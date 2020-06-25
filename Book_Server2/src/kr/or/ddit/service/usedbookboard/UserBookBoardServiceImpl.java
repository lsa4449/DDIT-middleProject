package kr.or.ddit.service.usedbookboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.usedbookboard.IUserBookBoardDao;
import kr.or.ddit.dao.usedbookboard.UsedBookBoardDaoImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.PriceVO;

public class UserBookBoardServiceImpl extends UnicastRemoteObject implements IUserBookBoardService{

	private IUserBookBoardDao dao;
	private static IUserBookBoardService service;
	
	private UserBookBoardServiceImpl() throws RemoteException {
		super();
		dao = UsedBookBoardDaoImpl.getInstance();
	}
	
	public static IUserBookBoardService getInstance() throws RemoteException{
		if(service == null) {
			service = new UserBookBoardServiceImpl();
		}
		return service;
	}

	@Override
	public List<BoardVO> selectAll(int num) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectAll(num);
	}

	@Override
	public List<BoardVO> selectTitleUserBookBoard(BoardVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectTitleUserBookBoard(vo);
	}

	@Override
	public List<BoardVO> selectMemUserBookBuyBoard(String str) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectMemUserBookBuyBoard(str);
	}

	@Override
	public List<BoardVO> selectMemUserBookCellBoard(String str) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectMemUserBookCellBoard(str) ;
	}

	@Override
	public List<FilesVO> selectFile(int num) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectFile(num);
	}

	@Override
	public int insertUserBookBoard(BoardVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.insertUserBookBoard(vo);
	}
	
	public int searchboardNum() throws RemoteException{
		return dao.searchboardNum();
	}

	@Override
	public int insertfile(FilesVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.insertfile(vo) ;
	}

	@Override
	public int deleteUsedBookBoard(int num) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.deleteUsedBookBoard(num) ;
	}

	@Override
	public int deletefile(int num) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.deletefile(num);
	}

	@Override
	public int updateUserBookBoard(BoardVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.updateUserBookBoard(vo);
	}

	@Override
	public int updatefile(FilesVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		return dao.updatefile(vo);
	}

	@Override
	public BoardVO detailUserBookBoard(BoardVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.detailUserBookBoard(vo);
	}

	@Override
	public int upboardCount(BoardVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.upboardCount(vo);
	}

	@Override
	public int insertBookBoardBuyPrice(PriceVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertBookBoardBuyPrice(vo);
	}

	@Override
	public PriceVO selectPrice(int num) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectPrice(num);
	}

	@Override
	public int deletePrice(int num) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.deletePrice(num);
	}
}
