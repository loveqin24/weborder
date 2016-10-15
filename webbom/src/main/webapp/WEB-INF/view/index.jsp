<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<title>在线订单系统</title>
		<meta name="description" content="description">
		<meta name="author" content="Leo">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath()%>/resources/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="<%=request.getContextPath()%>/resources/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/select2/select2.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/justified-gallery/justifiedGallery.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/css/style_v2.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/resources/plugins/chartist/chartist.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
				<script src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery.min.js"></script>
		<script	>
			$("#part1").click(function(){
				var url = "/part/parts";
				var data = {type:1};
				$.ajax({
					type : "get",
					async : false,  //同步请求
					url : url,
					data : data,
					timeout:1000,
					success:function(dates){
						//alert(dates);
						$("#main").html(dates);//要刷新的div
					},
					error: function() {
						// alert("失败，请稍后再试！");
					}
				});
			});
		</script>

	</head>
<body>
<!--Start Header-->
<div id="screensaver">
	<canvas id="canvas"></canvas>
	<i class="fa fa-lock" id="screen_unlock"></i>
</div>
<div id="modalbox">
	<div class="devoops-modal">
		<div class="devoops-modal-header">
			<div class="modal-header-name">
				<span>Basic table</span>
			</div>
			<div class="box-icons">
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		<div class="devoops-modal-inner">
		</div>
		<div class="devoops-modal-bottom">
		</div>
	</div>
</div>
<header class="navbar">
	<div class="container-fluid expanded-panel">
		<div class="row">
			<div id="logo" class="col-xs-12 col-sm-2">
				<a href="">订单系统</a>
			</div>
			<div id="top-panel" class="col-xs-12 col-sm-10">
				<div class="row">
					<div class="col-xs-8 col-sm-4">
						<div id="search">
							<input type="text" placeholder="search"/>
							<i class="fa fa-search"></i>
						</div>
					</div>
					<div class="col-xs-4 col-sm-8 top-panel-right">
						<a href="#" class="about">about</a>
						<!-- <a href="index_v1.html" class="style1"></a> -->
						<ul class="nav navbar-nav pull-right panel-menu">
							<!-- <li class="hidden-xs">
								<a href="index.html" class="modal-link">
									<i class="fa fa-bell"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a class="ajax-link" href="ajax/calendar.html">
									<i class="fa fa-calendar"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a href="ajax/page_messages.html" class="ajax-link">
									<i class="fa fa-envelope"></i>
									<span class="badge">7</span>
								</a>
							</li> -->
							<li class=""><!-- dropdown -->
								<a href="#" class="dropdown-toggle account" data-toggle="dropdown">
									<div class="avatar">
										<img src="<%=request.getContextPath()%>/resources/image/touxiang1.jpg" class="img-circle" alt="avatar" />
									</div>
									<!-- <i class="fa fa-angle-down pull-right"></i> -->
									<div class="user-mini pull-right">
										<span class="welcome">Welcome,</span>
										<span>${USER_CONTEXT.getUsername() }</span>
									</div>
								</a>
								
									<li>
										<a href="logout">
											<i class="fa fa-power-off"></i>
											<span>Logout</span>
										</a>
									</li>
								<!-- </ul> -->
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!--End Header-->
<!--Start Container-->
<div id="main" class="container-fluid">
	<div class="row">
		<div id="sidebar-left" class="col-xs-2 col-sm-2">
			<ul class="nav main-menu">
				<li>
					<a href="index" class="active ajax-link">
						<i class="fa fa-dashboard"></i>
						<span class="hidden-xs">主菜单</span>
					</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-bar-chart-o"></i>
						<span class="hidden-xs">BOM配置</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link"  id="part1" >菜单1</a></li>
						<li><a class="ajax-link" href="part/parts">菜单2</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">在线订单</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="#">菜单1</a></li>
						<li><a class="ajax-link" href="#">菜单2</a></li>
						<li><a class="ajax-link" href="#">菜单3</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-table"></i>
						 <span class="hidden-xs">系统 设置</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="#">菜单1</a></li>
						<li><a class="ajax-link" href="#">菜单2</a></li>
						<li><a class="ajax-link" href="#">菜单3</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!--Start Content-->
		<div id="content" class="col-xs-12 col-sm-10">
			<div id="about">
				<div class="about-inner">
					<h4 class="page-header">Open-source admin theme for you</h4>
					<p>DevOOPS team</p>
					<p>Homepage - <a href="http://devoops.me" target="_blank">http://devoops.me</a></p>
					<p>Email - <a href="mailto:devoopsme@gmail.com">devoopsme@gmail.com</a></p>
					<p>Twitter - <a href="http://twitter.com/devoopsme" target="_blank">http://twitter.com/devoopsme</a></p>
					<p>Donate - BTC 123Ci1ZFK5V7gyLsyVU36yPNWSB5TDqKn3</p>
				</div>
			</div>
			<div class="preloader">
				<img src="<%=request.getContextPath()%>/resources/img/devoops_getdata.gif" class="devoops-getdata" alt="preloader"/>
			</div>
			<div id="ajax-content"></div>
		</div>
		<!--End Content-->
	</div>
</div>
<!--End Container-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://code.jquery.com/jquery.js"></script>-->
<script src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/resources/plugins/bootstrap/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/justified-gallery/jquery.justifiedGallery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/tinymce/tinymce.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/tinymce/jquery.tinymce.min.js"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="<%=request.getContextPath()%>/resources/js/devoops.js"></script>
</body>
</html>
