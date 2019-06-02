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
<!--添加  vue.js 支持加载-->
<script src="/static/vue/vue.min.js"></script>
<!--添加  vue.js 支持加载-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(function(){
});

var save_url = '${save_url}';

function save() {
	//loading
	var index = layer.load(1, {
		shade : [ 0.1, '#fff' ]
		//0.1透明度的白色背景
	});
	var shengchanDate = $("#shengchanDate").val();
	var img_url = $("#img_url").val();
	$.post(save_url, {
		bianhao:app.bianhao,
		pinpai:app.pinpai,
		chepai:app.chepai,
		chexing:app.chexing,
		tupian:img_url,
		shengchanDate:shengchanDate,
		danjia:app.danjia,
		kucun:app.kucun
	}, function(result) {
		if (result.success) {
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
			window.parent.closeDlg(result.msg);
		} else {
			layer.closeAll();
			layer.msg(result.msg);
		}
	}, 'json');
}
</script>
<style>
html, body {
}

.layui-form-item {
	margin-bottom: 3px;
}
</style>
<title>Insert title here</title>
</head>
<body id="app">
<div style="padding: 10px;">
		<form class="layui-form layui-form-pane">
		  
		  <div class="layui-form-item">
				<label class="layui-form-label">车辆编号</label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="bianhao"
						value="${car.bianhao}" placeholder="请输入 车辆编号" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">车辆品牌</label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="pinpai"
						value="${car.pinpai}" placeholder="请输入 车辆品牌" class="layui-input">
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">车牌</label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="chepai"
						value="${car.chepai}" placeholder="请输入  车牌" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">车型</label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="chexing"
						value="${car.chexing}" placeholder="请输入 车型" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">图片</label>
				<div class="layui-input-block">	
					<button type="button" class="layui-btn" id="tupian1">
					  <i class="layui-icon">&#xe67c;</i>上传图片
					</button>	  
		            <input type="hidden" class="layui-input" id="img_url"  name="img" />		            
		            <div class="layui-upload-list">
		                <img class="layui-upload-img" width="300px" height="300px" id="demo1" src="upload/${car.tupian}" onerror="this.src='/static/images/base/upload_hover.png';this.onerror=null"/>
		                <p id="demoText" font color="red">==如果要更改车辆信息需重新上传图片！否则图片将清除==</p>
		            </div>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">生产日期</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" value="<fmt:formatDate value="${car.shengchanDate}" pattern="yyyy-MM-dd "/>" id="shengchanDate" placeholder="yyyy-MM-dd">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">价格</label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="danjia"
						value="${car.danjia}" placeholder="请输入 价格" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">库存数量 </label>
				<div class="layui-input-block">
					<input type="text"   autocomplete="off" v-model="kucun"
						value="${car.kucun}" placeholder="请输入 库存数量" class="layui-input">
				</div>
			</div>

		</form>
		<div class="site-demo-button" style="margin-top: 20px;">
			<button id="save" onclick="save()"
				class="layui-btn site-demo-layedit" data-type="content">${btn_text }</button>
		</div>
	</div>
</body>
<script>
var app = new Vue({
	el : '#app',
	data : {
	}
});
</script>

<script>
layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
			'upload', 'element' ], function() {
		var laydate = layui.laydate //日期
		, laypage = layui.laypage //分页
		layer = layui.layer //弹层
		, table = layui.table //表格
		, carousel = layui.carousel //轮播
		, upload = layui.upload //上传
		,form = layui.form
		, element = layui.element; //元素操作
		
		
		 //常规用法
		  laydate.render({
		    elem: '#shengchanDate'
		  });
		
		  element.render({
		    elem: '#img_url'
		  });
		  
		  layui.use('upload', function(){
		        var upload = layui.upload
		            , $ = layui.jquery;
		        var uploadInst = upload.render({
		            elem: '#tupian1' //绑定元素
		            ,url: /*[[@{/upload/img}]]*/'admin/car/upload/image' //上传接口
		            ,before: function(obj){
		                //预读本地文件示例，不支持ie8
		                obj.preview(function(index, file, result){
		                    $('#demo1').attr('src', result); //图片链接（base64）
		                });
		            }
		            ,done: function(res){
		                //如果上传失败
		                if(res.code > 0){
		                    return layer.msg('上传失败');
		                }
		                //上传成功
		                
		                document.getElementById("img_url").value = res.url;

		            }
		            ,error: function(){
		                //演示失败状态，并实现重传
		                var demoText = $('#demoText');
		                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
		                demoText.find('.demo-reload').on('click', function(){
		                    uploadInst.upload();
		                });
		            }
		        });
		    });
		
});
</script>
</html>