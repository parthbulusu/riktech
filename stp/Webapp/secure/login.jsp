<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<jsp:include page="../common/header.jsp"></jsp:include>
<%
String loginError=request.getParameter("loginError");
if(StringUtils.isEmpty(loginError))loginError="";
%>
<div id="bodyWrapper">	
	<div class="loginHelp">
		<jsp:include page="./loginHelp.jsp"/>
	</div>
	<div class="signin-box">

			<h2>Sign In</h2> 
			<span class="error">
				<%=loginError%>
			</span>
			<form action="/stp/j_security_check" method="POST">  
	           <ul> 
	               <li>
	                   <span><label class="username-label" for="username">Username:</label></span> 
	                   <span><input id="username" type="text" name="j_username" value=""/></span> 
	               </li> 
	               <li> 
	                   <span><label class="password-label" for="password">Password:</label></span> 
	                   <span><input id="password" type="password" name="j_password"/></span>
	                   <span><input type="submit" value="Login" /></span>
	               </li> 
	           </ul> 
		      </form>

		</div>   
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

<link href="/stp/assets/css/login.css" type="text/css" rel="stylesheet">
