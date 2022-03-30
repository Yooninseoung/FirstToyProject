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
$(function(){
	var choiceNum=$('#choice').val();
	$("input:radio[name=parentNO]:input[value="+choiceNum+"]").attr('checked', true); //name이 parentNO이고 value가 ?인 라디오를 선택
});

</script>

<title>메뉴 수정</title>
</head>
<body>
<form name="viewFoodForm" method="post" action="${contextPath }/manageCon/modMenu.do" enctype="multipart/form-data">

<input type="hidden" value="${foodView.menuNO }" name="menuNO" /> <!-- 메뉴 번호를 히든으로 보냄 -->
<input type="hidden" value="${foodView.parentNO }" id="choice" /> <!-- 기존의 종류 parentNO -->
<table border=1 align="center">
<tr>
<td>메뉴 종류</td>
<td>
				<div><input type="radio"  name="parentNO" value="1" >분식</div>
				<div><input type="radio"  name="parentNO" value="2" >음료</div>
				<div><input type="radio"  name="parentNO" value="3" >기타</div>
</td>
</tr>

<tr>
<td>메뉴 이름</td>
<td><input type="text" name="food" value="${foodView.food }" />
</tr>

<tr>
<td>메뉴 가격</td>
<td><input type="text" name="price" value="${foodView.price }" />
</tr>

<c:if test="${not empty foodView.imageFileName && foodView.imageFileName != 'null' }">
<tr>
<td align="center"rowspan="2">이미지</td>
<td><input type="hidden" value="${foodView.imageFileName }" name="originalFileName" /> <!-- 원래 이미지 이름 -->
<image src="${contextPath }/download.do?imageFileName=${foodView.imageFileName }&foodNO=${foodView.menuNO }" id="preview" style="width: 300px; height: 300px;"/></td>
</tr>
<tr>
<td>
<input type="file" name="imageFileName"/>
</td>
</tr>
</c:if>
<td></td>
<td>
<input type="submit" value="수정 확정">
<input type="button" value="취소" onclick="location.href='${contextPath }/manageCon/listMenu.do'">
</td>
</table>

</form>

</body>
</html>