package kr.or.ddit.dao.studyroom;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MyStudyRoomVO;
import kr.or.ddit.vo.StudyRoomRentalVO;
import kr.or.ddit.vo.StudyRoomVO;

public interface IStudyRoomDao {
	
	/**
	 * 스터디룸 예약 현황 (관리자)
	 * @param StudyRoomVO
	 * @author sua
	 */
	public int reserStudyRoom(String studyRoomReserState);
	
	/**
	 * 스터디룸 이용 현황 (관리자)
	 * @param StudyRoomVO
	 * @author sua
	 */
	public int useStudyRoom(String studyRoomState);
	
	/**
	 * 스터디룸 취소 
	 * @param StudyRoomVO
	 * @author sua
	 */
	public int cancelStudyRoom(String studyRoomState);
	
	/**
	 * 전체 스터디룸의 상태를 가져오기 위해서 사용한다. PK : 스터디룸 관리 번호(NUMBER) | 스터디룸 상태(char(1)), 예약 여부(char(1))
	 * @return List<StudyRoomVO>
	 * @throws RemoteException
	 */
	public List<StudyRoomVO> selectStudyRoom();
	
	/**
	 * 스터디룸의 상태를 클릭했을 때 바꾼다. vo에 studyroom번호 값과 바꿀 상태를 넣어준다(studyroomstate) 
	 * @return int
	 * @throws RemoteException
	 */
	public int updateStudyRoomReserve_Cancel(StudyRoomVO vo);
	
	/**
	 * 스터디룸 대여메소드이다. 로그인 유저의 정보로 같이 넘겨서 어떤 회원이 대여 했는지 알 수 있도록 한다. 
	 * @param vo
	 * @return int형으로 1이 나와야 한다.
	 * @throws RemoteException
	 */
	public int updateStudyRoomRental(StudyRoomRentalVO studyRoomRentalVO);
	
	/**
	 * 나의 페이지에서 사용될 MyStudyRoomVO이다. 
	 * @param vo, 나의 스터디룸 번호, 대여번호(FK), 스터디룸 관리 번호 (FK)
	 * @return int
	 * @throws RemoteException
	 */
	public int updateMyStudyRoom(MyStudyRoomVO vo);

}
