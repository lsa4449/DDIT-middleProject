package kr.or.ddit.dao.mypage;

import java.util.List;
import kr.or.ddit.vo.PointCategoryVO;

import kr.or.ddit.vo.PointCategoryVO;

public interface IMyPagePointDao {

	
	// 포인트 입력
	public int insertNowPoint(PointCategoryVO pVo);
	// 총 포인트 조회
	public String selectAllPoint(int memNum);
	// 거래 포인트 입력
	public int insertTransPoint(PointCategoryVO pVo);
	// 거래 포인트 조회
	public String selectAllPointTrans(int memNum);
	
	
}
