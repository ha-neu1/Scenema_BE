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
                    가디언즈는 내 가슴 속 영원히... 줍줍...
                </div>
                <div class="info">
                    <dl>
                        <dt>작성자</dt>
                        <dd>주모경</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>23.05.10</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>11</dd>
                    </dl>
                    <dl>
                        <dt>좋아요</dt>
                        <dd>0</dd>
                    </dl>
                </div>
                <div class="cont">
                    티프스, 라일라, 플로어 그리고 로켓이 다같이 모여 천장을 바라보며 푸른 하늘을 꿈꾸던 장면이 머리에서 잊혀지질 않아요..ㅠ
                </div>
            </div>
            <div class="bt_wrap">
                <a href="boardList.html" class="on">목록보기</a>
                <a href="BoardWriting.html">새글쓰기</a>
                <a>좋아요</a>
            </div>
        </div>
</body>
</html>