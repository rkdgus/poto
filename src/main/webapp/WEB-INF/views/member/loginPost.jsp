<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<script type="text/javascript">
		alert("아이디,비밀번호가 존재하지 않습니다.");
		location.href="${pageContext.request.contextPath}/member/login";
	</script>
</body>
</html>