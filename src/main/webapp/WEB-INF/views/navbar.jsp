<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	.navbar img{
		width:100px;
	    display: block;                       
	}
         
</style>
</head>
<body>
	<nav class="navbar">           
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/images/google.png"></a>                           
			</div>
			<ul class="nav navbar-nav  navbar-right">
			<c:if test="${login != null }">                  
				<li class="navbar-text">${login.userid} 님 </li>
				<li><a href="${pageContext.request.contextPath}/member/logout">갤러리</a></li>                                                      
				<li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
			</c:if>
			<c:if test="${login == null }">
				<li><a href="${pageContext.request.contextPath}/member/userForm">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
			</c:if>
				
			</ul>
			                   
		</div>              
	</nav>                                                                    
</body>                             
</html>