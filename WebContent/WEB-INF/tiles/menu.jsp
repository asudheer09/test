<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
.navbar-inverse {
    background-color: #4a6bae;
    border-color: #fff;
}
.navbar-inverse .navbar-nav>li>a{
	color:#fff;
}

.navbar-inverse .navbar-nav>li>a:hover{
	background-color:#fff ;
	color:#4a6bae;
}
.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.active>a:hover{
	background-color:#fff;
	color:#4a6bae;
	border-radius: 0px;
}

</style>



<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      
      <c:if test="${pageContext.request.userPrincipal.name != null}">
      	<li><a href="applicationView">Application Details</a></li>
      </c:if>
      <c:if test="${pageContext.request.userPrincipal.name != null}">
       	<li><a href="<c:url value="serverView"/>">Server Details</a></li>
      </c:if>
      
      <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
      
      	<li><a href="<c:url value="editApplicationDetails"/>">Edit Application Details</a></li>
      	
      </sec:authorize>
      
      <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
      
      	<li><a href="<c:url value="editServerDetails"/>">Edit Server Details</a></li>
      	
      </sec:authorize>
      
      <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
      	<li><a href="<c:url value="newApplication"/>">Create Application</a></li>
      </sec:authorize>
      
      <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()"> 
       	<li><a href="<c:url value="newServer"/>">Create Server</a></li>
      </sec:authorize>
      
    </ul>
    
    <c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

    <ul class="nav navbar-nav navbar-right">
    	<c:if test="${pageContext.request.userPrincipal.name == null}">
      		<li><a href="/IBMApplicationsManagement/"> Login</a></li>
     	</c:if> 
     	<c:if test="${pageContext.request.userPrincipal.name != null}">
     		<li><a href="javascript:formSubmit()"> Logout</a></li>
     	</c:if>
    </ul>
  </div>
</nav>
