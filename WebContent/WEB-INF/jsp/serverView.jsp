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
	
	<c:if test="${not empty list}">
	<center>
		<table border="1">
   			<tr>
    			<td class="heading">Server ID</td>
    			<td class="heading">Application Name</td>
    			<td class="heading">Server Name</td>
    			<td class="heading">Server Status</td>
    			<td class="heading">Start Time</td>
    			<td class="heading">End Time</td>
   			</tr>
			<c:forEach var="listValue" items="${list}">
					<tr>
						<td>${listValue.serverId}</td>
						<td>${listValue.applicationName}</td>
						<td>${listValue.serverName}</td>
						<td>${listValue.serverStatus}</td>
						<td>${listValue.startDate}</td>
						<td>${listValue.endDate}</td>
					</tr>
			</c:forEach>
		</table>
 	</center>
	</c:if>
	</div>
	<div class="center"><button id="btnExport" onclick="fnExcelReport();"> EXPORT </button></div>
</body>
</html>