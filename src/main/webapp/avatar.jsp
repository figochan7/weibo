<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>标题</title>       
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">   
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
   <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="./resources/css/jquery.datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/formValidation.min.css"/> 
 <script src="./resources/js/jquery.datetimepicker.js"></script>
<script src="./resources/js/formValidation.min.js"></script>
<script src="./resources/js/framework/bootstrap.js"></script>
<script src="./resources/js/language/zh_CN.js"></script>


<link rel="stylesheet" type="text/css" href="./resources/css/global.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/setting.css"/>
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/original.css"/>
<script type="text/javascript" src="/weibo/resources/js/humane.js"></script>

 </head>
 <body>
<div id="page">
 <div id="head">
 <jsp:include page="head.jsp" />
 </div>
<div class="panel">
 <div class="row">
  <div class="col-md-7 ">
  <div class="login-div">
	<form class="form-horizontal" method="post" name="reg-form" id="reg-form">
   <div class="form-group">
      <label for="username" class="col-sm-3 control-label">用户名：</label>
      <div class="col-sm-9">
         <input type="text" class="form-control" id="username" name="username"
            placeholder="用户名" disabled="disabled" value="${user.username }">
      </div>
   </div>
   <div class="form-group">
      <label for="telephone" class="col-sm-3 control-label">手机号：</label>
      <div class="col-sm-9">
         <input type="text" class="form-control" id="telephone" name="telephone"
            placeholder="手机号码" value="${user.telephone }">
      </div>
   </div>
   <div class="form-group">
      <label for="email" class="col-sm-3 control-label">邮　箱：</label>
      <div class="col-sm-9">
         <input type="text" class="form-control" id="email" name="email"
            placeholder="邮箱" value="${user.email }">
      </div>
   </div>
   
   <div class="form-group">
      <label for="birthday" class="col-sm-3 control-label">生　日：</label>
      <div class="col-sm-9">
         <input type="text" class="form-control" id="birthday" name="birthday"
            placeholder="生日" readonly="readonly" value="${birthday }">
      </div>
   </div>
    <div class="form-group">
      <label for="introduction" class="col-sm-3 control-label">简　介：</label>
      <div class="col-sm-9">
         <textarea class="form-control" rows="3" name="introduction" id="introduction">${user.introduction }</textarea>
      </div>
   </div>

  
   <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
         <button type="button" class="btn btn-warning " id="signup">&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;</button>
         <button type="button" class="btn btn-primary " id="reset">&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;</button>
<br><br>
      <p class="text-success">使用微博期间请自觉遵守相关法律法规</p>
      </div>
   </div>
</form>
</div>
  </div>
  <div class="col-md-5">
  <div class="pwd-div">
  	<form class="form-horizontal" method="post" name="pwd-form" id="pwd-form">

   <div class="form-group">
      <label for="password" class="col-sm-3 control-label">旧密码：</label>
      <div class="col-sm-9">
         <input type="password" class="form-control" id="password" name="password"
            placeholder="请输入旧的密码">
      </div>
   </div>
   
    <div class="form-group">
      <label for="repassword" class="col-sm-3 control-label">新密码：</label>
      <div class="col-sm-9">
         <input type="password" class="form-control" id="newpassword" name="newpassword"
            placeholder="请输入新的密码">
      </div>
   </div>
   
   <div class="form-group">
      <label for="repassword" class="col-sm-3 control-label">确　认：</label>
      <div class="col-sm-9">
         <input type="password" class="form-control" id="repassword" name="repassword"
            placeholder="请再次输入新的密码">
      </div>
   </div>
  
   <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
         <button type="button" class="btn btn-warning " id="updatepwd">&nbsp;修改密码&nbsp;</button>
      </div>
   </div>
</form>
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