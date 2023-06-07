<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="resources/css/SignUpForm.css">
<script>
$(document).ready(function(){
    $("#signUpButton").on('click', function(e){
	    var userid = $("#userid").val();
	    var pw = $("#pw").val();
	    var pwAgain = $("#pwAgain").val();
	    var name = $("#name").val();
	    var phone = $("#phone").val();
	    var name = $("#name").val();
	    var email = $("#emailId").val()+ "@" + $("#emailAddress").val();
	    
        if (userid === "") {
            alert("아이디를 입력하세요.");
        } else if ( pw === "" || pwAgain === "" || pw != pwAgain) {
        	alert("비밀번호를 확인하세요.")
        } else if ( name === "") {
        	alert("이름을 입력하세요.")
        } else if ( phone === "") {
        	alert("전화번호를 입력하세요.")
        } else if ( email === "") {
        	alert("이메일을 입력하세요.")
        } else {
            $.ajax( {
                url : "signup",
                method : "post",
                data : {
                    "userid" : userid,
                    "pw" : pw,
                    "name" : name,
                    "phone" : phone,
                    "email" : email
                },
                success : function(){
                    alert("환영합니다!");
                    window.location.href = "/scenema";
                },
                error : function(){
                    console.log("에러발생");
                }
            }); // ajax
        }//else
    });//click
    
    $('#idCheckButton').click(function(){
        $.ajax({
            url: "duplicateCheck",
            method: "post",
            data: {
                "userid": $("#userid").val()
            },
            success: function(response) {
                if (response.isDuplicated) {
                    alert("사용할 수 없는 아이디입니다.");
                } 
                else {
                    alert("사용할 수 있는 아이디입니다.");
                }
            },
            error: function() {
                console.log("에러 발생");
            }
        });
    });
    
});//ready
</script>
</head>
<body>
<%@ include file="Header.jsp" %>

<body>
	<article>
		<form name="signUpForm">
		<h2>회원가입</h2>
			<div class='formindiv'>
				<p>아이디</p>
				<div id=id_div>
					<input type="text" id="userid" placeholder="ID" maxlength="16">
					<button type="button" id="idCheckButton">중복확인</button>
				</div>
			</div>

			<div class='formindiv'>
				<p>비밀번호</p>
				<label><input type="password" name="pw" id="pw" placeholder="PW" maxlength="16"></label>
			</div>
			
			<div class='formindiv'>
				<p>비밀번호 확인</p>
				<input type="password" name="pwAgain" id="pwAgain" placeholder="PW CHECK"
				maxlength="16">
			</div>

			<div class='formindiv'>
				<p>이름</p>
				<input type="text" name="name" id="name"
					placeholder="NAME">
			</div>

			<div class='formindiv'>
				<p>휴대폰번호 [ex)010-1234-1234]</p>
				<input type="tel" name="phone" id="phone" placeholder="010-1234-1234">
			</div>

			<div class='formindiv'>
				<p style="margin-bottom: 8px">이메일 [ex)abc@scenema.com]</p>
				<div id='emailinner'>
				<input type="text" name="emailId" id="emailId" placeholder="E-MAIL" style="width: 163px">
				@
				<input type="text" name="emailAddress" id="emailAddress" placeholder="" style="width: 170px">
				</div>
			</div>

			<div class='formindiv'>
				<button type="button" id="signUpButton">회원가입</button>
			</div>

		</form>
	</article>
</body>
<%@ include file="Footer.jsp" %>
</html>