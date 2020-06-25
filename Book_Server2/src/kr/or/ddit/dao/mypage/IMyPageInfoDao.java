package kr.or.ddit.dao.mypage;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMyPageInfoDao {
	
	/**
	 * 회원정보 확인
	 * @param id
	 * @return
	 */
	public List<MemberVO> selectMyInfo (String id);
	
	
	/**
	 * 회원정보 수정
	 * @param memberVo
	 * @return
	 */
	public int updateMyInfo (MemberVO memberVo);
	
	
	
}
