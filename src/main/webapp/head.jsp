<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>标题</title>       
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">   
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
 </head>
 <body>

<nav class="navbar navbar-default" role="navigation">
	 <div class="navbar-header head-item">
      <a class="navbar-brand" href="user/index"><strong>IDEAL微博</strong></a>
   </div>
   <div>
      <form class="navbar-form navbar-left" role="search">
         <div class="form-group">
            <input type="text" class="form-control" placeholder="搜索">
         </div>
         <button type="submit" class="btn btn-default">Go</button>
      </form>  
      
      <ul class="nav navbar-nav navbar-right head-item">
         <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               	设置<b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="setting/info">个人信息</a></li>
               <li><a href="setting/avatar">头像</a></li>
               <li><a href="user/logout">退出</a></li>
            </ul>
         </li>
      </ul> 
      
       <p class="navbar-text navbar-right head-item">
         <a href="user/myindex" class="navbar-link"><c:out value="${user.username}"/></a>
      </p>  
      
       <p class="navbar-text navbar-right head-item">
         <a href="user/index" class="navbar-link">首页</a>
      </p>

     
   </div>
</nav>
 </body>
</html>