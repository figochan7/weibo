$(function(){
	
	 $('#login-form').formValidation({
		 message: 'This value is not valid',
	        icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            username: {
	                message: 'The username is not valid',
	                validators: {
	                    notEmpty: {
	                        message: '用户名不能为空'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 20,
	                        message: '用户名长度在6-20个字符'
	                    },
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: '用户名只能由字母、数字、下划线和点组成'
	                    }
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    }
	                }
	            }
	        }
   });
	
	$('#login').click(function() {
		$('#login').addClass("disabled");
        $('#login-form').formValidation('validate');
        if($('#login-form').data('formValidation').isValid()!=false){
        	var username=$("#username").val();
        	var password=$("#password").val();
        	
        	$.post("user/login",{
    			"username" : username,
    			"password" : $.md5(password)
    		},function(data,index){
    			if(data.result=="success"){
    				humane.log("登录成功，正在跳转");
    		    	window.location = "user/index";    
    			}
    			else{
    				humane.log(data.result);
    			}
    		},"json");
        }
        setTimeout(function() { $('#login').removeClass("disabled");}, 3000); 
    });

});
