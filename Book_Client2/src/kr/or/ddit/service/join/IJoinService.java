package kr.or.ddit.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.vo.MemberVO;

public interface IJoinService extends Remote{
	//회원가입 관련 메소드 정의
	
	/**
	 * 소셜 아이디로 가입
	 * @param 
	 * @return 성공하면  null값 반환 -> int cnt 변수를 사용해 반환값 재설정
	 */
	public int insertMemberSns(MemberVO memberVO) throws RemoteException;
	
	/**
	 * 회원가입
	 * 입력받은 vo들을 db에 insert
	 * @param MemberVO
	 * @return 성공하면  null값 반환 -> int cnt 변수를 사용해 반환값 재설정
	 */
	public int insertMember(MemberVO memberVO) throws RemoteException;
	
	/**
	 * 아이디 중복 확인
	 * 입력받은 id가 DB에 존재하는지 유무 확인 -> id를 키값으로 회원 vo 반환
	 * @param Id 
	 * @return MemberVO
	 */
	public int selectIdMember(String id) throws RemoteException;
	
	/**
	 * 닉네임 중복 확인
	 * 입력받은 닉네임이 db에 존재하는지 유무 확인 -> 닉네임을 키값으로 회원 vo반환
	 * @param nickName
	 * @return MemberVO
	 */
	public int selectNickNameMember(String nickName) throws RemoteException;
	
	
}
