<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <!-- 관리자 로그인 시 첫화면 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>
<h1 align="center">관리자 선택</h1>
<table align="center" width="600">
<tr align="center">
<td><a href="${contextPath }/memberCon/listMember.do">회원 리스트 보기</a></td>
<td><a href="${contextPath }/manageCon/listMenu.do">메뉴 추가/삭제</td> <!-- 메뉴 목록 -->
<td><a href="${contextPath }/orderCon/listOrder.do">주문 현황</td>
</tr>
</table>
</body>
</html>