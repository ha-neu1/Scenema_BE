<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/BoardRead.css">
<script src="resources/js/jquery-3.6.4.min.js"></script>
</head>
<body>
<%@ include file="Header.jsp" %>
	<div class="board_view_wrap">
		<div class="board_menu">
            <h2>커뮤니티</h2>
        </div>
            <div class="board_view">
                <div class="title">
                    ${board.title }
                </div>
                <div class="info">
                    <dl>
                        <dt>작성자</dt>
                        <dd>${board.userid }</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${board.creatAt }</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>${board.boardCount }</dd>
                    </dl>
                    <dl>
                        <dt>좋아요</dt>
                        <dd>${board.boardLike }</dd>
                    </dl>
                </div>
                <div class="cont">
                    ${board.boardContent }
                </div>
            </div>
            <div class="bt_wrap">
                <a href="boardList.html" class="on">목록보기</a>
                <a href="BoardWriting.html">새글쓰기</a>
                <a>좋아요</a>
            </div>
        </div>
</body>
<%@ include file="Header.jsp" %>
</html>