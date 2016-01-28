<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  <!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
    <link rel="stylesheet" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css" />
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	  <script src="./resources/js/md5.js"></script>
<link rel="stylesheet" type="text/css" href="./resources/css/global.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/follow.css"/>
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/original.css"/>
<script type="text/javascript" src="/weibo/resources/js/humane.js"></script>
<script src="./resources/js/message.js"></script>
 </head>
 <body>
 <div id="page">
 <div id="head">
 <jsp:include page="head.jsp" />
 </div>


 <div class="row ">
  <div class="col-md-4 container ">
  	<div class="container index">

      <ul class="nav navbar-left navbar-default left-ul">
          <li class="active"><a href="user/index">首页</a></li>
         <li class=""><a href="message/get" ><span class="badge pull-right"><c:out value="${unReadCount}"/></span>
         消息</a></li>
         <li class=""><a href="follow/getFollowed">我关注的人</a></li>
         <li class=""><a href="follow/getFans">关注我的人</a></li>

      </ul>

  	</div>
  	
  </div>
  <div class="col-md-8 bg-primary content">
  	<div class="public panel panel-default">
  	<div class="panel-heading">
  	消息${messageCount}
  	</div>
  	<div class="panel-body">
  		<c:forEach items="${messageList }" var="message">
  			<div class="well">
  				<div class="row">
  					<div class="col-md-2">
  						<img src="./resources/images/avatar/${ message.from.avatar}" class="img-round img-thumbnail text-center" width="70" height="70" alt="">
  					</div>
  					<div class="col-md-8">
  						<a href="user/oindex?id=${message.from.id }">
	  				  		<h4 ><strong>${message.from.username}</strong></h4>
	  					</a>
	  					
	  					<div>${message.content}</div>
	  					<div>
	  					发送时间：${message.date }
	  					</div>
  					</div>
  					<div class="col-md-2">
  						<c:if test="${message.isRead==0 }">
  						<a type="button" onclick="markRead('${message.id }')" class="btn btn-success">标记已读</a>
  						</c:if>
  						<c:if test="${message.isRead==1 }">
  						<a type="button" onclick="deleteMessage('${message.id }')" class="btn btn-danger">删除消息</a>
  						</c:if>
  					</div>
  				</div>
  			</div>
  		</c:forEach>
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