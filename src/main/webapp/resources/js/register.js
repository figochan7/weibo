$(function(){
	$("#birthday").datetimepicker({		
		lang:'ch',
		timepicker:false,
		format:'Y-m-d',	
		onChangeDateTime:function(){
			 $('#reg-form').formValidation('revalidateField', 'birthday');
		}
	});
	
	
	 $('#reg-form').formValidation({
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
                   },
                   remote: {
                       url: 'user/check',
                       message: '用户名已经存在'
                   }
               }
           },
           telephone: {
               validators: {
            	   notEmpty: {
                       message: '手机号码不能为空'
                   },
                   phone: {
                       message: '不是有效的手机号码',
                       country: 'CN'
                   }
               }
           },
           birthday: {
               validators: {
            	   notEmpty: {
                       message: '生日不能为空'
                   }
               }
           },
           email: {
               validators: {
            	   notEmpty: {
                       message: '邮箱不能为空'
                   },
                   emailAddress: {
                       message: '不是有效的邮箱地址'
                   }
               }
           },
           password: {
               validators: {
                   notEmpty: {
                       message: '密码不能为空'
                   }
               }
           },
           repassword: {
               validators: {
                   notEmpty: {
                       message: '密码不能为空'
                   },
                   identical: {
                       field: 'password',
                       message: '两次输入密码不一致'
                   }
               }
           }
       }
   });
	
	$('#signup').click(function() {
		$('#signup').addClass("disabled");
		$('#reg-form').formValidation('validate');
        if($('#reg-form').data('formValidation').isValid()!=false){
        	var username=$("#username").val();
        	var telephone=$("#telephone").val();
        	var email=$("#email").val();
        	var birthday=$("#birthday").val();
        	var password=$("#password").val();
        	
        	$.post("user/add",{
    			"username" : username,
    			"password" : $.md5(password),
    			"birthday" : birthday,
    			"telephone" : telephone,
    			"email" : email
    		},function(data,index){
    			if(data.result=="success"){
    				humane.log("注册成功，正在返回登录页面");
    		    	window.location = "login.jsp";
    
    			}
    			else{
    				humane.log(data.result);
    			}
    		},"json");
        }
        setTimeout(function() { $('#signup').removeClass("disabled");}, 3000); 
       
        
    });

    $('#reset').click(function() {
        $('#reg-form').data('formValidation').resetForm(true);
    });
});
