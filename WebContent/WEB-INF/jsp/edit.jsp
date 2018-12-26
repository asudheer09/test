<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datetimepicker.css"></link>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>


<script type="text/javascript">

//$.fn.datepicker.defaults.format = "YYYYM-M-DD HH:MM";

$(document).ready(function(){
	
	$('#startDate').datetimepicker({
		format:'Y-m-d H:i'
	});
	$('#endDate').datetimepicker({
		format:'Y-m-d H:i'
	});
	$('#projectedStartDate').datetimepicker({
		format:'Y-m-d H:i'
	});
	$('#projectedEndDate').datetimepicker({
		format:'Y-m-d H:i'
	});
});

</script>

<div align="center">
	
	<div class="panel-title">
		
		<h1>Application Details</h1>
		
	</div>

        <form:form action="saveApplication" method="post" modelAttribute="application">
        <table>
            <form:hidden path="applicationId"/>
            
            <tr>
                <td>Application Name:</td>
                <td><form:input path="applicationName" class="form-control"/></td>
            </tr>
            <tr>
                <td>Projected StartDate:</td>
             <c:choose>
    			<c:when test="${!empty application.projectedStartDate}">
       			 	<td><form:input path="projectedStartDate" id="projectedStartDate" class="form-control" disabled="true" value="${application.projectedStartDate}"/></td>
    			</c:when>
   				 <c:otherwise>
     			 	<td><form:input path="projectedStartDate" id="projectedStartDate" class="form-control" value="${application.projectedStartDate}"/></td>
   			 	</c:otherwise>
			 </c:choose> 
                
            </tr>
            <tr>
                <td>Projected EndDate:</td>
                <c:choose>
    			<c:when test="${!empty application.projectedEndDate}">
                <td><form:input path="projectedEndDate" id="projectedEndDate" class="form-control" disabled="true"/></td>
                </c:when>
                <c:otherwise>
     			 	<td><form:input path="projectedEndDate" id="projectedEndDate" class="form-control" /></td>
   			 	</c:otherwise>
			 </c:choose> 
            </tr>
            <tr>
                <td>Current Action:</td>
                <td><form:input path="currentAction" class="form-control"/></td>
            </tr>
             <tr>
                <td>Start Date:</td>
                <td><form:input path="startDate" id="startDate" class="form-control"/></td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><form:input path="endDate" id="endDate" class="form-control"/></td>
            </tr>
              <tr></tr>
            <tr>
                <td>Danone Validation:</td>
                <td><form:select path="danoneValidation" class="form-control"> <form:options items="${DanoneValidation}" /></form:select></td>
            </tr>
             <tr></tr>
             <tr>
                <td>Status:</td>
			<td style="width: 196px; height: 30px;">
			<form:select path="status" class="form-control">	<form:options items="${statusList}" /></form:select></td>
            </tr>
            <tr></tr>
            <tr>
                <td>Comments:</td>
                <td><form:input path="comments"  class="form-control"/></td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" class="btn btn-primary"></td>
            </tr>
        </table>
        </form:form>
    </div>
		
		