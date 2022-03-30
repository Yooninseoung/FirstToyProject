<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
    <%
    request.setCharacterEncoding("utf-8");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 align="center"><p>PC방 주문 정보</p></h1>
<table align="center" border="1">
<tr align="center" bgcolor="lightgreen">
<td width="5%"><b>주문일</b></td>
<td width="5%"><b>주문자</b></td>
<td width="15%"><b>내역</b></td>
<td width="5%"><b>비고</b></td>
</tr>
<c:choose>

			<c:when test="${ empty orderList })">
				<tr>
					<td colspan=5><b>등록된 메뉴가 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${!empty orderList }">
				<c:forEach var="order" items="${orderList }">
					<tr align="center">
					
						<c:choose>
							<c:when test="${order.getLevel()==1 }">
								<!--  완성 / 미완성 구분
							<c:choose>
							<c:when test="${oreder.ok.equals(0)}"><td colspan="100%" bgcolor="lightyellow">미완성</td></c:when>
							<c:when test="${oreder.getOk().equals(1) } "><td colspan="100%" bgcolor="lightyellow">완성</td></c:when>
							</c:choose>
							-->
								
							</c:when>

							<c:otherwise>
							<td>${order.orderDate }</td>

								<td>${order.id }</td>
								<td>${order.content }</td>
								<td>${order.ok }</td>
								
								
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
</table>

</body>
</html>