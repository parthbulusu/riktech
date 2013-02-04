<html>
<head>
<LINK REL="stylesheet" href="styles.css" type="text/css" >
</head>

<table border="1" width="95%">
<tr>
<td width="15%" valign="top"><%@include file="Nav.jsp" %></td>
<td width="85%" valign="top"><h1>QuestionBank findAll Results</h1>
<%@ page import="com.riktech.stp.dto.*" %>
<%
QuestionBank dto[] = (QuestionBank[]) request.getAttribute( "result" );
%>
<table border="1">
<tr>
	<td>id</td>
	<td>question</td>
</tr>
<%
for (int i=0; i<dto.length; i++)
{
	QuestionBank value = dto[i];
%>
<tr>
	<td><%= value.getId() %></td>
	<td><%= value.getQuestion() %></td>
</tr>
<%
}
%>
</table>
</td>
</tr>
</table>

</html>
