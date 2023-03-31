<%@page import="org.apache.catalina.filters.ExpiresFilter.XServletOutputStream"%>
<%@page import="common.JDBCTemplate"%>
<%@page import="dao.impl.MemberDaoImpl"%>
<%@page import="dao.face.MemberDao"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member member = (Member) request.getAttribute("member");%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 Result다</title>
<style type="text/css">
table {
	border: 1px solid #ccc;
	margin: 0 auto;
}
td {
	border-bottom : 1px;
}
h1 {
	text-align: center;
}

</style>
</head>
<body>
<h1>회원 가입을 환영한다 뉴비 디붕아</h1>


<table>
	<thead>
		<tr>
			<th>userno</th>
			<th>ID</th>
			<th>NICK</th>
			<th>Email</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><%=member.getUserno()%></td>
			<td><%=member.getUserid()%></td>
			<td><%=member.getNick()%></td>
			<td><%=member.getEmail()%></td>
		</tr>
	</tbody>
</table>

<p> 번호 :  <%=member.getUserno() %></p>
<p> 아이디 : <%=member.getUserid() %></p>
<p> 닉네임 : <%=member.getNick() %></p>
<p> 이메일 : <%=member.getEmail() %></p>

<a href="/member/join"><button>신규 회원 가입</button></a>

</body>
</html>