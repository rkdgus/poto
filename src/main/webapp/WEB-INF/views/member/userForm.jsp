<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width:120px;
		display: inline-block;
	}
</style>

</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>

	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<form method="post" action="" id="f1">
		<p>
			<label>아이디</label><input type="text" name="userid" id="userid"><button id="check">중복체크</button>
		</p>
		<p>
			<label>이름</label><input type="text" name="username" id="username">
		</p>
		<p>
			<label>비밀번호</label><input type="password" id="password">
		</p>
		<p>
			<label>비밀번호 확인</label><input type="password" name="userpw" id="userpw">
		</p>
		<p>
			<label>전화번호</label><input type="tel" name="phone" id="phone">
		</p>
		<p>
			<label>이메일</label><input type="email" name="email" id="email">
		</p>
		<input type="submit" value="회원가입"> <input type="reset"
			value="취소">
	</form>
		</div>
		<div class="col-sm-4"></div>
	</div>
	<script type="text/javascript">
	$(function(){
		var idCheck=false;
		$("#f1").submit(function() {
			var idReg = /^[a-z]+[a-z0-9]{6,15}$/g;
			var pwReg =/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}$/;
			var nameReg =/^[가-힣]{2,5}$/;
			if(!idReg.test($("#userid").val())){
				alert("아이디 영어,숫자 조합 6~15자  입력하세요");
				return false;                
			}                        
			if(!nameReg.test($("#username").val())){
				alert("이름 한글  2~5자 입력하세요");
				return false; 
			}
			if(!pwReg.test($("#password").val())){
				alert("비밀번호 영어,숫자,특수문자(~!@#$%^&*) 조합 8~20자  입력하세요");
				return false; 
			}
			if($("#password").val() != $("#userpw").val()){
				alert("비밀버호와 비밀번호 확인이 다름니다.");
				return false; 
			}
			
			if(idCheck==false){
				alert("아이디 중복확인을 해주세요");
				return false; 
			}
		})
		
		$("#userid").change(function() {
			idCheck=false;
		})
		
		$("#check").click(function(){
			idCheck=false;
			var id=$("#userid").val();
			if(id==""){
				alert("아이디를 입력하세요");
				return false;         
			}
			$.ajax({
				url :"${pageContext.request.contextPath}/member/userForm/"+id,
				type : "get",
				dataType : "text",
				success : function(result) {
					console.log(result);
					if(result == "SUCCESS"){
						idCheck=true;
						alert("사용 가능 합니다.");   
					}else{
						idCheck=false;
						alert("중복된 아이디입니다.");   
					}
					       
				}
			})
			               
			return false;
		})                         
	})                                      
	
</script>
</body>
</html>