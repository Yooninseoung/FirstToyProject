<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>

	<form method="post" action="${contextPath }/memberCon/loginCheck.do">
		<!-- 로그인 화면 결과에따라 손님과 관리자를 구별 -->
		<table board="1" align="center">
			<tr align="center">
				<td colspan="2" align="center"><h1> 로그인 </h1></td>
			</tr>

			<tr>
				<td width="200"><p align="right">아이디</p></td>
				<td width="400"><input type="text" name="id" /></td>
			</tr>

			<tr>
				<td width="200"><p align="right">비밀번호</p></td>
				<td width="400"><input type="password" name="pwd" /></td>
			</tr>

			<tr>
				<td> </td>
				
				<td>
				<input type="submit" value="로그인">
				<input type="reset" value="재입력">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>