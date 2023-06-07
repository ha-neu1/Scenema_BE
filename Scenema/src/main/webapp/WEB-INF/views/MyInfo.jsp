<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/MyInfo.css">
</head>
<script src="resources/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function(){
	$("#UserUpdateButton").on('click',function(){
		location.href = "/scenema/updateuser"		
	});
	
	$("#deleteUser").on('click',function(){
		if(confirm("정말로 회원탈퇴하시겠습니까?\n탈퇴 시 데이터는 복구되지 않습니다.")){
			$.ajax({
				url:'deleteuser',
				data: {
					'userid':"${userid}"
				},
				type:'get',
				success:function(res){
					alert("회원탈퇴되었습니다. 안녕히 가세요.");
					location.href = "/scenema/logout";
				},
				error:function(request,status,e){
					alert("코드="+request.status+"\n메시지="+request.responseText+"\nerror="+e);
				}
			}); //ajax
		}else{
			alert("회원탈퇴를 취소합니다.");
		}
		
	});
	
});
</script>
<body>
<%@ include file="Header.jsp" %>

<article>
		<form name="signUpForm">
		<h2>내 정보</h2>
			<table id='myinfo'>
			<tr><td>아이디</td><td>${loginUser.userid}</td></tr>
			<tr><td>이름</td><td>${loginUser.name}</td></tr>
			<tr><td>휴대폰번호</td><td>${loginUser.phone}</td></tr>
			<tr><td>이메일</td><td>${loginUser.email}</td></tr>
			<tr><td>정보 수정 및 탈퇴</td><td><button type="button" id="UserUpdateButton">회원정보수정</button><button id="deleteUser">회원탈퇴</button></td></tr>
			</table>
		
		<h2>내가 좋아요한 영화</h2>
		<div class='container'>
		<c:forEach items="${movieDBlist}" var="movie">
			<span class='likemovies'>
				<span class='imgcontainer'>
				<a href="detailpage?movieid=${movie.movieid}"><img src="${movie.posterurl}">
				</a>
				</span>
			</span>
		</c:forEach>
		</div>
			
		</form>
	</article>

<%@ include file="Footer.jsp" %>
</body>
</html>