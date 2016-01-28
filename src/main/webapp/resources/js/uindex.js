var bqvalue = new Array("[微笑]","[亲亲]","[偷笑]","[傲慢]","[再见]","[冷汗]",
		"[发呆]","[发怒]","[可怜]","[可爱]","[右哼哼]","[吐]","[吓]","[龇牙]",
		"[咒骂]","[哈欠]","[嘘!]","[困]","[坏笑]","[大兵]","[大哭]","[奋斗]",
		"[委屈]","[害羞]","[尴尬]","[左哼哼]","[得意]","[快哭了]","[惊恐]","[惊讶]");
$(function(){
	
	$("#insert-emoji").popover({
		html:true,
		content: function() {  
            return $("#emoji-content").html();  
         } 
	});
	
	$("#add-group").popover({
		html:true,
		content: function() {  
            return $("#addgroup-content").html();  
         } 
	});
	
	$("#publish").click(function(){
		var text=$("#weibo-content").val();
		var n=140;
		if(text.trim()==""){
			humane.log("您还没有输入任何内容");
	    	return;
		}
		n=getLen(text,n);
		if(n<0){
			humane.log("长度超出限制，请修改后发布");
	    	return;
		}
		//判断是否选择了图片
		var fileLength=$("#fileupload").data('uploadify').queueData.queueLength;
		if(fileLength==0){
			//没有图片的文博
			$.post("weibo/add",{
    			"text" : changetxt(text)
    		},function(data,index){
    			if(data.result=="success"){
    				humane.log("发布成功");
    		    	window.location = "user/index";
    
    			}else{
    				humane.log(data.result);
    			}
    		},"json");
		}else{
			//有图片的微博
			$("#fileupload").uploadify('upload', '*');
		}
	});

	$("#fileupload").uploadify({  
        'buttonText' : '图片...',  
        'height' : 30,  
        'swf' : '/weibo/resources/swf/uploadify.swf',  
         'uploader' : '/weibo/weibo/addPics', 
         'fileSizeLimit' : 10*1024*1024,
        'width' : 80,  
        'fileExt':'*.jpg;*.jpeg;*.gif;*.png',
        'auto':false,  
        'fileObjName'   : 'file',
        'multi':true,
        'queueSizeLimit':9,
        'onUploadStart' : function(file){
        	$("#fileupload").uploadify("settings","formData",{
        		'text':changetxt($("#weibo-content").val()),
        		'username':$("#hideusername").val()
        	});
        },
        'onUploadSuccess' : function(file, data, response) { 
        	if(data=="success"){
        		humane.log("微博发布成功");
        			//window.location="user/index";
        	}
        	else{
        		humane.log("微博发布失败，请稍后再试");
        	}
        }  
		});
	
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
				window.location="user/index";
			}else{
				humane.log(data.result); 
			}
		},"json");
	});
	
	$(".praiseComment").on("click",function(){
		humane.log("点赞成功");
	});
	
});
function  getComments(tar,index){
	 $.getJSON("comment/id/"+tar,{},function(data,status){
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
							window.location="user/index";
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
	  window.location = "user/index"+"?pageIndex="+currentPageIndex+"&itemsPerPage="+itemsPerPage;
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
			window.location="user/index";
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
			window.location="user/index";
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
		"text" : changetxt(text),
		"weiboid":weiboid
	},function(data,index){
		if(data.result=="success"){
			humane.log("转发成功");
			window.location="user/index";

		}else{
			humane.log(data.result); 
		}
	},"json");
}

function addImg(emoji){
	var text=$("#weibo-content").val();
	var title=emoji.title;
	text=text+"["+title+"]";
	
	$("#weibo-content").val(text);
	
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
/* 转换文本框里面的内容,将所有的图像的值,替换为相应的imghtml语言,并且返回 */
function changetxt(str){
	var ustr = new Array();
	var reg = /\[[^\w\s]+?\]/;//正则匹配图像的值
	var strtmp=str;
	while(strtmp.match(reg)!=null){//匹配是否有符合图像值的
		var temp = strtmp.match(reg);
		var repstr = createImg(temp);
		if(repstr!=null){
			strtmp = strtmp.replace(temp,repstr);//将值和生成的值相应的html语言替换
		}else{//如果匹配不到就替换成特定的字符，并保存原来的值，避免进入死循环
			strtmp = strtmp.replace(temp,"#$#");
			ustr.push(temp);
		}
	}
	/* 将原来匹配出来的不符合 图像的值替换回去 */
	if(ustr!=null && ustr.length>0){
		for(var i=0;i<ustr.length;i++){
			strtmp = strtmp.replace("#$#",ustr[i]);
		}
	}
	return strtmp;//返回已经替换之后的记过
}
function createImg(title){
	/* 通过设置的图像值,挨个匹配寻找路径 */
	for(var i=0;i<bqvalue.length;i++){
		if(title == bqvalue[i]){
			break;
		}
	}
	/* 如果找不到值就返回null */
	if(i == bqvalue.length)
		return null;
	/* 如果找到值就拼出img标记并且返回 */
	var str = "<img src='./resources/images/emoji/"+(i+1)+".gif' width='24' height='24' />";
	return str;
}