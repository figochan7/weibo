<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--用于递归输出微博转发的路径  -->
<c:if test="${a!=null }">
	<c:if test="${a.source==null }">
		<div class="row img-row">
	  			<a href="user/oindex?id=${a.user.id }">	  		
	  		<h4 ><strong>@${ a.user.username}</strong></h4>
	  		</a>
	  		&nbsp;&nbsp;&nbsp;&nbsp;${a.content}
	  		<c:if test="${a.picList.size()!=0 }">
	  		
	  		<div class="row img-row">
	  			<c:forEach items="${a.picList }" var="pic" > 
	  			<img alt="显示图片" class="weibo-img" src="./resources/upload/${pic.savename}" width="100" height="100">
	  		
	  			</c:forEach>
	  		</div>
	  		</c:if>
	  		<c:set var="a" scope="request" value="${a.source}"/>
	  		<c:import url="recursion.jsp"></c:import>
	  		</div>
	</c:if>
		<c:if test="${a.source!=null }">
		//<a href="user/oindex?id=${a.user.id }">@${a.user.username }: </a>
	  		${a.content}
		</c:if>	  		
	  		<c:set var="a" scope="request" value="${a.source}"/>
	  		<c:import url="recursion.jsp"></c:import>
</c:if>
