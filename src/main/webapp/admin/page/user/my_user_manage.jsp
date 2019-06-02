<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">

<script type="text/javascript" src="/static/jquery.min.js"></script>
<link href="/static/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="/static/layui/css/layui.css">
<script src="/static/layui/layui.js"></script>
<!-- 引入manage 的基础css -->
<script	src="/static/common/manage/manage_base.js"></script>
<link href="/static/common/manage/manage_base.css" rel="stylesheet"/>
<!-- 引入manage 的基础css -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function a(){}
</script>
<title>Insert title here</title>
</head>
<style>
body{
	padding-top: 3px;
}
</style>
<body>
<script>

//用户选中的行ids = 1,2,3,5   len=4
var global_ids;
var global_ids_len;
//用户选中的行ids = 1,2,3,5   len=4
var w ;//窗口的宽
var h ;//窗口的高

//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 reload_data();
}

//相当前刷新  重新加载
function reload_data(){
	table.reload('table', {
		 where: {}
    });
}


function editpassword(id){
	w = 500;
	h = 400;
	checkWindow();
	layer.open({
	  type: 2,
	  title: '修改密码',
	  shadeClose: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/user/setPassword'//iframe的url
	});
}

//打开编辑窗口
function edit(id){
	w = 500;
	h = 400;
	checkWindow();
	layer.open({
	  type: 2,
	  title: '修改基本信息',
	  shadeClose: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/user/editmessage' //iframe的url
	});
}
</script>

<div class="layui-form" style=" ">

<div class="layui-table-toolbar" style="margin-bottom: 3px;">
	<div class="layui-btn-group">
		<button onclick="edit()" class="layui-btn layui-btn-sm"><i class="layui-icon">&#x1002;</i>修改基本信息</button></br>
	  	<button onclick="editpassword()" class="layui-btn layui-btn-sm"><i class="layui-icon">&#x1002;</i>修改密码</button>
 	 </div>
</div>


<script>
		layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
				'upload', 'element' ], function() {
			var laydate = layui.laydate //日期
			, laypage = layui.laypage //分页
			layer = layui.layer //弹层
			, table = layui.table //表格
			, carousel = layui.carousel //轮播
			, upload = layui.upload //上传
			, element = layui.element; //元素操作
		});
</script>
</body>
</html>