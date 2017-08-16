<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<sec:csrfMetaTags />
    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/css/sb-admin.css" />" rel="stylesheet">
	<link href="<c:url value="/css/site/moda-praia-adm.css" />" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="<c:url value="/css/plugins/morris.css" />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	 <!-- jQuery -->
    <script src="<c:url value="/js/jquery.js" />"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
    <!-- Morris Charts JavaScript -->
    <script src="<c:url value="/js/plugins/morris/raphael.min.js" />"></script>
	<script src="<c:url value="/js/angular.min.js"  />"></script>
</head>

<body>

    <div id="wrapper">

        <jsp:include page="header.jsp" />

        <div id="page-wrapper">

            <div class="container-fluid">

               <jsp:include page="${partial}" />

            </div>

        </div>

    </div>

   
</body>

</html>
