<%@page import="com.riktech.stp.dto.UserComments"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../assets/handlebars/technologies.hb"/>
<jsp:include page="../assets/handlebars/comments.hb"/>
	<div id="layoutWrapper">
		<div id="headerWrapper">
			<h1>
				<c:out value="${tsType}"/> based troubleshooting
				<a  class="TblContentFont5" id="expandPanelDev">Show/Hide Help</a>
			</h1>
		</div>
		<div id="bodyWrapper">	
			<jsp:include page="./bkbHelp.jsp"/>

			<div id="kb-container" class="scroll-pane-arrows">
				<ul id="kb-tiles">
			
				</ul>
			</div>
			<div id="kb-container-helper">
				<ul>
					<li><a href="#dig-tab">Diagram</a></li>
					<li><a href="#blog-tab">Blog</a></li>
					<li><a href="#notes-tab">Notes</a></li>
				</ul>
				<div id="dig-tab">
					<img style="max-width:500px; max-height:600px;" src="/stp/assets/images/spacer.gif" id="mainImage"></img>
				</div>
				<div id="blog-tab">
					<jsp:include page="./comments.jsp">
						<jsp:param name="visibility" value="<%=UserComments.VISIBILITY_PRIVATE %>"/>
					</jsp:include>
				</div>
				<div id="notes-tab">
					<jsp:include page="./comments.jsp">		
						<jsp:param name="visibility" value="<%=UserComments.VISIBILITY_PUBLIC %>"/>
					</jsp:include>					
				</div>

			</div>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script src="/stp/assets/js/browseKnowledgeBase.js"></script>
<link href="/stp/assets/css/browseKnowledgeBase.css" type="text/css" rel="stylesheet">

