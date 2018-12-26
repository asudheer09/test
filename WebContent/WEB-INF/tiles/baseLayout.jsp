<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

            <title><tiles:insertAttribute name="title" ignore="true" /></title>
            <style type="text/css">
            	#container{
            		width:100%;
            		margin:0 auto;
            		padding:0;
            		height:auto;
            	}
            	body {
  					background: #eee !important;
				}
            </style>
    </head>
	    <body>        
	     <div id="container">
		    	<tiles:insertAttribute name="header" />
		        <tiles:insertAttribute name="menu" />
		        <tiles:insertAttribute name="body" />
		        <tiles:insertAttribute name="footer" />
	       </div>
	                
	    </body>
</html>
