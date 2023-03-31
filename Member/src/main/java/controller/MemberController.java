package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/member/join")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// do get 테스트
	System.out.println("controller test");
	
//	  1. 클라이언트에서 요청 ( /member/join , get )
//
//	  2. 서블릿이 요청 받음 ( controller.MemberController )
//
//	  3. VIEW 응답하기 ( /WEB-INF/views/member/joinForm.jsp )
//		( userid, nick, email 을 입력하는 <form> 생성 )
//
//		( action: /member/join, method: post )
	
		RequestDispatcher rd;
		
		rd = req.getRequestDispatcher("/WEB-INF/views/member/joinFrom.jsp");
		
		rd.forward(req, resp);
		
		// 한 방에 작성하기
//		req.getRequestDispatcher("").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 한글 적용
		req.setCharacterEncoding("UTF-8");
		
		// dopost 테스트
		System.out.println("dopost 테스트");
			
//	1. 클라이언트에서 Form데이터를 전송 ( /member/join, post )
			
//		  2. 서블릿이 요청 받음 ( controller.MemberController )
			
//		  3. 서블릿이 요청파라미터를 꺼내서 MODEL객체에 저장
//		    ( dto.Member )
			// 방법 1
			String id = req.getParameter("id");
			String nick = req.getParameter("nick");
			String mail = req.getParameter("mail");
			
			System.out.println(id + ":" + nick + ":" + mail);
		
			// -----------------------------
			// 방법 2.
			Member mem = new Member();
			
			// 이런 식으로 절차 생략하고 한꺼번에 작성하기
//			mem.setEmail(req.getParameter("mail"));
			mem.setEmail(mail);
			mem.setNick(nick);
			mem.setUserid(id);
//
			System.out.println(mem);
			
			// --------------------------------
			// 방법3.
			// Member 객체에 요청 파라미터 값을 가져오는 다른 방법
			// param이라는 Member 객체에 파라미터 값을 저장하는 메소드 
			// 전달 파라미터를 Member DTO로 저장하기 - MemberService 이용
			Member param = memberService.getParam( req );
			
			System.out.println("MemberController doPost() - param : " + param);
			
			
			
//		  4. DTO객체를 DAO에 join메소드로 전송
//		  5. DAO는 Member 테이블에 DTO객체 INSERT
//		  6. 입력한 데이터를 반환함
			
			Member member = memberService.join(param);
				
			System.out.println("memberService join() - " + member);
			
			// 삽입 실패의 경우 Service에서 반환하는 Member DTO객체 빈 값이 된다
			// 따라서 DB에 저장된 데이터를 다시 조회하는 방식으로 
			// 신뢰성을 올린다
			Member rmem = memberService.result(member);
			
			RequestDispatcher rd;
			
			// JSP view에 객체 전달하기
			req.setAttribute("member", rmem);
			
			// JSP view를 지정하고 forward
			req.getRequestDispatcher("/WEB-INF/views/member/result.jsp").forward(req, resp);
	
	
	}
	
	
}
