$(function(){

   });
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
			window.location="follow/getFollowed";
		}else{
			humane.log(data.result); 
		}
	},"json");
}

function cancelFollow(id){
	$.post("follow/cancel",{
		"id":id
	},function(data,index){
		if(data.result=="success"){
			humane.log("取消关注成功");
			window.location="follow/getFollowed";
		}else{
			humane.log(data.result); 
		}
	},"json");
}
	