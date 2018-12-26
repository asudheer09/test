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
	
});

</script>

<style>
#applicationId{
	width: 196px;
    height: 30px;
    }
 #serverStatus{
 	width: 196px;
    height: 30px;
 }
</style>


<div align="center">
	
	<div class="panel-title">
		
		<h1>Server Details</h1>
		
	</div>

        <form:form action="saveServer" method="post" modelAttribute="server">
        <table>
            <form:hidden path="serverId"/>
             <tr>
                <td>Server Name:</td>
                <td><form:input path="serverName" id="serverName" class="form-control"/></td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><form:input path="startDate" id="startDate" class="form-control"/></td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><form:input path="endDate" id="endDate" class="form-control" /></td>
            </tr>
            <tr>
            
            <td>Application Name:</td>
            <td style="width: 196px; height: 30px;">
            	<form:select path="applicationId"><form:options items="${appList}" /></form:select>
            </td>
            <tr>
            <td>Server Status</td>
            <td style="width: 196px; height: 30px;">
            	<form:select path="serverStatus"><form:options items="${serverStatus}" /></form:select>
            </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" class="btn btn-primary"></td>
            </tr>
        </table>
        </form:form>
    </div>
		