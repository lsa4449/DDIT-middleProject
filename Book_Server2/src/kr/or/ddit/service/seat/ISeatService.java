package kr.or.ddit.service.seat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.SeatRentalVO;
import kr.or.ddit.vo.SeatVO;

public interface ISeatService extends Remote{

	/**
	 * 현재 좌석에 대한 정보를 가져온다.
	 * @return List<SeatVO>
	 * @throws RemoteException
	 */
	public List<SeatVO> selectCurrentStatus() throws RemoteException;				
	
	/**
	 * 대여 좌석 테이블을 SeatRentalVO로 읽어서 memNum이 자신과 같으면 예약할 수 없다는 Alert 창을 띄운다.
	 * @param memNum
	 * @return SeatRentalVO
	 * @throws RemoteException
	 */
	public SeatRentalVO selectMemberNum(int memNum) throws RemoteException;
	
	/**
	 * 취소 버튼을 클릭하면 모두 취소 되도록
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateCancleRentalSeat(SeatRentalVO tempSeatRentalVO) throws RemoteException;
	
	/**
	 * 좌석을 예약한다.
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateRentalSeat(SeatRentalVO tempSeatRentalVO) throws RemoteException;
	
	/**
	 * 좌석의 상태를 업데이트 한다.
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateSeatStatus(SeatVO tempSeatRentalVO) throws RemoteException;
	
	
	
	
}
