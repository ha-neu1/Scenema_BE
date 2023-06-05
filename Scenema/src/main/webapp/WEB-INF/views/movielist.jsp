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
    <script>
        // 전역 변수 선언
        var slideIndex = 1;
        var timer;

        $(document).ready(function() {
            //스크롤시 메뉴바 고정
            $(window).scroll(function() {
                if ($(this).scrollTop() > 70) {
                    $("#menu").css('position', 'fixed');
                    $("#menu").css('top', '0');
                    $("#menu").css('left', '0');
                    $("#menu").css('z-index', '1');
                } else {
                    $("#menu").css('position', 'relative');
                }
            });
        })

        // 페이지 로드 시 함수 실행
        window.onload = function() {
            showSlides(slideIndex);
            timer = setInterval(function() {
                plusSlides(1)
            }, 5000); // 5초마다 자동으로 슬라이드 이동
        }

        // 현재 슬라이드에서 +/- n 슬라이드로 이동하는 함수
        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        // 현재 슬라이드에서 특정 슬라이드로 이동하는 함수
        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        // 슬라이드를 보여주는 함수
        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            var dots = document.getElementsByClassName("dot");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";
            dots[slideIndex-1].className += " active";
        }

        // 마우스가 슬라이드 위에 있을 때 타이머 멈추기
        function pauseTimer() {
            clearInterval(timer);
        }

        // 마우스가 슬라이드 밖으로 나갈 때 타이머 다시 시작
        function resumeTimer() {
            timer = setInterval(function() {
                plusSlides(1)
            }, 5000); // 5초마다 자동으로 슬라이드 이동
        }
    </script>
</head>
<body>
    <header id='header'>
        <div>
            <nav id='headlinks'>
                <a href="login.html">로그인</a>
                <a href="signUp.html">회원가입</a>
            </nav>
            <h1 id=logo style="margin-top:0px; margin-bottom:10px; text-align: center"> SCENEMA </h1>
        </div>

        <nav id='menu' class='menu'>
            <ul>
                <li class='menu_in'><a href="mainpage.html">홈</a></li>
                <li class='menu_in'><a href="movie.html">영화</a></li>
                <li class='menu_in'><a href="boardList.html">커뮤니티</a></li>
                <li class='menu_in'><a href="#">내정보</a></li>
            </ul>
        </nav>
    </header>
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
</body>
</html>
    