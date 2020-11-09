function dologin() {
	  		//非空校验
	  		var loginacct=$("#loginacct").val();//取出登录账号元素的值
	  		//loginacct==null--表单元素的value取值不会是null，取值是空字符串
	  		if(loginacct==""){
//	  			alert("用户的账号不能为空，请输入");使用layer提高用户使用体验
	  			layer.msg("用户的账号不能为空，请输入",{time:1000,icon:5,shift:6},function(){
	  				//alert("回调方法");指代的是关闭的时候会执行一些方法
	  				//{time:1000,icon:5,shift:6}  弹出来的图保留多少时间，icon的第几个，从左往右数
	  			});
	  			return;//如果不想让程序的逻辑往下执行，直接返回return
	  		}
	  		//提交表单
	  		var userpasswd=$("#userpasswd").val();
	  		if(userpasswd==""){
//	  			alert("用户登录密码不能为空,请输入");
	  			layer.msg("用户登录密码不能为空，请输入",{time:1000,icon:5,shift:6},function(){
	  			});
	  			return;
	  		}
	  		//提交表单
//	  		alert("提交表单");
//	  		$("#loginForm").submit();
	  		//使用ajax提交数据
	  		var loadingIndex=null;//layer插件的等待界面的状态变量
	  		$.ajax({/*将data中的数据用post方式提交到url中*/
	  			type:"POST",
	  			dataType:"json",/*要求为String类型的参数，预期服务器返回的数据类型。如果不指定，JQuery将自动根据http包mime信息返回responseXML或responseText，*/
	  			url:"UserServlet?method=loginAjax",
	  			data:{
	  				"loginacct":loginacct,
	  				"userpasswd":userpasswd
	  			},
	  		//考虑到发送数据过程中网络延迟等情况，加入layer的等待效果，用户体验良好
	  			beforeSend:function(){//加载的操作,发送数据前
	  				//使用插件的效果
	  				loadingIndex=layer.msg("处理中",{icon:16});
	  			},//服务器返回结果后,采取动作
	  			//jsondata--"{"success":"true","result":"{"":"","":""}","message":"info---"}"
	  			success:function(result){//请求成功后
	  				//Json.parse(result.result)
	  				layer.close(loadingIndex);//拿到数据了就应该关闭等待效果
	  				if(result.success){/*这里直接重定向到method = mainPage了*/
	  					window.location.href="/DispatcherServlet?method=mainPage";
	  				}else{
	  					layer.msg("用户登录账号或密码不正确，请重新输入",{time:1000,icon:5,shift:6},function(){
	  		  			});	  				
	  				}
	  			}
	  		});//所谓使用json对象，简单理解就是大括号，键值对，各种值的表达形式
}