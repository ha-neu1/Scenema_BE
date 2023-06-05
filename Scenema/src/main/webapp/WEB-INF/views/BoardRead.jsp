<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/BoardRead.css">
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
	
	<div class="board_view_wrap">
		<div class="board_menu">
            <h2>커뮤니티</h2>
        </div>
            <div class="board_view">
                <div class="title">
                    ${board.title}
                </div>
                <div class="info">
                    <dl>
                        <dt>작성자</dt>
                        <dd>${board.userid}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${board.createAt}</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>${board.boardCount}</dd>
                    </dl>
                    <dl>
                        <dt>좋아요</dt>
                        <dd>${board.boardLike}</dd>
                    </dl>
                </div>
                <div class="cont">
                    ${board.boardContent}
                </div>
            </div>
            <div class="bt_wrap">
                <a href="/boardList" class="on">목록보기</a>
                <a href="BoardWriting.html">새글쓰기</a>
                <a>좋아요</a>
            </div>
            
            
    <div class="comment-in">
    <input type="text" placeholder="댓글을 남겨주세요." autocomplete="off"
    id="input-comment"/>
    <button onclick="addComment()" id="addComment">댓글 추가</button>
    </div>
 
    <c:forEach var="comment" items="${comments}">
    <div id="comment-list">
        <div id="item-1" class="item">
        	<span id="cmtName-1">${comment.userid}</span>
        	<span id="cmt-date">${comment.bcCreateAt}</span>
            <span id="comment-1">${comment.contents}</span>
        </div>
    </div>
    </c:forEach>
        
        </div>
</body>
</html>