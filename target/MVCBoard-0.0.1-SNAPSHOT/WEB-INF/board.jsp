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
	<link rel="stylesheet" href="css/board.css">
	<title>BOARD</title>
</head>
<body>
	<%
		BoardFunc boardF = new BoardFunc();
		List<Map> allList = boardF.list();
		request.setAttribute("allList", allList);
	%>
	<div class="kind">
		<a href="board.do">전체</a>
		<a href="board.do">질문</a>
		<a href="board.do">정보</a>
	</div>
	<div class="container">
		<table>
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="45%">
				<col width="15%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th>구 분</th>
					<th>번 호</th>
					<th>제 목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allList }" var="item" varStatus="i">
				<tr>
					<td><c:out value="${item.KIND}"/></td>
					<td><c:out value="${item.BOARD_ID}"/></td>
					<td id="title"><a href="<c:url value='view.do?BOARD_ID=${item.BOARD_ID}' />"><c:out value="${item.TITLE}"/></a></td>
					<td><c:out value="${item.USER_ID}"/></td>
					<td><c:out value="${item.DATE}"/></td>
				</tr>					
				</c:forEach>
			</tbody>
		</table>
		
		<p>
			<a href="board.do"><button>처음으로</button></a>
			<a href="write.do" style="float:right;"><button>글쓰기</button></a>
		</p>
	</div>
</body>
</html>