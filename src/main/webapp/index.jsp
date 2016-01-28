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
<link rel="stylesheet" type="text/css" href="/weibo/resources/css/uploadify.css"/>

 </head>
 <body>
<div id="page">
 <div id="head">
 <jsp:include page="head.jsp" />
 </div>
 <div class="row ">
  <div class="col-md-2 container ">
  	<div class="container index">

      <ul class="nav navbar-left navbar-default left-ul">
         <li class="active"><a href="user/index">首页</a></li>
         <li class=""><a href="message/get" ><span class="badge pull-right"><c:out value="${unReadCount}"/></span>
         消息</a></li>
         <li class=""><a href="follow/getFollowed">我关注的人</a></li>
         <li class=""><a href="follow/getFans">关注我的人</a></li>

      </ul>

  	</div>
  	<div class="container group">
  	<ul class="nav navbar-left navbar-default left-ul">
         <li class="active"><a href="user/index">全部</a></li>
         <c:forEach items="${groupList }" var="group">
         	<li class=""><a href="follow/getFollowed?groupname=${group.name}">${group.name}</a></li>
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
  <div class="col-md-7 bg-primary content">
  	<div class="public panel panel-default">
  	<div class="panel-heading">
  	想告诉大家的事？<span class="pull-right">还可以输入<strong><span id="counter1" class="text-danger">140</span></strong>个字</span>
  	</div>
  	<div class="panel-body">
  		<textarea class="form-control" rows="3" id="weibo-content" onkeyup="calNum(this,counter1,0)" ></textarea>
  		 <button type="button" id="publish" class="btn btn-warning">&nbsp;&nbsp;&nbsp;发布&nbsp;&nbsp;&nbsp;</button>
  		 <input type="hidden" id="hideusername" value="<%=session.getAttribute("username") %>" /> 
  		 <div class="pull-right">
  		 <button type="button" id="insert-emoji" class="btn btn-default insert-btn" title="选择插入的表情"  
      data-container="body" data-toggle="popover" data-placement="bottom" 
      data-content="">
     		 <span class="glyphicon glyphicon-dashboard"></span>表情
   		</button>
   		<input type="file" name="file" id="fileupload" multi="true" />
   		</div>
   		<div id="emoji-content" class="hide">  
   			<div class="row">
   				<div class="col-xs-2"><img src="./resources/images/emoji/1.gif"  class="center-block  emoji" title="微笑" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/2.gif"  class="center-block  emoji" title="亲亲" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/3.gif"  class="center-block  emoji" title="偷笑" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/4.gif"  class="center-block  emoji" title="傲慢" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/5.gif"  class="center-block  emoji" title="再见" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/6.gif"  class="center-block  emoji" title="冷汗" onclick="addImg(this);"></div>

   			</div>
   			<div class="row">
   				<div class="col-xs-2"><img src="./resources/images/emoji/7.gif"  class="center-block  emoji" title="发呆" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/8.gif"  class="center-block  emoji" title="发怒" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/9.gif"  class="center-block  emoji" title="可怜" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/10.gif"  class="center-block  emoji" title="可爱" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/11.gif"  class="center-block  emoji" title="右哼哼" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/12.gif"  class="center-block  emoji" title="吐" onclick="addImg(this);"></div>
   			</div>
   			<div class="row">
   				<div class="col-xs-2"><img src="./resources/images/emoji/13.gif"  class="center-block  emoji" title="吓" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/14.gif"  class="center-block  emoji" title="龇牙" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/15.gif"  class="center-block  emoji" title="咒骂" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/16.gif"  class="center-block  emoji" title="哈欠" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/17.gif"  class="center-block  emoji" title="嘘" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/18.gif"  class="center-block  emoji" title="困" onclick="addImg(this);"></div>
   			</div>
   			<div class="row">
   				<div class="col-xs-2"><img src="./resources/images/emoji/19.gif"  class="center-block  emoji" title="坏笑" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/20.gif"  class="center-block  emoji" title="大兵" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/21.gif"  class="center-block  emoji" title="大哭" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/22.gif"  class="center-block  emoji" title="奋斗" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/23.gif"  class="center-block  emoji" title="委屈" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/24.gif"  class="center-block  emoji" title="害羞" onclick="addImg(this);"></div>
   			</div>
   			<div class="row">
   				<div class="col-xs-2"><img src="./resources/images/emoji/25.gif"  class="center-block  emoji" title="尴尬" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/26.gif"  class="center-block  emoji" title="左哼哼" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/27.gif"  class="center-block  emoji" title="得意" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/28.gif"  class="center-block  emoji" title="快哭了" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/29.gif"  class="center-block  emoji" title="惊恐" onclick="addImg(this);"></div>
   				<div class="col-xs-2"><img src="./resources/images/emoji/30.gif"  class="center-block  emoji" title="惊讶" onclick="addImg(this);"></div>
   			</div>
           
         </div>    		
  	</div>
  	</div>
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
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发表于：<fmt:formatDate value="${weibo.date}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
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
  <div class="col-md-3 ">
  	<div class="bg-primary panel" id="info"><br>
  	<img src="./resources/images/avatar/<c:out value="${user.avatar}"/>"  class="img-circle img-thumbnail center-block  user-avatar">
  	<h4 id="username" class="text-center "><strong><c:out value="${user.username}"/></strong></h4><hr>
  	<div class="row">
  	<div class="col-md-4 text-center"><h4><c:out value="${user.followCount}"/></h4>关注</div>
  	<div class="col-md-4 text-center"><h4><c:out value="${user.fansCount}"/></h4>粉丝</div>
  	<div class="col-md-4 text-center"><h4><c:out value="${user.weiboCount}"/></h4>微博</div>
  	</div>
  	</div>
  	<div class="hot panel panel-default">
  		<div class="panel-heading">
  			热门微博
  		</div>
  	<div class="panel-body">
  		<c:forEach items="${hotWeiboList }" var="hotweibo" varStatus="status">
			<a class="list-group-item hot-weibo" href="user/oindex?id=${hotweibo.user.id }">${status.index+1}. ${hotweibo.content} 
			<br><span >评论：${hotweibo.commentCount}</span>
			</a>
		</c:forEach>
  	</div>
  	</div>
  	
  	<div class="star panel panel-default">
  		<div class="panel-heading">
  			微博红人
  		</div>
  	<div class="panel-body">
  		<c:forEach items="${hotUserList }" var="hotuser" varStatus="status">
			<a class="tooltip-test list-group-item"  data-toggle="tooltip"  title="${hotuser.introduction}"  href="user/oindex?id=${hotuser.id }">${status.index+1}. ${hotuser.username} <span class="pull-right">粉丝：${hotuser.fansCount}</span></a>
			
		</c:forEach>
  	</div>
  	</div>
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
 <script type="text/javascript" src="/weibo/resources/js/jquery.uploadify.js"></script>

<script src="/weibo/resources/js/uindex.js"></script>

</html>