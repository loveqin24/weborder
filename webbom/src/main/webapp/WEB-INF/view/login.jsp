<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<title>登入</title>
		<meta name="description" content="description">
		<meta name="author" content="Evgeniya">
		<meta name="keyword" content="keywords">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath()%>/resources/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="<%=request.getContextPath()%>/resources/css/style_v2.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>
<div class="container-fluid">
	<div id="page-login" class="row">
		<div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
			<div class="text-right">
				<a href="pageregister.html" class="txt-default">Need an account?</a>
			</div>
			<div class="box">
				<div class="box-content">
				 <form action="doLogin" method="post">
					<div class="text-center">
						<h3 class="page-header">登入页面</h3>
					</div>
					<div class="form-group">
						<label class="control-label">用户名:</label>
						<input type="text" class="form-control" name="username" />
					</div>
					<div class="form-group">
						<label class="control-label">密码:</label>
						<input type="password" class="form-control" name="password" />
					</div>
					<div class="text-center">
						<!-- <a href="#" class="btn btn-primary">登入</a> -->
						<input type="submit" value="登入" class="btn btn-primary" />
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
