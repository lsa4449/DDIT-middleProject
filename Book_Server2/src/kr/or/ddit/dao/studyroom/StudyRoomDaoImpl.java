package kr.or.ddit.dao.studyroom;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.MyStudyRoomVO;
import kr.or.ddit.vo.StudyRoomRentalVO;
import kr.or.ddit.vo.StudyRoomVO;

public class StudyRoomDaoImpl implements IStudyRoomDao{

	private SqlMapClient smc;
	private static IStudyRoomDao dao;
	
	public StudyRoomDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IStudyRoomDao getInstance(){
		if(dao == null) {
			dao = new StudyRoomDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int reserStudyRoom(String studyRoomReserState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int useStudyRoom(String studyRoomState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancelStudyRoom(String studyRoomState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudyRoomVO> selectStudyRoom() {
		List<StudyRoomVO> voList =  null;
		
		try {
			voList = smc.queryForList("studyRoom.selectStudyRoom");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return voList;
	}

	@Override
	public int updateStudyRoomReserve_Cancel(StudyRoomVO vo) {
		int cnt = 0;
		try {
			System.out.println(cnt);
			System.out.println("실행전");
			cnt = smc.update("studyRoom.updateStudyRoomReserve_Cancel", vo);
			System.out.println("실행후");
			System.out.println(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateStudyRoomRental(StudyRoomRentalVO studyRoomRentalVO) {
		int cnt = 0;
		
		try {
			System.out.println("실행전 : " + cnt);
			cnt = smc.update("studyRoom.updateStudyRoomRental", studyRoomRentalVO);
			System.out.println("실행후 : " + cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMyStudyRoom(MyStudyRoomVO vo) {
		int cnt = 0;
		
		try {
			System.out.println("실행전 : " + cnt);
			cnt = smc.update("studyRoom.updateMyStudyRoom", vo);
			System.out.println("실행후 : " + cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

}
