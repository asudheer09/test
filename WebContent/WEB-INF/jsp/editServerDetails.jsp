<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<style>

td {
 font-size: 14px;
 color: black;
 width: 150px;
 height: 22px;
 text-align: center;
}
.heading {
 font-size: 14px;
 color: white;
 font: bold;
 background-color: #428bca;
 border: thick;
}
.wrapper{
	margin-top: 100px;
	margin-bottom: 200px;
}

</style>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datetimepicker.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datetimepicker.min.css">
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

</head>
<body>
	 <div class="wrapper">
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<center>
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} 
		</h2>
		</center>
	</c:if>
	<c:if test="${not empty list}">
	<center>
		<table border="1">
   			<tr>
    			<td class="heading">Server ID</td>
    			<td class="heading">Server Name</td>
    			<td class="heading">Application Name</td>
    			<td class="heading">Server Status</td>
    			<td class="heading">Start Time</td>
    			<td class="heading">End Time</td>
    			<td class="heading"> Edit</td>
   			</tr>
			<c:forEach var="listValue" items="${list}">
					<tr>
						<td>${listValue.serverId}</td>
						<td>${listValue.serverName}</td>
						<td>${listValue.applicationName}</td>
						<td>${listValue.serverStatus}</td>
						<td>${listValue.startDate}</td>
						<td>${listValue.endDate}</td>
						<td><a href="editServer?id=${listValue.serverId}">Edit</a></td>
					</tr>
			</c:forEach>
		</table>
 	</center>
	</c:if>
	</div>
</body>
</html>