<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/write.css">
	<title>Write</title>
</head>
<body>
	<form action="update.do" method="post">
		<div id="write">
			<ul>
				<li><input type="text" name="USER_ID" required value="${update.USER_ID }"></li>
				<li><input type="text" name="TITLE" required value="${update.TITLE }"></li>
				<li><textarea maxlength="3000" name="CONTENT" required>${update.CONTENT }</textarea></li>
			</ul>
		</div>
		<div style="display: none;">
			<input type="text" name="BOARD_ID" value="${update.BOARD_ID }" />
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