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
           }
       }
   });
	 
	 $('#pwd-form').formValidation({
	       message: 'This value is not valid',
	       icon: {
	           valid: 'glyphicon glyphicon-ok',
	           invalid: 'glyphicon glyphicon-remove',
	           validating: 'glyphicon glyphicon-refresh'
	       },
	       fields: {
	           password: {
	               validators: {
	            	   notEmpty: {
	                       message: '旧密码不能为空'
	                   }
	               }
	           },
	           newpassword: {
	               validators: {
	            	   notEmpty: {
	                       message: '新密码不能为空'
	                   }
	               }
	           },
	           repassword: {
	               validators: {
	            	   notEmpty: {
	                       message: '新密码不能为空'
	                   },
	                   identical: {
	                       field: 'newpassword',
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
        	var telephone=$("#telephone").val();
        	var email=$("#email").val();
        	var birthday=$("#birthday").val();
        	var introduction=$("#introduction").val();
        	console.log(introduction);
        	$.post("setting/update",{
    			"birthday" : birthday,
    			"telephone" : telephone,
    			"email" : email,
    			"introduction":introduction
    		},function(data,index){
    			if(data.result=="success"){
    				humane.log("修改成功");
    		    	window.location = "setting/info";    
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
    
    $('#updatepwd').click(function() {
		$('#updatepwd').addClass("disabled");
		$('#pwd-form').formValidation('validate');
        if($('#pwd-form').data('formValidation').isValid()!=false){

        	var password=$("#password").val();
        	var newpassword=$("#newpassword").val();
        	if(password==newpassword){
        		humane.log("新旧密码不能一样");
        		return;
        	}
        	$.post("setting/pwd",{
        		"password":$.md5(password),
        		"newpassword":$.md5(newpassword)
    		},function(data,index){
    			if(data.result=="success"){
    				humane.log("修改成功");
    		    	window.location = "setting/info";    
    
    			}
    			else{
    				humane.log(data.result); 
    			}
    		},"json");
        }
        setTimeout(function() { $('#updatepwd').removeClass("disabled");}, 3000); 
       
        
    });

});
