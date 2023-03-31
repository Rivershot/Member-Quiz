package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

	/**
	 * 전달된 파라미터(회원정보)를 DBdp 저장시켜
	 * 회원 가입 처리를 한다
	 * 
	 * @param param - 클라이언트가 입력한 신규 회원 정보
	 * @return - DB에 삽입된 회원 정보
	 */
	public Member join(Member param);

	public Member result(Member member);
	
	/**
	 * 
	 * 전달된 요청 정보 객체(req)를 통해서
	 * 전달 파라미터 userid, nick, email 추출
	 * 
	 * 추출된 파라미터는 Member 객체로 Return
	 * 
	 * @param req - 요청 정보 객체
	 * @return - Member 객체로 전달 파라미터인 userid, nick, email Return
	 */
	public Member getParam(HttpServletRequest req);
	
	
}
