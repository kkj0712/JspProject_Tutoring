<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
img{
	display: inline-block;
	float: right;
}
#loginFrm{
	width: 60%;
	height: auto;
	position: absolute;
	top: 50%;
	left: 35%;
	transform: translate(-50%, -50%);
}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<img src="/Tutoring/img/banner.png" alt="loginImage">
		</div>
		<div class="col-md-4">
				<form action="login" method="post" id="loginFrm">
				  <div class="form-group">
				    <label for="userid">아이디</label>
				    <input type="text" class="form-control" id="userid" placeholder="Enter id" name="userid">
				  </div>
				  <div class="form-group">
				    <label for="pwd">패스워드</label>
				    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
				  </div>
				  <button type="button" id="loginBtn" class="btn btn-primary" style="width: 100%;">로그인</button>
				</form>
		</div>
</div>
<script>
$("#loginBtn").click(function(){
	if($("#userid").val()==""){
		alert("아이디를 입력하세요");
		$("#userid").focus();
		return false;
	}
	if($("#pwd").val()==""){
		alert("암호를 입력하세요");
		$("#pwd").focus();
		return false;
	}
	$.ajax({
		type : "post",
		url  : "login",
		data : {"userid":$("#userid").val(), "pwd":$("#pwd").val()},
		success : function(value){
			switch(value.trim()){
			case "0" : alert("로그인 성공"); location.href="/Tutoring/index.jsp"; break;
			case "1" : alert("관리자 로그인"); location.href="/Tutoring/member/Adminview"; break;
			case "2" : alert("비밀번호 오류"); break;
			case "-1": alert("회원이 아닙니다"); location.href="/Tutoring/member/insert"; break;
			default: alert(value.trim());
			}
		},
		error: function(e){
			alert("error:"+e);
		}
	})
})//loginBtn
</script>
<%@ include file="../include/footer.jsp" %>