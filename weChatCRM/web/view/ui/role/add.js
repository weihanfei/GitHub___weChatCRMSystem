  //是否模糊查询
    $(function() {
    	//菜单有关内容不需要改动
    	$(".list-group-item").click(function() {
    		if ($(this).find("ul")) {
    			$(this).toggleClass("tree-closed");
    			if ($(this).hasClass("tree-closed")) {
    				$("ul", this).hide("fast");
    			} else {
    				$("ul", this).show("fast");
    			}
    		}
    	});
    	//点击添加
    	$("#addRoleBtn").click(function(){
    		var roleName=$("#roleName").val();
    		var roleDesc=$("#roleDesc").val();
    		var roleRemark=$("#roleRemark").val();
    		$.ajax({
        		type:"post",
        		dataType:"json",
        		url:"/RoleServlet?method=roleInsertAjax",
        		data:{
        			"roleName":roleName,
        			"roleDesc":roleDesc,
        			"roleRemark":roleRemark
        		},
        		beforeSend : function() {// 加载的操作,发送数据前
    				// 使用插件的效果
    				//旋转圈圈旋转状态一直保存在一个变量中，界面显示为一个圈圈一直转
        			layerLoading = layer.msg("处理中", {
    					icon : 16
    				});
    			},
        		success:function(result){
        			//结束layer的效果
        			layer.close(layerLoading);
        			if(result.success){
        				window.location.href="/DispatcherServlet?method=rolePage";
        				return;
        			}else{
        				layer.msg("添加失败！！！");
        			}
        		}
        	});
    	});
    	
    });