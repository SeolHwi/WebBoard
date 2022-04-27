<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/view.css">
	<title>View</title>
</head>
<body>
	<div id="main">
		<h2>${view.TITLE }</h2>
		<h4>${view.USER_ID } | ${view.DATE }</h4>
		<hr>
		<p>${view.CONTENT }<p>
	</div>
	<div id="button">
		<a href="board.do"><button>목록</button></a>
		<a href="update.do?BOARD_ID=${view.BOARD_ID }"><button>수정</button></a>
		<a href="delete.do?BOARD_ID=${view.BOARD_ID }"><button>삭제</button></a>
	</div>
</body>
</html>