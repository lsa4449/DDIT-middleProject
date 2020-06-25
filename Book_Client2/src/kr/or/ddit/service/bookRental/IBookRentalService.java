package kr.or.ddit.service.bookRental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BookRentalVO;

public interface IBookRentalService extends Remote {
	
	public List<BookRentalVO> selectAllBookRental() throws RemoteException;
	
	//도서 대여 테이블에 정보 삽입
	public int insertBookRental(BookRentalVO vo) throws RemoteException;
	
}
