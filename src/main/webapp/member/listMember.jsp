<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
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
<title>회원 정보창</title>
</head>
<body>

<h1 align="center"><p>PC방 회원정보</p></h1>
<table align="center" border="1">
<tr align="center" bgcolor="lightgreen">
<td width="7%"><b>아이디</b></td>
<td width="7%"><b>비밀번호</b></td>
<td width="7%"><b>이름</b></td>
<td width="7%"><b>가입일</b></td>
<td width="7%"><b>삭제</b></td>
</tr>

<c:choose>

<c:when test="${ empty membersList })">
<tr>
<td colspan=5>
<b>등록된 회원이 없습니다.</b>
</td>
</tr>
</c:when>
<c:when test="${!empty membersList }">
<c:forEach var="mem" items="${membersList }">
<tr align="center">
<td>${mem.id }</td>
<td>${mem.pwd }</td>
<td>${mem.name }</td>
<td>${mem.joinDate }</td>
<td><a href="${contextPath }/memberCon/delMember.do?id=${mem.id}">삭제</a></td>
</tr>
</c:forEach>
</c:when>
</c:choose>
</table>
<div align="right"><input align="center" type="button" value="취소" onclick="location.href='${contextPath }/member/homeManage.jsp'"></div>

</body>
</html>