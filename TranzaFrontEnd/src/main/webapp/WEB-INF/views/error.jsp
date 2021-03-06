<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/ecommerce-style.css" rel="stylesheet">

<title>Online Shopping - ${title}</title>

</head>

<body>
	<div class="wrapper">

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>
		<div class="content">

			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="jumbotron">

							<h1>${errorTitle}</h1>
							<hr style="height: 5px; background-color: #BA2F16;" />

							<blockquote style="word-wrap: break-word">${errorDescription}</blockquote>

						</div>

					</div>
				</div>

			</div>
		</div>

		<%@include file="./header/footer.jsp"%>

	</div>


</body>


</html>