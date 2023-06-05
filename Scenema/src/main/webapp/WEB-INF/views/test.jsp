<%@page import="dto.MovieDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="resources/js/jquery-3.6.4.min.js"></script>
<script>
/*URL*/
let url = 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json'; 
url += '?key=12d3990ca0efc626624eb0ddf4269a36';
url += '&targetDt=20230604';
url += '&itemPerPage=10';

let result = null;
let request = new XMLHttpRequest();
request.open('GET', url);
request.send();
request.onload = function() {
	result = request.response;
	let myobj = JSON.parse(result);
	console.log(myobj.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
	
	let movielist = myobj.boxOfficeResult.dailyBoxOfficeList;
	let movienames = "";
	
	for(let i=0; i<movielist.length; i++){
		movienames += movielist[i].movieNm+"|";
	}
	
	$("#result").text(movienames);
}
</script>
<div id='result'></div>

</body>
</html>