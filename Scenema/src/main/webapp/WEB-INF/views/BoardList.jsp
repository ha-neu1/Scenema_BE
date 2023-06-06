<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/BoardList.css">
<script src="resources/js/jquery-3.6.4.min.js"></script>
</head>
<body>
<%@ include file="Header.jsp" %>
		<div class="Search_write">
			<div class="SearchBar">
			<h2>커뮤니티</h2>
				<form class="SearchInput">
					<input id="Search" type="text" placeholder="검색어를 입력하세요"
					autocomplete="off" class="SearchInput_input">
					<button id="SearchBtn">검색</button>
				</form>
				<div class="bt_wrap">
				<a href="/scenema/boardwriting" class="on">글쓰기</a>
			</div>
			</div>
		</div>
		<div>
            <div class="board_list">
                <div class="top">
					<div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">작성자</div>
                    <div class="date">작성일</div>
                    <div class="count">조회수</div>
                    <div class="like">좋아요</div>
                </div>
                <c:forEach var="board" items="${BoardList}">
                <div>
					<div class="num" id="boardid">${board.boardid }</div>
                    <div class="title" id="title"><a href="/scenema/boarddetail?boardid=${board.boardid }">${board.title}</a></div>
                    <div class="writer" id="userid">${board.userid}</div>
                    <div class="date" id="creatAt">${board.creatAt}</div>
                    <div class="count" id="boardCount">${board.boardCount}</div>
                    <div class="like" id="boardLike">${board.boardLike}</div>
                </div>
                </c:forEach>
            </div>
			<div class="board_page">
                <a href="#" class="bt prev"></a>
                <a href="#" class="num on">1</a>
                <!-- <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a> -->
                <a href="#" class="bt next">></a>
            </div>
		</div>

</body>
<%@ include file="Footer.jsp" %>
</html>