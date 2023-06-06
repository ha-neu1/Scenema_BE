<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/BoardListById.css" rel="stylesheet">
<script src="resources/js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(function(){
		$("#addComment").on('click', function(){
			$.ajax({
				url : "addcomment",
				method : "post",
				data : {
					"boardid" : $("#boardid").val(),
					"contents" : $("#add_comment").val(),
					"userid" : "java3"
				},
				success : function(){
					alert("댓글을 등록했습니다.")
				}
			});//ajax
		});//click
	});//ready
</script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="board_view_wrap">
		<div class="board_menu">
			<h2>커뮤니티</h2>
		</div>
		<div class="board_view">
			<c:forEach var="board" items="${BoardListById}">
				<div class="title">${board.title }</div>
				<div class="info">
					<dl>
						<dt>번호</dt>
						<dd id="boardid">${board.boardid }</dd>
					</dl>
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
			</c:forEach>
			<c:forEach var="board" items="${BoardListById }">
				<div class="cont">${board.boardContent }</div>
			</c:forEach>
		</div>
		<div class="bt_wrap">
			<a href="/scenema/boardlist" class="on">목록보기</a> <a
				href="/scenema/boardwriting">새글쓰기</a> <a>좋아요</a>
		</div>
			<div class="comment-in">
				<input type="text" id="add_comment" placeholder="댓글을 남겨주세요." autocomplete="off"
					id="incomment" />
				<button id="addComment">댓글 추가</button>
			</div>
<!-- 
		<c:forEach var="boardComment" items="${BoardCommentById}">
			<div id="comment-list">
				<div id="item" class="item">
					<span id="userid">${boardComment.userid}</span> <span id="bcCreateAt">${boardComment.bcCreateAt}</span>
					<span id="contents">${boardComment.contents}</span>
				</div>
			</div>
		</c:forEach>
-->
	</div>
</body>
<%@ include file="Footer.jsp"%>
</html>