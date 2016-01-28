$(function(){
	
	$(".comment-btn").click(function(){
		var isforward=$(this).prev().children(".isForward")[0].checked;
		var comment=$(this).parent().prev().val();
		var id=$(this).next().val();
		var forward="no";
		
		if(isforward==true){
			forward="yes";
		}
		if(id==null || id==''){
			humane.log("原微博不存在，请稍后再试");
			return;
		}
		if(comment==null || comment==''){
			humane.log("评论内容不能为空");
			return;
		}
		$.post("comment/add",{
			"id":id,
			"forward":forward,
			"comment":comment
		},function(data,index){
			if(data.result=="success"){
				humane.log("评论成功");
				window.location="user/myindex";
			}else{
				humane.log(data.result); 
			}
		},"json");
	});

	
});
function  getComments(tar,index){
	
	 $.getJSON("comment/id/"+tar,{},function(data,status){
		 console.log(data);
		 $("#comment"+index+" .comment-body").empty();
		 for(var i=0;i<data.length;i++){
			var newdate= new Date(parseInt(data[i].date)).toLocaleString().replace(/:\d{1,2}$/,' ');
			 
			//console.log($("#comment"+index+" .comment-body .comment-item"));
			 var text="<div class='row comment-item'><div class='col-xs-2 text-center'><img src='./resources/images/avatar/"+data[i].user.avatar+
			 	"'  class='img-rounded img-thumbnail' width='40' height='40'></div><div class='col-xs-10'><a href='user/oindex?id="+data[i].user.id+
			 	"' class='text-primary'>"+data[i].user.username+"：</a> "+data[i].content+"<br><span class='text-muted'>"+newdate+
			 	"</span><input type='hidden' class='hiddencommentid' value='"+data[i].id+"'><a  href='javascript:void(0)'  class='pull-right praisecomment'><span class='glyphicon glyphicon-thumbs-up'>赞"+
			 	data[i].praiseCount+"</span></a></div></div>";
			 $("#comment"+index+" .comment-body").prepend(text);


			 $(".praisecomment").on("click",function(){
					var id=$(this).prev().val();
					$.post("comment/praise",{
						"id":id
					},function(data,index){
						if(data.result=="success"){
							humane.log("给评论点赞成功");
							window.location="user/myindex";
						}else{
							humane.log(data.result); 
						}
					},"json");
				});
		 }
	  });   
}


function prevPage(){
	  var currentPageIndex = $("#currentPageIndex").val();
	  if(currentPageIndex!="1"){
		  getOpenClassGroupByPage(parseInt(currentPageIndex)-1);
	  }
	  else{
		  humane.log("已经是第一页");
	  } 
}
function nextPage(){
	  var currentPageIndex = $("#currentPageIndex").val();
	  var totalPages = $("#totalPages").val();
	  if(currentPageIndex!=totalPages){
		  getOpenClassGroupByPage(parseInt(currentPageIndex)+1);
	  }
	  else{
		  humane.log("已经是最后页");
	  }
}
function getOpenClassGroupByPage(currentPageIndex){
	  var itemsPerPage = 10;
	
	  window.location = "user/myindex?pageIndex="+currentPageIndex+"&itemsPerPage="+itemsPerPage;
}
function addGroup(){
	var groupname=$("#groupname").val();
	if(groupname==null || groupname==''){
		humane.log("请输入分组名字");	
		return;
	}
	$.post("group/add",{
		"groupname":groupname
	},function(data,index){
		if(data.result=="success"){
			humane.log("添加分组成功");
			window.location="user/myindex";
		}else{
			humane.log(data.result); 
		}
	},"json");
}
function praise(id){
	

	$.post("weibo/praise",{
		"weiboid":id
	},function(data,index){
		if(data.result=="success"){
			humane.log("点赞成功");
			window.location="user/myindex";
		}else{
			humane.log(data.result); 
		}
	},"json");
}
function forwardmodal(weiboid){
	$("#hideforwardid").val(weiboid);	
}
function forward(){
	var text=$("#forward-content").val();
	var weiboid=$("#hideforwardid").val();
	if(weiboid==null || weiboid==''){
		humane.log("转发失败，请稍后再试");	
		return;
	}
	if(text==null || text==''){
		humane.log("请输入转发内容");		
		return;
	}

	
	$.post("weibo/forward",{
		"text" : text,
		"weiboid":weiboid
	},function(data,index){
		if(data.result=="success"){
			humane.log("转发成功");
			window.location="user/myindex";

		}else{
			humane.log(data.result); 
		}
	},"json");
}
/* 动态的计算文本框里面已经输入的数量  */
function calNum(txtobj,divobj,fg){
	var str = txtobj.value;
	var n = 140;//初始化数字
	n=getLen(str,n);
	if(n<0){
		divobj.style.color = "#f00";//设置如果超了，变背景色为红色
	}else{
		if(fg == 1)//如果标记为1设置白色
			divobj.style.color = "#FFF";
		else//如果标记为0 设置为黑色
			divobj.style.color = "#000";
	}
	/* 将计算好的数存入div中 */
	if(n<0){
		divobj.innerHTML = 0 ;
	}else{
		divobj.innerHTML = n ;
	}    
}

function getLen(str,n){
	var tmp = str.replace(/[^\w\s]/g,"");//将文本框里面的字符中的中文全部替换成空的
	if(tmp!=null)
		n = n-(str.length-tmp.length) - Math.floor(0.5*tmp.length);//计算，一个中文是1个字符，2个英文是1个
    else
		n = n - Math.floor(0.5*str.length);//计算，一个中文是1个字符，2个英文是1个
	return n;
}
