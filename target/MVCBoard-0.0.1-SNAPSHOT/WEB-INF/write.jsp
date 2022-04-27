<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.func.BoardFunc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/write.css">
	<title>Write</title>
</head>
<body>
	<%
		BoardFunc boardF = new BoardFunc();
		List<Map> kindList = boardF.kind();
		request.setAttribute("kindList", kindList);
	%>
	<form action="write.do" method="post">
		<div id="write">
			<ul>
				<li><select name="KIND" required>
					<option selected disabled>---카테고리 선택---</option>
					<c:forEach items="${kindList }" var="item" varStatus="i">
						<option value="${item.ID }"><c:out value="${item.KIND }" /></option>
					</c:forEach>
				</select></li>
				<li><input type="text" name="USER_ID" required placeholder="작성자(10자 제한)"></li>
				<li><input type="text" name="TITLE" required placeholder="제목을 입력해주세요.(50자 제한)"></li>
				<li><textarea maxlength="3000" name="CONTENT" required placeholder="내용 작성 (3000자 제한)"></textarea></li>
			</ul>
		</div>
		<div id="button">
			<input type="submit" value="등록" onclick="move('board.do');">
			<input type="button" value="취소" onclick="move('board.do');">
		</div>
	</form>
	
	<script>
		function move(url) {
			location.href=url;
		}
	</script>
</body>
</html>