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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">


function calcu(menuName){ // 선택된 메뉴의 정보를 저장하는 장바구니 역할
	var food= $("#food"+menuName).text();
	var num = $("#quantity"+menuName).val();
	var price = $("#price"+menuName).text();
	var result = food+" : "+num+"개 / ";
	var total = parseInt(num)*parseInt(price);
	
	var preTotal = $("#totalWon").text();
	total += parseInt(preTotal);
	$("#totalWon").text(total);
	
	
	var temp = $("#result").text() + result;
	$("#result").html(temp);
	
	
}

function orderList(){// 장바구니의 정보를 db에 저장.. 관련
	var content = $("#result").html() + "==> 총 가격 : " +  $("#totalWon").text() +"(원)";
	var orderId = $("#orderId").html();

	var form = document.createElement("form");
	form.setAttribute("charset","utf-8");
	form.setAttribute("method","post");
	form.setAttribute("action","${contextPath }/orderCon/orderContent.do");
	
	var filed = document.createElement("input"); 
	filed.setAttribute("type", "text");
	filed.setAttribute("name", "orderContent");
	filed.setAttribute("value", content);
	form.appendChild(filed);
	
	var filed2 = document.createElement("input"); 
	filed2.setAttribute("type", "text");
	filed2.setAttribute("name", "orderId");
	filed2.setAttribute("value", orderId);
	form.appendChild(filed2);
	
	document.body.appendChild(form);
	form.submit();
}
</script>


<title>메뉴 주문창</title>
</head>
<body>
<div align="center"><h1>주문 창</h1></div>
주문자 : <p id="orderId">${id}</p>
<table align="center" border="1" width="800" height="1000">
		<tr align="center" bgcolor="lightgreen">
			<td width="10%"><b>사진</b></td>
			<td width="10%"><b>음식</b></td>
			<td width="10%"><b>가격(원)</b></td>
			<td width="3%"><b>수량</b></td>
			<td width="3%"><b>등록</b></td>

		</tr>

		<c:choose>

			<c:when test="${ empty menuList })">
				<tr>
					<td colspan="100%"><b>등록된 메뉴가 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${!empty menuList }">
				<c:forEach var="menu" items="${menuList }">
					<tr align="center">
						<c:choose>
							<c:when test="${menu.getLevel()==1 }">
								<td colspan=5 bgcolor="lightblue">${menu.food }</td>
							</c:when>

							<c:otherwise>

							
								<td><img
									src="${contextPath }/download.do?imageFileName=${menu.imageFileName }&foodNO=${menu.menuNO}"
									id="preview" style="width: 200px; height: 100px;"></td>

								<td><span id="food${menu.food }">${menu.food }</span></td>
								<td><span id="price${menu.food }">${menu.price }</span>(원)</td>
								<td><input id="quantity${menu.food}" type="text"></td>
								
								<td><input type="button" onClick="calcu('${menu.food}')" value="장바구니" ></td>
								
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
		<td colspan="100%"><p id="result">주문 메뉴 : </p></td>
		</tr>
		<tr>
		<td colspan="100%">
		<div>
		주문 합계 :<span id="totalWon">0</span>(원)
		<input type="button" value="주문하기" onClick="orderList()" >
		</div>
		</td>
		
		</tr>


	</table>

</body>
</html>