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
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/global.css"/>
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/index.css"/>


 </head>
 <body>
<div id="page">
 <div id="head">
 <jsp:include page="head.jsp" />
 </div>
<div class="row o-index-bg">
	<div class="col-md-4 text-center"></div>
	<div class="col-md-4 text-center"><br><br>
		<img class="img-circle img-thumbnail " alt="" src="./resources/images/avatar/${user.avatar }" width="100" height="100">
		<br><h3 class=""><strong>${user.username }</strong></h3>
		<c:if test="${user.introduction==null }">
			一句话介绍自己，让别人更了解自己
		</c:if>
		<c:if test="${user.introduction!=null }">
			${user.introduction }
		</c:if><br><br>
		
		
	</div>
	<div class="col-md-4 text-center"></div>
</div>
 <div class="row ">
  <div class="col-md-4 container ">

  		<div class="row well" style="margin-left: 3px;margin-right: 3px;">
  		<div class="col-xs-4 text-center o-item">
  			${user.weiboCount }<br>微博
  		</div>
  		<div class="col-xs-4 text-center o-item">
  			${user.followCount }<br>关注
  		</div>
  		<div class="col-xs-4 text-center o-item">
  			${user.fansCount }<br>粉丝
  		</div>
  		
  	</div>
  	
  		<div class="container index">

      <ul class="nav navbar-left navbar-default left-ul">
          <li class="active"><a href="user/index">首页</a></li>
         <li class=""><a href="message/get" ><span class="badge pull-right"><c:out value="${unReadCount}"/></span>
         消息</a></li>
         <li class=""><a href="follow/getFollowed">我关注的人</a></li>
         <li class=""><a href="follow/getFans">关注我的人</a></li>

      </ul>

  	</div><br>
	<div class="container index">
		<div class="panel panel-default">
			<div class="panel-heading">
  				个人信息
  			</div>
  			<div class="panel-body">
  				<div class="panel panel-default">
  					<div class="panel-heading">
  						生日
  					</div>
  					<div class="panel-body">
  						<fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日 E"/>
  					</div>
  				</div>
  				<div class="panel panel-default">
  					<div class="panel-heading">
  						电话
  					</div>
  					<div class="panel-body">
  						${user.telephone }
  					</div>
  				</div>
  				<div class="panel panel-default">
  					<div class="panel-heading">
  						邮箱
  					</div>
  					<div class="panel-body">
  						${user.email }
  					</div>
  				</div>
  				<div class="panel panel-default">
  					<div class="panel-heading">
  						个人简介
  					</div>
  					<div class="panel-body">
  						<c:if test="${user.introduction==null }">
							一句话介绍自己，让别人更了解自己
						</c:if>
							<c:if test="${user.introduction!=null }">
							${user.introduction }
						</c:if>
  					</div>
  				</div>
  			</div>
		</div>
    

  	</div>
  	<div class="container group">
  	<ul class="nav navbar-left navbar-default left-ul">
        <li class="active"><a href="javascript:volid(0)">全部</a></li>
         <c:forEach items="${groupList }" var="group">
         	<li class=""><a href="javascript:volid(0)">${group.name}</a></li>
		</c:forEach>
		 <li class="text-center"> 
		 <button type="button" class="btn btn-success"  id="add-group" data-toggle="modal" 
  					 data-target="#group-modal">
     			 <span class="glyphicon glyphicon-plus">添加分组</span>
  		 </button>
		</li>

      </ul>

      </div>			
  	</div>

  <div class="col-md-8 bg-primary content">

   	<c:forEach items="${weiboList }" var="weibo" varStatus="status"> 
	  	<div class="public panel panel-default">
	  	<div class="panel-body">
	  	<div class="row weibo-head">
	  		
	  		<a href="user/oindex?id=${weibo.user.id }">
	  		<img src="./resources/images/avatar/${ weibo.user.avatar}" class="img-circle img-thumbnail pull-left" width="60" height="60" alt="">
	  		<h4 ><strong>${ weibo.user.username}</strong></h4>
	  		</a>
	  	</div>
	  		<div class="row weibo-con-div">
	  		${weibo.content}
	  		<c:if test="${weibo.picList.size()!=0 }">
	  		
	  		<div class="row img-row">
	  			<c:forEach items="${weibo.picList }" var="pic" > 
	  			<img alt="显示图片" class="weibo-img" src="./resources/upload/${pic.savename}" width="100" height="100">
	  		
	  			</c:forEach>
	  		</div>
	  		</c:if>
	  		<c:set var="a" scope="request" value="${weibo.source}"/>
	  		<c:import url="recursion.jsp"></c:import>
	  		</div>
	  		
	  		<div class="row text-muted">
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发表于：${weibo.date}
	  		</div>
	  		<div class="row">
	  			<div class="col-xs-4 text-center operate">
	  				<a  href="javascript:void(0)"  data-toggle="modal" 
  					 data-target="#forward-modal" onclick="forwardmodal('${weibo.id}');">
   						转发${weibo.forwardCount}
					</a>
	  			</div>
	  			<div class="col-xs-4 text-center operate">
	  			<a  href="javascript:void(0)"  data-toggle="collapse" 
  					 data-target="#comment${status.index }" onclick="getComments('${weibo.id}','${status.index }')">
   						评论${weibo.commentCount}
					</a>
					
	  			</div>
	  			<div class="col-xs-4 text-center operate">
	  			 <a  href="javascript:void(0)" onclick="praise('${weibo.id}')">
      				<span class="glyphicon glyphicon-thumbs-up">赞${weibo.praiseCount}</span>
  				 </a>
	  			
	  			</div>
	  		</div>
	  		<div class="row collapse comment-div" id="comment${status.index }">
	  			<span class="pull-right">还可以输入<strong><span id="counter3" class="text-danger">140</span></strong>个字</span>
            <textarea class="form-control comment-content" rows="1" id="" onkeyup="calNum(this,counter3,0)" ></textarea>
	  		<div class="checkbox">
   				<label><input type="checkbox" value="" class="isForward">评论并转发</label>
				<button type="button" class="btn btn-warning pull-right comment-btn" >
               		评论
            </button>
            <input type="hidden" value="${weibo.id }">
			</div>	
			
			<div class="row comment-body">
	  			
	  		</div>  
	  				
	  		</div>
	  		
	  	</div>
	  	</div>
	  	
 	  	</c:forEach> 
 	  	<input type="hidden" id="currentPageIndex" value="${pageIndex}">
 	  	<input type="hidden" id="totalPages" value="${totalPages}">
 	  	<ul class="pager">
		  <li><a href="javascript:void(0)" onclick="prevPage();">Previous</a></li>
		  <li><a href="javascript:void(0)" onclick="nextPage();">Next</a></li>
		</ul>
  </div>

  </div>


 <div id="foot">
  <jsp:include page="foot.jsp" />
 </div>
 </div>
 
<div class="modal fade" id="forward-modal" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" >
             	转发微博
            </h4>
         </div>
         <div class="modal-body">
         <input type="hidden" id="hideforwardid">
         <span class="pull-right">还可以输入<strong><span id="counter2" class="text-danger">140</span></strong>个字</span>
            <textarea class="form-control" rows="3" id="forward-content" onkeyup="calNum(this,counter2,0)" ></textarea>
         </div>
         <div class="modal-footer">
     
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="forward()">
               		转发
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<div class="modal fade" id="group-modal" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" >
             	添加分组
            </h4>
         </div>
         <div class="modal-body">
        
            <input type="text" id="groupname" class="form-control">
         </div>
         <div class="modal-footer">
     
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="addGroup()">
               		添加
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

 </body>
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/original.css"/>
<script type="text/javascript" src="/weibo/resources/js/humane.js"></script>
 

<script src="/weibo/resources/js/myindex.js"></script>

</html>