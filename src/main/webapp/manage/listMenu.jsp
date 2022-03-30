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
<title>메뉴 관리</title>
</head>
<body>

	<h1 align="center">
		<p>PC방 메뉴 정보</p>
	</h1>
	
	
	<div align="right">
		<input type="button" value="메뉴 추가하기"
			onClick="location.href='${contextPath }/manage/foodForm.jsp'" />
		<input align="center" type="button" value="취소" onclick="location.href='${contextPath }/member/homeManage.jsp'">
	</div>
	
	<table align="center" border="1" width="800" height="1000">
		<tr align="center" bgcolor="lightblue">
			<td width="5%"><b>사진</b></td>
			<td width="10%"><b>음식</b></td>
			<td width="10%"><b>가격(원)</b></td>
			<td width="3%"><b>삭제</b></td>
			<td width="3%"><b>수정</b></td>

		</tr>

		<c:choose>

			<c:when test="${ empty menuList })">
				<tr>
					<td colspan=5><b>등록된 메뉴가 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${!empty menuList }">
				<c:forEach var="menu" items="${menuList }">
					<tr align="center">
						<c:choose>
							<c:when test="${menu.getLevel()==1 }">
								<td colspan=5 bgcolor="lightyellow">${menu.food }</td>
							</c:when>

							<c:otherwise>


								<td><img
									src="${contextPath }/download.do?imageFileName=${menu.imageFileName }&foodNO=${menu.menuNO}"
									id="preview" style="width: 200px; height: 100px;"></td>

								<td>${menu.food }</td>
								<td>${menu.price }</td>
								<td><a
									href="${contextPath }/manageCon/delMenu.do?menuNO=${menu.menuNO}">삭제</a></td>
									<td><a
									href="${contextPath }/manageCon/viewMenu.do?menuNO=${menu.menuNO}">수정</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>


	</table>


</body>
</html>