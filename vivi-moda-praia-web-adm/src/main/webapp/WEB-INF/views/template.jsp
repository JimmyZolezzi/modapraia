<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title></title>

<!-- Bootstrap Core CSS -->

<link rel="stylesheet" type="text/css" href="<c:url value="/css/theme-novo.css" />" >

<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/heroic-features.css" />">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<jsp:include page="header.jsp" />
	<!-- Page Content -->
	<div class="container">
		<jsp:include page="${partial}" />
		<jsp:include page="footer.jsp" />
		<hr>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="<c:url value="/js/jquery.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>

</body>
</html>
