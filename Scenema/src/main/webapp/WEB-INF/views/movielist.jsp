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
	<div class="grid" id="movies"></div>
	</div>
	
	<script>
		const apiKey = '4b6df7b6f8b376e0947c645f1e386af2';
		const url = `https://api.themoviedb.org/3/movie/popular?api_key=4b6df7b6f8b376e0947c645f1e386af2&language=ko-KR`;
	
		fetch(url)
				.then(response => response.json())
				.then(data => {
					const moviesContainer = document.getElementById('movies');
	
					data.results.forEach(movie => {
						const movieElement = createMovieElement(movie);
						moviesContainer.appendChild(movieElement);
					});
				})
				.catch(error => console.log(error));
	
		function createMovieElement(movie) {
			const movieElement = document.createElement('div');
			movieElement.classList.add('movie');
	
			const imageElement = document.createElement('img');
			imageElement.src = `https://image.tmdb.org/t/p/w500/${movie.poster_path}`;
			movieElement.appendChild(imageElement);
	
			const titleElement = document.createElement('div');
			titleElement.classList.add('movie-title');
			titleElement.textContent = movie.title;
			movieElement.appendChild(titleElement);
	
			movieElement.addEventListener('click', () => {
				const movieUrl = `https://www.themoviedb.org/movie/${movie.id}`;
				window.open(movieUrl, '_blank');
			});
	
			return movieElement;
		}
	</script>

	<%@ include file="Footer.jsp"%>
</body>
</html>
    