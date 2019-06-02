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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
	function reg(){
		var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
		var reg_num_ = $("#reg_num_").val();
		var trueName = $("#trueName").val();
		var phone = $("#phone").val();
		var sex_ = $("#sex_").val();
		var reg_password = $("#reg_password").val();
		
		if(reg_num_.length<3){
			layer.closeAll();
			layer.msg('账号不能小于3位', {icon: 2});return;
		}
		if(trueName.length<1){
			layer.closeAll();
			layer.msg('请输入真实姓名', {icon: 2});return;
		}
		if(phone.length<11){
			layer.closeAll();
			layer.msg('请输入正确的电话', {icon: 2});return;
		}
		if(reg_password.length<6){
			layer.closeAll();
			layer.msg('请输入6位以上的密码', {icon: 2});return;
		}
		var sex_ = $("#sex_").val();
		
		$.post('user/add', {num_ : reg_num_,trueName:trueName,phone:phone,sex_:sex_,type:2,password :reg_password
		}, function(result) {
			if (result.success) {
				layer.closeAll();
				layer.msg('注册成功', {icon: 1});
				
				$("#reg_btn").attr("onclick","void();"); 
				$("#reg_btn").text("注册成功"); 
			} else {
				layer.closeAll();
				layer.alert(result.msg);
			}
		}, 'json');
	}
	

	function login() {
		var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
		var num_ = $("#num_").val();
		var password = $("#password").val();
		if (num_ == null || num_ == "") {
			layer.closeAll();
			layer.alert('请输入账号!');
			return;
		}
		if (password == null || password == "") {
			layer.closeAll();
			layer.alert('请输入密码!');
			return;
		}
		$.post('user/login', {num_ : num_,password : password
		}, function(result) {
			if (result.success) {
				window.location.href = "admin/main";
			} else {
				layer.closeAll();
				layer.alert(result.msg);
				$("#error").html(result.msg);
			}
		}, 'json');
	}
</script>
<title>汽车租赁管理系统</title>
</head>
<body>
<div style="height: 200px;"></div>

	<div style="margin: 0 auto; width: 800px;">
	
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			<ul class="layui-tab-title">
				<li class="layui-this">登录</li>
				<li>注册</li>
			</ul>
			<div class="layui-tab-content" style="height: 500px;">
				<div class="layui-tab-item layui-show">
					<!-- 登录 -->
					<div class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label">账号</label>
							<div class="layui-input-block">
								<input type="text" id="num_" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-账号" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input  onkeydown="if(event.keyCode==13) login()" type="password" id="password" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-密码" class="layui-input" />
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button id="login_btn" onclick="login()" class="layui-btn" lay-submit="" lay-filter="demo1">登录</button>
							</div>
						</div>
					</div>
					<!-- 登录 -->
				</div>
				
				<div class="layui-tab-item">
					<!-- 注册  -->
					<div class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label">账号</label>
							<div class="layui-input-block">
								<input id="reg_num_" type="text" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-账号" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">真实姓名 </label>
							<div class="layui-input-block">
								<input id="trueName" type="text" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-真实姓名" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">电话 </label>
							<div class="layui-input-block">
								<input id="phone" type="text" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-电话" class="layui-input">
							</div>
						</div>
						
						  <div class="layui-form-item">
							    <label class="layui-form-label">性别</label>
							    <div class="layui-input-block">
							      <select id="sex_" name="interest" lay-filter="aihao">
							        <option value="男"  selected="">男</option>
							        <option value="女">女</option>
							      </select>
							    </div>
						 </div>
							  
  						<div class="layui-form-item">
							<label class="layui-form-label">密码 </label>
							<div class="layui-input-block">
								<input id="reg_password" type="password" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入-密码" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button id="reg_btn" onclick="reg()" class="layui-btn" lay-submit="" lay-filter="demo1">提交注册</button>
							</div>
						</div>
					</div>
					<!-- 注册  -->
				</div>
			</div>
		</div>

	</div>



</body>
<script>
	layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel', 'upload',
			'element' ], function() {
		var laydate = layui.laydate //日期
		, laypage = layui.laypage //分页
		layer = layui.layer //弹层
		, table = layui.table //表格
		, carousel = layui.carousel //轮播
		, upload = layui.upload //上传
		, element = layui.element; //元素操作
	});
</script>
</html>