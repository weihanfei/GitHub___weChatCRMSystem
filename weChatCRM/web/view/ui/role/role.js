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
    	
    	$("#queryBtn").click(function(){
    		var search=$("#queryText").val();
    		$.ajax({
        		type:"post",
        		dataType:"json",
        		url:"/RoleServlet?method=pageQueryForParamsAjax",
        		data:{
        			"pageno" : 1,//当前页
        			"pagesize" : 2,//当前页的条数
        			"search":search
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
        			// 局部刷新页面数据
        			var tableContent="";//列表
        			var pageContent="";//页码
        			var page =JSON.parse(result.result);
    				var datas = page.datas;
        			$.each(datas,function(i,target){
        				tableContent+=renderPage(target);
        			});
        			$("#roleData").html(tableContent);
        			//////////////////////////////////////
        			pageContent=renderPageiation(1,page);
        			$(".pagination").html(pageContent);
        		}
        	});
    	});
    });
    
    //分页查询
    function pageQuery(pageno) {
    	var layerLoading=null;
    	$.ajax({
    		type:"post",
    		dataType:"json",
    		url:"/RoleServlet?method=pageQueryAjax",
    		data:{
    			"pageno" : pageno,//当前页
    			"pagesize" : 2//当前页的条数
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
    			// 局部刷新页面数据
    			var tableContent="";//列表
    			var pageContent="";//页码
    			var page =JSON.parse(result.result);
				var datas = page.datas;
    			$.each(datas,function(i,target){
    				tableContent+=renderPage(target);
    			});
    			$("#roleData").html(tableContent);
    			//////////////////////////////////////
    			pageContent=renderPageiation(pageno,page);
    			$(".pagination").html(pageContent);
    		}
    	});

    }
    //页码渲染
    function renderPageiation(pageno,page){
    	var pageContent="";
		// 页码处理
		// 有上一页
		if (pageno > 1) {
			pageContent += "<li><a href='#' onclick='pageQuery("
					+ (pageno - 1) + ")'>上一页</a></li>";
		}
		// 遍历中间页码
		for (var i = 1; i <= page.totalno; i++) {
			if (pageno == i) {
				pageContent += "<li  class='active'><a href='#' onclick='pageQuery("
						+ i + ")'>" + i + "</a></li>";
			} else {
				pageContent += "<li><a href='#' onclick='pageQuery("
						+ i + ")'>" + i + "</a></li>";
			}
		}
		if (pageno < page.totalno) {
			pageContent += "<li><a href='#' onclick='pageQuery("
					+ (pageno + 1) + ")'>下一页</a></li>";
		}
		return pageContent;
    }
    
    //列表渲染
    function renderPage(role){
    	var tableContent="";
		tableContent+="<tr>";
 	  	tableContent+="<td>"+role.id+"</td>";
 	  	tableContent+="<td><input type='checkbox' name='' value=''></td>";
 	 	tableContent+="<td>"+role.roleName+"</td>";
 	 	tableContent+="<td>"+role.roleDesc+"</td>";
 	 	tableContent+="<td>"+role.roleRemark+"</td>";
 	 	tableContent+="<td>";
		tableContent+="<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
		tableContent+="<button type='button' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
		tableContent+="<button type='button' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>";
		tableContent+="	</td>";
 	    tableContent+="</tr>";
 	    return tableContent;
    }
