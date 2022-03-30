<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
    request.setCharacterEncoding("utf-8");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function readURL(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload=function(e){
			$("#preview").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
</script>

<title>메뉴 추가</title>
</head>
<body>
																			<!-- 추가할 메뉴의 정보를 입력하는 곳 -->
	<form name="addFoodForm" method="post" action="${contextPath }/manageCon/addFood.do" enctype="multipart/form-data">

		<table border=1 align="center" width="500" height="600">
		
		
			<tr align="center">
				<td colspan="2" align="center"><h1>메뉴 추가</h1></td>
			</tr>
			
			<tr>
				<td width="100"><p align="center">종류</p></td>
				<td width="400">
				<div><input type="radio" name="parentNO" value="1" >분식</div>
				<div><input type="radio" name="parentNO" value="2" >음료</div>
				<div><input type="radio" name="parentNO" value="3" >기타</div>
				
				</td>
			</tr>

			<tr>
				<td width="200"><p align="center">메뉴 이름</p></td>
				<td width="400"><input type="text" name="food" /></td>
			</tr>

			<tr>
				<td width="200"><p align="center">가격</p></td>
				<td width="400"><input type="text" name="price" /></td>
			</tr>

			<!-- 음식 이미지 넣는 곳 -->
			<tr>
				<td>
				<input type="file" size="67" onchange="readURL(this);" name="imageFileName" />
				</td>
				<td>
				<img id="preview" src="#" width="200" height="200" />
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="추가">
					<input type="reset" value="재입력">
					<input type="button" value="취소" onclick="location.href='${contextPath }/manageCon/listMenu.do'">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>