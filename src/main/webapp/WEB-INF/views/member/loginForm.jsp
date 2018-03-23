<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width:100px;
		display: inline-block;
	}
</style>                  
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<div class="row">         
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
		<h1>로그인</h1>
			<form method="post" action="loginPost" id="f1">
		<p>
			<label>아이디</label><input type="text" name="userid" id="userid">
		</p>
		<p>
			<label>비밀번호</label><input type="password" name="userpw" id="password">
		</p>
		
		<input type="submit" value="로그인"> <input type="reset"
			value="취소">
	</form>
		</div>
		<div class="col-sm-4"></div>
	</div>
</body>
</html>