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

    <div class="products">
	<h3>현재 인기 영화</h3>
	<div class='products_box'>
			<c:forEach items="${boxofficelist}" var="list" varStatus="vs" end="4" >
				<span class="image-container">
  				<a href="detailpage?movieid=${boxofficelist[vs.index].movieid}">
    			<img class="apiposter" src="${boxofficelist[vs.index].posterurl}" data-title="${boxofficelist[vs.index].title}">
    			<span class="movie-title">${boxofficelist[vs.index].title}</span>
  				</a>
				</span>
			</c:forEach>
		</div>
	<div class='products_box'>
			<c:forEach items="${boxofficelist}" var="list" varStatus="vs" begin="5" >
				<span class="image-container">
  				<a href="detailpage?movieid=${boxofficelist[vs.index].movieid}">
    			<img class="apiposter" src="${boxofficelist[vs.index].posterurl}" data-title="${boxofficelist[vs.index].title}">
    			<span class="movie-title">${boxofficelist[vs.index].title}</span>
  				</a>
				</span>
			</c:forEach>
		</div>
		</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
    