$(function(){

   });


function markRead(id){
	$.post("message/mark",{
		"id":id
	},function(data,index){
		if(data.result=="success"){
			humane.log("标记已读成功");
			window.location="message/get";
		}else{
			humane.log(data.result); 
		}
	},"json");
}

function deleteMessage(id){
	$.post("message/delete",{
		"id":id
	},function(data,index){
		if(data.result=="success"){
			humane.log("删除信息成功");
			window.location="message/get";
		}else{
			humane.log(data.result); 
		}
	},"json");
}
	