package kr.or.ddit.service.login;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.vo.MemberVO;

public interface ILoginService extends Remote{
	
	// 1. 아이디와 패스워드 입력 후 db에 해당하는 회원이 있으면 해당 회원 정보 반환
	
	// 2. 소셜 로그인?
	
	// 3. 아이디 찾기 - 이름, 핸드폰 번호로 찾기
	
	// 4. 아이디 찾기 - 이름, 이메일로 찾기
	
	// 5. 비밀번호 찾기 - 아이디, 이름, 전화번호 입력 시 임시비밀번호를 핸드폰으로 발송
	
	// 6. 비밀번호 찾기 - 아이디, 이름, 이메일 입력 시 임시비밀번호를 이메일로 발송
	
	// 
	
	/**
	 * 이 메소드가 뭔지
	 * @param mem 세부설명
	 * @return vo 뭔지 세부설명
	 */
	
	
	/**
	 * 아이디로 비밀번호 찾기 - 이것으로 로그인 해도 될거 같은데???
	 * 아이디를 키값으로해서  DB에서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memId 회원이 입력한 id
	 * @return mem_id와 mem_pass가 담긴 memberVo객체를 반환
	 */
	public MemberVO selectMember(String id) throws RemoteException;
	
	/**
	 * 이름과 이메일 키값으로 해서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memVo 회원이 입력한 이름과 메일이 담긴 vo
	 * @return 회원의 정보가 담긴 memberVo객체를 반환
	 */
	public MemberVO selectEmailMemberId(MemberVO memberVo) throws RemoteException; 
	
	/**
	 * 이름과 전화번호 키값으로 해서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memVo 회원이 입력한 이름과 메일이 담긴 vo
	 * @return 회원의 정보가 담긴 memberVo객체를 반환
	 */
	public MemberVO selctPhoneMemberId(MemberVO memberVo) throws RemoteException;
	
	/**
	 * 아이디, 이름, 이메일을 키값으로 해서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memVo 회원이 입력한 이름과 메일이 담긴 vo
	 * @return 회원의 정보가 담긴 memberVo객체를 반환
	 */
	public MemberVO selectEmailMemeberPass(MemberVO memberVo) throws RemoteException;
	
	/**
	 * 아이디, 이름, 전화번호 키값으로 해서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memVo 회원이 입력한 이름과 메일이 담긴 vo
	 * @return 회원의 정보가 담긴 memberVo객체를 반환
	 */
	public MemberVO selectPhoneMemberPass(MemberVO memberVo) throws RemoteException;
	
	/**
	 * 이름을 키값으로 해서 해당하는 사용자 정보가 담긴 정보를 반환하는 메서드
	 * @param memVo 회원이 입력한 이름과 아이디 주민번호가 담긴 vo
	 * @return 회원의 정보가 담긴 memberVo객체를 반환
	 */
	public MemberVO findPassUser(MemberVO memberVo) throws RemoteException; 
	
	/**
	 * 로그인을 하기 위해서 해당 사용자의 아이디와 비밀번호가 필요해서 사용한다.
	 * @param memberVo (id, pw)를 뽑는다.
	 * @return memberVO
	 */
	public MemberVO selectLoginMember(MemberVO memberVo) throws RemoteException;
	
 
	 
}
