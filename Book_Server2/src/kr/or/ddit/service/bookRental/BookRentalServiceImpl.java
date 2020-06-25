package kr.or.ddit.service.bookRental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.bookRental.BookRentalDaoImpl;
import kr.or.ddit.dao.bookRental.IBookRentalDao;
import kr.or.ddit.vo.BookRentalVO;

public class BookRentalServiceImpl extends UnicastRemoteObject implements IBookRentalService {

	private IBookRentalDao dao;
	private static IBookRentalService service;

	protected BookRentalServiceImpl() throws RemoteException {
		super();
		dao = BookRentalDaoImpl.getInstance();
	}

	public static IBookRentalService getInstance() throws RemoteException {
		if (service == null) {
			service = new BookRentalServiceImpl();
		}
		return service;
	}

	@Override
	public List<BookRentalVO> selectAllBookRental() throws RemoteException{
		return dao.selectAllBookRental();
	}
	
	@Override
	public int insertBookRental(BookRentalVO vo) throws RemoteException {
		return dao.insertBookRental(vo);
	}

}
