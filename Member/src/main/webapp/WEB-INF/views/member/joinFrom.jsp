<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">




</style>
</head>
<body>
<form action="/member/join" method="post">

<div>
	<span>아이디</span>
	<label for="id"><input type="text" id="id" name="id"></label>
</div>

<div>
	<span>닉네임</span>
	<label for="nick"><input type="text" id="nick" name="nick"></label>
</div>
<div>
	<span>이메일</span>
	<label for="mail"><input type="text" id="mail" name="mail"></label>
</div>
<br>
<br>
<button type="submit">회원가입</button>

</form>
</body>
</html>