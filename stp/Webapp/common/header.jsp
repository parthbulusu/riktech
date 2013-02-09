<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String s_ts_id=request.getParameter("ts_id");
	String s_ts_type="Technology";	
	request.setAttribute("isScenarioBased",false);
	
	int ts_id=TechnologyDaoFactory.TECHNOLOGY_BASED_TS;
	try{
		ts_id=Integer.parseInt(s_ts_id);
		if(ts_id==TechnologyDaoFactory.SCENARIO_BASED_TS)
		{
			s_ts_type="Business Solutions";	
			request.setAttribute("isScenarioBased",true);
		}		
	}catch(Exception e)
	{
		//e.printStackTrace();
	}
	request.setAttribute("tsType",s_ts_type);

%>
<%@page import="com.riktech.stp.factory.TechnologyDaoFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<!DOCTYPE HTML>
<html>
<head>
	<script type="text/javascript">
		var isScenarioBased=<%=ts_id%>;
	</script>
 	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
	<script type="text/javascript" src="/stp/assets/js/jquery.jscrollpane.min.js"></script>	
	<script type="text/javascript" src="/stp/assets/js/handlebars-1.0.rc.1.min.js"></script>
	<script type="text/javascript" src="/stp/assets/js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="/stp/assets/js/stputils.js"></script>
	<link href="/stp/assets/css/jquery.jscrollpane.css" type="text/css" rel="stylesheet">
	<link href="/stp/assets/css/nl_brand.css" type="text/css" rel="stylesheet">
	<link href="/stp/assets/css/nl_default.css" type="text/css" rel="stylesheet">
</head>
<body><%-- Ends in footer --%>
	<div id="header">
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td nowrap="" valign="top" width="30%">
				<img src="/stp/assets/images/spacer.gif" width="1" border="0">
				<!-- img src="/stp/images/NL-logo1.jpg" height="61" width="145" border="0" / -->
				<img src="/stp/assets/images/NL-brand-logo.gif" height="50" width="190" border="0">
			</td>
			<td nowrap="" align="right" valign="bottom" width="70%">
				<img src="/stp/assets/images/your_logo.png" height="44" width="150" border="0">
				<img src="/stp/assets/images/spacer.gif" width="16" border="0">
			</td>
		</tr>
	</tbody></table>
	</div>

	<div id="topbar">
		<c:if test="${not empty userProfile.userName}">
			<ul class="menu-left">
				<li class="menu-item${isScenarioBased?'':' Selected'}"><a href="/stp/home"> Technology</a></li>
				<li class="menu-item${isScenarioBased?' Selected':''}"><a href="/stp/home?ts_id=<%=TechnologyDaoFactory.SCENARIO_BASED_TS%>">Business Solutions</a></li>
			</ul>
			<ul class="menu-center ">
			 <li class="menu-item">Welcome ${userProfile.firstName} ${userProfile.lastName}</li>
			</ul>
			<ul class="menu-right ">
			<c:if test="${userProfile.adminRole}">
				<li class="menu-item"><a href="/stp/qbAdminHome">Admin</a></li>
			</c:if>

			 <li class="menu-item"><a href="/stp/logout">Logout</a></li>
			</ul>			
		</c:if>
	</div>