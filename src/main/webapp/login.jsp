<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title></title>       
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">   
  <meta http-equiv="keywords" content="figochan,微博">
  <meta http-equiv="description" content="微博系统">
  	<!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
    <link rel="stylesheet" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css" />
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./resources/css/global.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/login.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/formValidation.min.css"/> 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="./resources/js/formValidation.min.js"></script>
<script src="./resources/js/framework/bootstrap.js"></script>
<script src="./resources/js/language/zh_CN.js"></script>
 <script type="text/javascript" src="./resources/js/script.js"></script>
<script type="text/javascript" src="./resources/js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="./resources/js/noty/layouts/center.js"></script>
<script type="text/javascript" src="./resources/js/noty/themes/default.js"></script>
<script src="./resources/js/md5.js"></script>
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/original.css"/>
<script type="text/javascript" src="/weibo/resources/js/humane.js"></script>
  <script src="./resources/js/login.js"></script>
 </head>
 <body>
 <div id="page">

 <div id="content" class="">
<div class="panel login-panel">
 <div class="row">
  <div class="col-md-8 ">
  <div class="login-div">
  <p ><h2 class="text-center ">欢迎使用<strong>IDEAL</strong>微博</h2></p><br><br>
	<form class="form-horizontal" id="login-form" name="login-form" method="POST">
   <div class="form-group">
      <label class="col-sm-3 control-label">用户名：</label>
      <div class="col-sm-9">
         <input type="text" class="form-control" id="username" name="username"
            placeholder="用户名/手机号/邮箱">
      </div>
   </div>
   <div class="form-group">
      <label for="password" class="col-sm-3 control-label">密　码：</label>
      <div class="col-sm-9">
         <input type="password" class="form-control" id="password" name="password"
            placeholder="请输入密码">
      </div>
   </div>
  
   <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
         <button type="button" id="login" class="btn btn-warning ">&nbsp;&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;</button>
      <a href="#">忘记密码？</a><br><br>
      <p class="text-success">使用微博期间请自觉遵守相关法律法规</p>
      </div>
   </div>
</form>
</div>
  </div>
  <div class="col-md-4">
  <div class="text-center reg-div">
  <h4>还没有注册吗？</h4>
  <h4>现在就去注册吧</h4><br>
  <a href="register.jsp" class="btn btn-success btn-lg">现在注册</a>
  </div>
  </div>

</div>
</div>
 </div>
 <div id="foot">
  <jsp:include page="foot.jsp" />
 </div>
 </div>
 </body>
</html>