<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SCENEMA</title>
<link rel="stylesheet" href="resources/css/movie.css">
<script src="resources/js/jquery-3.6.4.min.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<h3 id='boxtitle'>장르별 <span style="color:#FF7322;font-family: 'NanumSquareNeoExtraBold';">인기 영화</span></h3>
	<div class="products">
		<h3><span style="color:#FF7322;">⧉</span>액션</h3>
		<div class='products_box'>
			<c:forEach items="${movielist}" var="list" varStatus="vs" end="4" >
				<span class="image-container">
	 				<a href="detailpage?movieid=${list.movieid}">
	   			<img class="apiposter" src="${list.posterurl}" data-title="${list.title}">
	   			<span class="movie-title">${list.title}</span>
	 				</a>
				</span>
			</c:forEach>
		</div>
			
		<h3><span style="color:#FF7322;">⧉</span>판타지</h3>
		<div class='products_box'>
			<c:forEach items="${movielist}" var="list" varStatus="vs" begin="5" end="9" >
				<span class="image-container">
	 				<a href="detailpage?movieid=${list.movieid}">
	   				<img class="apiposter" src="${list.posterurl}" data-title="${list.title}">
	 				</a>
	   				<span class="movie-title">${list.title}</span>
				</span>
			</c:forEach>
		</div>
		
		<h3><span style="color:#FF7322;">⧉</span>드라마</h3>
		<div class='products_box'>
			<c:forEach items="${movielist}" var="list" varStatus="vs" begin="10" end="14">
				<span class="image-container">
	 				<a href="detailpage?movieid=${list.movieid}">
	   			<img class="apiposter" src="${list.posterurl}" data-title="${list.title}">
	   			<span class="movie-title">${list.title}</span>
	 				</a>
				</span>
			</c:forEach>
		</div>
		
		<h3><span style="color:#FF7322;">⧉</span>애니메이션</h3>
		<div class='products_box'>
			<c:forEach items="${movielist}" var="list" varStatus="vs" begin="15" end="19" >
				<span class="image-container">
	 				<a href="detailpage?movieid=${list.movieid}">
	   			<img class="apiposter" src="${list.posterurl}" data-title="${list.title}">
	   			<span class="movie-title">${list.title}</span>
	 				</a>
				</span>
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="Footer.jsp"%>
</body>
</html>
    