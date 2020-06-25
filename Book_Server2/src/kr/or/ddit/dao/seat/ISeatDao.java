package kr.or.ddit.dao.seat;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.SeatRentalVO;
import kr.or.ddit.vo.SeatVO;

public interface ISeatDao {
	
	/**
	 * 좌석 대여 (밑에 좌석 테이블 상태 수정은 콤보로 해야됌)
	 * 대여 테이블에 VO insert, 좌석 테이블에 해당 좌석번호 상태 바꾸기
	 * @param SeatRentalVO
	 * @return 성공시 null값 반환, int cnt변수로 반환값 재설정
	 */
	public int insertSeatRental(SeatRentalVO seatRentalVO);
	
	/**
	 * 좌석 대여시 좌석 테이블 상태 수정 (좌석 대여시 insertSeatRental 메소드와 한쌍)
	 * @param String seatState
	 * @return 성공시 1반환, 실패시0 반환
	 */
	public int updateSeat(String seatState);
	
	
	/**
	 * 좌석 연장
	 * @param 
	 * 
	 */
	
	/**
	 * 좌석 취소
	 */
	
	/**
	 * 내가 대여중인 좌석 보기
	 */
	
	/**
	 * 대여중인 전체 좌석 보기
	 */
	
	
	/**
	 * 현재 좌석에 대한 정보를 가져온다.
	 * @return List<SeatVO>
	 * @throws RemoteException
	 */
	public List<SeatVO> selectCurrentStatus();	
	
	
	/**
	 * 대여 좌석 테이블을 SeatRentalVO로 읽어서 memNum이 자신과 같으면 예약할 수 없다는 Alert 창을 띄운다.
	 * @param memNum
	 * @return SeatRentalVO
	 * @throws RemoteException
	 */
	public SeatRentalVO selectMemberNum(int memNum);
	
	/**
	 * 취소 버튼을 클릭하면 모두 취소 되도록
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateCancleRentalSeat(SeatRentalVO tempSeatRentalVO);
	
	/**
	 * 좌석을 예약한다.
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateRentalSeat(SeatRentalVO tempSeatRentalVO);
	
	/**
	 * 좌석의 상태를 업데이트 한다.
	 * @param tempSeatRentalVO
	 * @return
	 * @throws RemoteException
	 */
	public int updateSeatStatus(SeatVO tempSeatRentalVO);
	
}
