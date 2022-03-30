<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <c:set var="contextPath" value="${pageContext.request.contextPath }" />
	
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
</head>
<body>
	<form method="post" action="${contextPath }/memberCon/addMember.do">
		<h1 style="text-align: center">회원 가입창</h1>
		
		<!-- 회원의 정보를 받음 -->
		<table align="center"> 
			<tr>
				<td width="200"><p align="right">아이디 : </p></td>
				<td width="400"><input type="text" name="id" /></td>
			</tr>
			
			<tr>
				<td width="200"><p align="right">비밀번호 : </p></td>
				<td width="400"><input type="password" name="pwd" /></td>
			</tr>
			
			<tr>
				<td width="200"><p align="right">이름 : </p></td>
				<td width="400"><input type="text" name="name" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td>
					<input type="submit" value="회원가입" /> 
					<input type="reset"value="재입력" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>