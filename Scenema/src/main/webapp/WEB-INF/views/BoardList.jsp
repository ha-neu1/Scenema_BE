<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/BoardList.css">
<script src="resources/js/jquery-3.6.4.min.js"></script>
</head>
<body>
<header id='header'>
		<div>
			<nav id='headlinks'>
				<a href="login.html" >로그인</a>
				<a href="signUp.html" >회원가입</a>
			</nav>
			<h1 id=logo style="margin-top:0px; margin-bottom:10px; text-align: center" >SCENEMA</h1>
		</div>
		<nav id= 'menu' class='menu'>
			<ul>
				<li class='menu_in'><a href="mainpage.html">홈</a></li>
				<li class='menu_in'><a href="movie.html">영화</a></li>
				<li class='menu_in'><a href="boardList.html">커뮤니티</a></li>
				<li class='menu_in'><a href="#">내정보</a></li>
			</ul>
		</nav>
	</header>
		<div class="Search_write">
			<div class="SearchBar">
			<h2>커뮤니티</h2>
				<form class="SearchInput">
					<input id="Search" type="text" placeholder="검색어를 입력하세요"
					autocomplete="off" class="SearchInput_input" value>
					<button id="SearchBtn">검색</button>
				</form>
				<div class="bt_wrap">
				<a href="BoardWriting.html" class="on">글쓰기</a>
			</div>
			</div>
		</div>
		<div>
            <div class="board_list">
                <div class="top">
                    <div class="title">제목</div>
                    <div class="writer">작성자</div>
                    <div class="date">작성일</div>
                    <div class="count">조회수</div>
                    <div class="like">좋아요</div>
                </div>
                <c:forEach var="board" items="${BoardList}">
                <div>
                    <div class="title"><a href="/boardRead/${boardid}">${board.title}</a></div>
                    <div class="writer">${board.userid}</div>
                    <div class="date">${board.createAt}</div>
                    <div class="count">${board.boardCount}</div>
                    <div class="like">${board.boardLike}</div>
                </div>
                </c:forEach>
            </div>
			<div class="board_page">
                <a href="#" class="bt prev"><</a>
                <a href="#" class="num on">1</a>
                <!-- <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a> -->
                <a href="#" class="bt next">></a>
            </div>
		</div>
</body>
</html>