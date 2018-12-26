<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<style>

td {
 font-size: 14px;
 color: black;
 width: 120px;
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
	<script src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>

</head>
<body>
	 <div class="wrapper">
	
	<%-- <c:if test="${pageContext.request.userPrincipal.name != null}">
		<center>
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} 
		</h2>
		</center>
	</c:if> --%>
	
	 
	<c:if test="${not empty applicationList}">
	<center>
		<table border="1">
   			<tr>
    			<td class="heading">ID</td>
    			<td class="heading">Application Name</td>
    			<td class="heading">Projected Start Time</td>
    			<td class="heading">Projected End Time</td>
    			<td class="heading">Danone Validation</td>
    			<td class="heading">Start Date</td>
    			<td class="heading">End Date</td>
    			<td class="heading">Current Step / Aciton / Task</td>
    			<td class="heading">Comments</td>
    			<td class="heading">Status</td>
    			<td class="heading">Edit</td>
   			</tr>
			<c:forEach var="listValue" items="${applicationList}">
					<tr>
						<td>${listValue.applicationId}</td>
						<td>${listValue.applicationName}</td>
						<td>${listValue.projectedStartDate}</td>
						<td>${listValue.projectedEndDate}</td>
						<td>${listValue.danoneValidation}</td>
						<td>${listValue.startDate}</td>
						<td>${listValue.endDate}</td>
						<td>${listValue.currentAction}</td>
						<td>${listValue.comments}</td>
						<td>${listValue.status}</td>
						<td>
						<a href="editApplication?id=${listValue.applicationId}">Edit</a>
					</td>
						
					</tr>
			</c:forEach>
		</table>
 	</center>
	</c:if>
	</div>
</body>
</html>