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
    	//****写代码需要改动的地方
    	pageQuery(1);
    });
 /*   $("tbody .btn-success").click(function() {
    	window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function() {
    	window.location.href = "edit.html";
    });*/
    
    //****写代码需要改动的地方
    function goUpdatePage(id){
    	window.location.href = "/DispatcherServlet?method=userUpdatePage";
    }
    
    //分页查询
    function pageQuery(pageno) {
    	//layerLoging--用来提升用户体验的
    	var loadingIndex = null;//layer.js  等待提示
    	
    	$.ajax({type : "POST",
    				url : "UserServlet?method=pageQueryAjax",
    	  			dataType:"json",
    				data :{//"{"pageno":pageno,"pagesize":2}"
    	    			"pageno" : pageno,
    	    			"pagesize" : 2
    	    		},
    				beforeSend : function() {// 加载的操作,发送数据前
    					// 使用插件的效果
    					//旋转圈圈旋转状态一直保存在一个变量中，界面显示为一个圈圈一直转
    					loadingIndex = layer.msg("处理中", {
    						icon : 16
    					});
    				},// 服务器返回结果后,采取动作
    				//无论成功与否，都要执行success
    				success : function(result) {
    					
    					layer.close(loadingIndex);// 拿到数据了就应该关闭等待效果
    					if (result.success) {
    						// 局部刷新页面数据
    						var tableContent = "";
    						var pageContent = "";

    						// 获取分页对象
    						var userPage =JSON.parse(result.result);
    						var users = userPage.datas;

    						// 循环遍历，将数组中的每一条数据都取出来
    						$.each(users,function(i, user) {
    											// 加入页面格式,使用alt+shif a 列编辑
    											tableContent += "<tr>";
    											tableContent += "<td>" + (i + 1)
    													+ "</td>";
    											tableContent += "<td><input type='checkbox' name='userid' value='"+user.id+"'></td>";
    											tableContent += "<td>"
    													+ user.loginacct + "</td>";
    											tableContent += "<td>"
    													+ user.username + "</td>";
    											tableContent += "<td>" + user.email
    													+ "</td>";
    											tableContent += "<td>";
    											tableContent += "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
    											tableContent += "<button type='button' onclick='goUpdatePage("+user.id+")' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
    											tableContent += "<button type='button' onclick='deleteUser("+user.id+",\" "+user.loginacct+" \" )' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>";
    											tableContent += "</td>";
    											tableContent += "</tr>";
    										});
    						// 页码处理
    						// 有上一页
    						if (pageno > 1) {
    							pageContent += "<li><a href='#' onclick='pageQuery("
    									+ (pageno - 1) + ")'>上一页</a></li>";
    						}
    						// 遍历中间页码
    						for (var i = 1; i <= userPage.totalno; i++) {
    							if (pageno == i) {
    								pageContent += "<li  class='active'><a href='#' onclick='pageQuery("
    										+ i + ")'>" + i + "</a></li>";
    							} else {
    								pageContent += "<li><a href='#' onclick='pageQuery("
    										+ i + ")'>" + i + "</a></li>";
    							}
    						}
    						if (pageno < userPage.totalno) {
    							pageContent += "<li><a href='#' onclick='pageQuery("
    									+ (pageno + 1) + ")'>下一页</a></li>";
    						}
    						$("#userData").html(tableContent);
    						$(".pagination").html(pageContent);
    					} else {

    						layer.msg("用户信息分页查询失败", {
    							time : 1000,
    							icon : 5,
    							shift : 6
    						}, function() {

    						});

    					}
    				}
    			});
    }
