package kr.or.ddit.service.monthBook;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.monthBook.IMonthBookDao;
import kr.or.ddit.dao.monthBook.MonthBookDaoImpl;
import kr.or.ddit.vo.MonthBookVO;
import kr.or.ddit.vo.MonthFileVO;

public class MonthBookServiceImpl extends UnicastRemoteObject implements IMonthBookService{
	
	private IMonthBookDao dao; 
	private static IMonthBookService service;
	
	protected MonthBookServiceImpl() throws RemoteException {
		super();
		dao = MonthBookDaoImpl.getInstance();
	}
	
	public static IMonthBookService getInstance() throws RemoteException {
		if(service== null) {
			service = new MonthBookServiceImpl();
		}
		return service;
	}

	@Override
	public List<MonthBookVO> selectMonthBookAll() throws RemoteException {
		return dao.selectMonthBookAll();
	}

	@Override
	public int insertMonthBook(MonthBookVO mVo) throws RemoteException {
		return dao.insertMonthBook(mVo);
	}

	@Override
	public MonthFileVO selectFiles(int num) throws RemoteException {
		return dao.selectFiles(num);
	}

	@Override
	public int insertfiles(MonthFileVO vo) throws RemoteException {
		return dao.insertfiles(vo);
	}

	@Override
	public int deleteMonthBook(int monthBookNum) throws RemoteException {
		return dao.deleteMonthBook(monthBookNum);
	}

}
