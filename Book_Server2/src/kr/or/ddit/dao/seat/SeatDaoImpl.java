package kr.or.ddit.dao.seat;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.SeatRentalVO;
import kr.or.ddit.vo.SeatVO;

public class SeatDaoImpl implements ISeatDao{
	
	private SqlMapClient smc;
	
	private static ISeatDao dao;
	
	private SeatDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ISeatDao getInstance() {
		if(dao == null) {
			dao = new SeatDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertSeatRental(SeatRentalVO seatRentalVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSeat(String seatState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SeatVO> selectCurrentStatus(){
		List<SeatVO> list = null;
		
		try {
			list = smc.queryForList("seat.selectCurrentStatus");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public SeatRentalVO selectMemberNum(int memNum) {
		SeatRentalVO vo = null;
		
		try {
			vo = (SeatRentalVO) smc.queryForObject("seat.selectMemberNum", memNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int updateCancleRentalSeat(SeatRentalVO tempSeatRentalVO) {
		int cnt = 0;
		try {
			cnt = smc.update("seat.updateCancleRentalSeat", tempSeatRentalVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRentalSeat(SeatRentalVO tempSeatRentalVO) {
		int cnt = 0;
		try {
			cnt = smc.update("seat.updateRentalSeat", tempSeatRentalVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateSeatStatus(SeatVO tempSeatRentalVO) {
		int cnt = 0;
		
		try {
			cnt = smc.update("seat.updateSeatStatus", tempSeatRentalVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

}
