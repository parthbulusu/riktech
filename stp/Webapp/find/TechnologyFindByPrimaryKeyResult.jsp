<html>
<head>
<LINK REL="stylesheet" href="styles.css" type="text/css" >
</head>

<table border="1" width="95%">
<tr>
<td width="15%" valign="top"><%@include file="Nav.jsp" %></td>
<td width="85%" valign="top"><h1>Technology findByPrimaryKey Results</h1>
<%@ page import="com.riktech.stp.dto.*" %>
<%
Technology dto = (Technology) request.getAttribute( "result" );
%>
<table border="1">
<tr>
	<td>id</td>
	<td>name</td>
	<td>parentId</td>
	<td>questionId</td>
</tr>
<%
if (dto != null) {
	Technology value = dto;
%>
<tr>
	<td><%= value.getId() %></td>
	<td><%= value.getName() %></td>
	<td><%= value.getParentId() %></td>
	<td><%= value.getQuestionId() %></td>
</tr>
<%
}
%>
</table>
</td>
</tr>
</table>

</html>
