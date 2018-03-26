<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.image_div {
	width: 150px;
	height: 150px;
	float: left;
}

.image_div1 {
	width: 200px;
	height: 200px;
	margin: 30px;
	position: relative;
	float: left;
	border: 1px solid #ccc;
	text-align: center;
}

.image_div1 img {
margin-top:10px;
	max-width: 100%;
	max-height: 100%;
}

#image_wrap {
	overflow: hidden;
}

.del_img_btn {
	position: absolute;
	top: 0;
	right: 0;

}

.image_div img {
	max-width: 150px;
	max-height: 150px;
	margin-right: 15px;
}
.image_div1:hover{
	box-shadow:2px 2px 2px 2px gray;
}
#modal img{           
	width:100%;
}

</style>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<h1>갤러리</h1>
	<button class="btn btn-success" data-toggle="modal"
		data-target="#myModal">사진 추가</button>

	<div class="form-group" id="image_list">
		<c:forEach var="file" items="${img }">
			<div class="image_div1"  data-toggle="modal"  data-target="#modal">
				
				
				<fmt:formatDate value="${file.regdate }" pattern="yyyy-MM-dd HH:mm" />
				
				<img src="displayFile?filename=${file.fullName }"
					data-del="${file.fullName }"> <a
					href="deleteImg/${file.pno }?filename=${file.fullName }"
					type="button" class="del_img_btn btn btn-danger">X</a>
				<p>
					${file.title}
				</p>
			</div>
		</c:forEach>
	</div>





	<div class="modal fade" id="modal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
					<div class="modal-body">

						<img src="">


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
			</div>

		</div>
	</div>






	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">사진 추가</h4>
				</div>
				<form method="post" action="galley" enctype="multipart/form-data">
					<div class="modal-body">

						<p>
							<input type="hidden" name="userid" id="userid"
								value="${login.userid }">
						</p>

						<div id="image_wrap"></div>

						<p>
							<label>파일선택</label> <input type="file" multiple="multiple"
								id="imageFile" name="imageFile">
						</p>



					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-default" value="추가">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>

		</div>
	</div>

	<script>
		var index = 1;
		$(function() {
			$(document).on(
					"change",
					"#imageFile",
					function() {
						$("#image_wrap").empty();
						var file = document.getElementById("imageFile");

						var reader = new FileReader();
						reader.onload = function(e) {

							var imgObj = $("<img>")
									.attr("src", e.target.result);

							var divObj = $("<div>").addClass("image_div")
									.append(imgObj);
							$("#image_wrap").append(divObj);
						}

						reader.readAsDataURL(file.files[0]);

						reader.onloadend = function(e) {
							if (index >= file.files.length) {
								index = 1;
								return;
							}
							reader.readAsDataURL(file.files[index]);
							index += 1;
						}
					})

			$(document).on("click", ".del_img_btn", function() {
				var yes = confirm("정말 삭제 하시겠습니까?");
				if (yes != 1) {
					return false;
				}
			})
			
			$(document).on("click", ".image_div1", function() {
				
				var src =$(this).find("img").attr("src").replace("/s_", "/");
				$("#modal img").attr("src",src);
			})               
			
			
			
			
		})
	</script>
</body>
</html>
