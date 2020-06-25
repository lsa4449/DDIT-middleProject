package kr.or.ddit.dao.admin;

import java.util.List;

import kr.or.ddit.vo.MemManageVO;
import kr.or.ddit.vo.MemberManageVO;

public interface IAdminDao {

	// 회원 전체 조회
	public List<MemberManageVO> selectMemberAll();

	// 회원 상태 정지로
	public int updateMemberStateStop(int memNum);

	// 회원 상태 우수
	public int updateMemberStateGood(int memNum);

	// 회원 상태 일반
	public int updateMemberStateNomal(int memNum);

	// 회원 상태 경고
	public int updateMemberStateWarning(int memNum);

}
