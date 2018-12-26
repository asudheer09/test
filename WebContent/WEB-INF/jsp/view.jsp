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
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/TableExport/3.3.5/js/tableexport.js" type="text/javascript"></script> -->
	<script src="${pageContext.request.contextPath}/js/tableexport.js" ></script>
<script>
function fnExcelReport()
{
	//alert("fnExcelReport ")
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('headerTable'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}

</script>
</head>
<body>
	 <div class="wrapper">
	
	<%-- <c:if test="${pageContext.request.userPrincipal.name != null}">
		<center>
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} 
		</h2>
		<h3>Delivered Count:${count}</h3>
		</center>
	</c:if> --%>
	
	 

	<c:if test="${not empty applicationList}">
	<center>
		<table border="1" id="headerTable">
   			<tr>
    			<td class="heading">ID</td>
    			<td class="heading">Application Name</td>
    			<td class="heading">Projected Start Time</td>
    			<td class="heading">Projected End Time</td>
    			<td class="heading">Validation</td>
    			<td class="heading">Start Date</td>
    			<td class="heading">End Date</td>
    			<td class="heading">Action</td>
    			<td class="heading">status</td>
    			<td class="heading">Comments</td>
   			</tr>
			<c:forEach var="listValue" items="${applicationList}">
					<tr>
						<td>${listValue.applicationId}</td>
						<td><a href="showServerDetails?id=${listValue.applicationId}">${listValue.applicationName}</a></td>
						<td>${listValue.projectedStartDate}</td>
						<td>${listValue.projectedEndDate}</td>
						<td>${listValue.danoneValidation}</td>
						<td>${listValue.startDate}</td>
						<td>${listValue.endDate}</td>
						<td>${listValue.currentAction}</td>
						<td>${listValue.status}</td>
						<td>${listValue.comments}</td>
					</tr>
			</c:forEach>
		</table>
 	</center>
	</c:if>
	</div>
	<div class="center"><button id="btnExport" onclick="fnExcelReport();"> EXPORT </button></div>
</body>
</html>