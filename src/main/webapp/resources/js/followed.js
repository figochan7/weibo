$(function(){

   });


function followFan(id){
	$.post("follow/add",{
		"id":id
	},function(data,index){
		if(data.result=="success"){
			humane.log("关注成功");
			window.location="follow/getFans";
		}else{
			humane.log(data.result); 
		}
	},"json");
}
	