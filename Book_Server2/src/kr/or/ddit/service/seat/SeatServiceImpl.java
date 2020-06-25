package kr.or.ddit.service.seat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.seat.ISeatDao;
import kr.or.ddit.dao.seat.SeatDaoImpl;
import kr.or.ddit.vo.SeatRentalVO;
import kr.or.ddit.vo.SeatVO;

public class SeatServiceImpl extends UnicastRemoteObject implements ISeatService{
	
	private ISeatDao dao;
	
	private static ISeatService service;
	
	private SeatServiceImpl() throws RemoteException{
		dao = SeatDaoImpl.getInstance();
	}
	
	public static ISeatService getInstance() throws RemoteException{
		if(service == null) {
			service = new SeatServiceImpl();
		}
		return service;
	}

	@Override
	public List<SeatVO> selectCurrentStatus() throws RemoteException {

		return dao.selectCurrentStatus();
	}

	@Override
	public SeatRentalVO selectMemberNum(int memNum) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectMemberNum(memNum);
	}

	@Override
	public int updateCancleRentalSeat(SeatRentalVO tempSeatRentalVO) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateCancleRentalSeat(tempSeatRentalVO);
	}

	@Override
	public int updateRentalSeat(SeatRentalVO tempSeatRentalVO) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateRentalSeat(tempSeatRentalVO);
	}

	@Override
	public int updateSeatStatus(SeatVO tempSeatRentalVO) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.updateSeatStatus(tempSeatRentalVO);
	}

}
