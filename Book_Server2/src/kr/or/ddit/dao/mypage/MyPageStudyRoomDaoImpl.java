package kr.or.ddit.dao.mypage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.MyStudyRoomSelect_JoinVO;

public class MyPageStudyRoomDaoImpl implements IMyPageStudyRoomDao{

	private SqlMapClient smc;
	private static IMyPageStudyRoomDao dao;
	private MyPageStudyRoomDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMyPageStudyRoomDao getInstance() {
		if(dao == null) {
			dao = new MyPageStudyRoomDaoImpl();
		}
		
		return dao;
	}
	
	
	@Override
	public List<MyStudyRoomSelect_JoinVO> selectMyStudyRoom(int num) {
		List<MyStudyRoomSelect_JoinVO> list = new ArrayList<MyStudyRoomSelect_JoinVO>();
		
		try {
			System.out.println("시작 전");
			list = smc.queryForList("myStudyRoom.selectMyStudyRoom", num);
			System.out.println("시작 후");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
