<html>
<head>
<LINK REL="stylesheet" href="styles.css" type="text/css" >
</head>

<table border="1" width="95%">
<tr>
<td width="15%" valign="top"><%@include file="Nav.jsp" %></td>
<td width="85%" valign="top"><h1>AnswerChoices findByPrimaryKey Results</h1>
<%@ page import="com.riktech.stp.dto.*" %>
<%
AnswerChoices dto = (AnswerChoices) request.getAttribute( "result" );
%>
<table border="1">
<tr>
	<td>id</td>
	<td>currentQuestId</td>
	<td>ansChoice</td>
	<td>nextQuestId</td>
</tr>
<%
if (dto != null) {
	AnswerChoices value = dto;
%>
<tr>
	<td><%= value.getId() %></td>
	<td><%= value.getCurrentQuestId() %></td>
	<td><%= value.getAnsChoice() %></td>
	<td><%= value.getNextQuestId() %></td>
</tr>
<%
}
%>
</table>
</td>
</tr>
</table>

</html>
