<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
a{
	text-decoration: none;
}
a:hover{
	text-decoration: none;
}
.back2{
	margin-top: 20px;
	background-color: #f8f9fa;
}
#frm{
	margin: 0 auto;
	width: 50%;
}
.button{
	text-align: center;
	margin: 0 auto;
	padding: 20px;
}
.sidebar{
	float: left;
	width: 200px;
	height:200px;
	margin-top: 60px;
	margin-left: 60px;
}
.sidebar ul{
	list-style: none;
}
.sidebar ul a{
	line-height: 2em; 
}
</style>

<script>
$(document).ready(function(){
	//장바구니 리스트를 불러옴
	$.getJSON("/Tutoring/member/cartList", 
				{userid:$("#userid").val()}, 
				function(d){
					var htmlStr="<table class='table table-hover table-bordered table-sm'>";
						htmlStr+="<thead><tr>";
		            	htmlStr+="<th style='width: 10%; text-align: center;'>강의 번호</th>";
		            	htmlStr+="<th style='text-align: center;'>강의명</th>";
		            	htmlStr+="<th style='width: 10%; text-align: center'>삭제</th>";
		            	htmlStr+="</tr></thead>";
		            	htmlStr+="<tbody>";
					$.each(d.carr, function(key, val){ 
		            	htmlStr+="<tr>";
	            		htmlStr+="<td>"+val.classnum+"</td>";
	            		htmlStr+="<td>"+val.classname+"</td>";
	            		htmlStr+="<td style='text-align: center;'><a href='javascript:cartdel("+val.cartnum+")'><img src='/Tutoring/img/delete.png' style='height:20px; width:20px;'></a></td>";
						htmlStr+="</tr>";
					})
						htmlStr+="</tbody></table>";
						$(".back2").html(htmlStr);
				}
	)
})
//장바구니 삭제함수
function cartdel(cartnum){
	if(confirm("장바구니에서 삭제하시겠습니까?")){
		$.getJSON("/Tutoring/member/cartDelete", 
				{"cartnum":cartnum, userid:$("#userid").val()}, 
				function(d){
						var htmlStr="<table class='table table-hover table-bordered table-sm'>";
						htmlStr+="<thead><tr>";
		            	htmlStr+="<th style='width: 10%; text-align: center;'>강의 번호</th>";
		            	htmlStr+="<th style='text-align: center;'>강의명</th>";
		            	htmlStr+="<th style='width: 10%; text-align: center'>삭제</th>";
		            	htmlStr+="</tr></thead>";
		            	htmlStr+="<tbody>";
					$.each(d.carr, function(key, val){ 
		            	htmlStr+="<tr>";
	            		htmlStr+="<td>"+val.classnum+"</td>";
	            		htmlStr+="<td>"+val.classname+"</td>";
	            		htmlStr+="<td style='text-align: center;'><a href='javascript:cartdel("+val.cartnum+")'><img src='/Tutoring/img/delete.png' style='height:20px; width:20px;'></a></td>";
						htmlStr+="</tr>";
					})
						htmlStr+="</tbody></table>";
						$(".back2").html(htmlStr);
				}
		)
	}
}
</script>
<aside class="sidebar">
	<ul id="menu">
        <li><h5><a href="/Tutoring/member/cartList.jsp"> 장바구니</a></h5></li>
        <li><h5><a href="/Tutoring/member/view"> 계정설정</a></h5></li>
	</ul>
</aside>

<div class="container" id="userInfo">
<br/>
<h5 style="text-align: left"><span id="cntSpan">장바구니</span></h5>
<form action="/Tutoring/member/cartList">
<input type="hidden" id="userid" name="userid" value="${sessionScope.userid}">
</form>
<div class="back2"></div>
<div id="result"></div>
</div>
<%@ include file="/include/footer.jsp" %>