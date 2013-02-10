<%@page import="org.riktech.stp.forms.TechnologyForm"%>
<%@page import="org.riktech.stp.forms.AnswerChoicesForm"%>
<%@page import="com.riktech.stp.dto.UserComments"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="layoutWrapper">
	<div id="headerWrapper">
		<h1>
			Add/Edit Question Bank
		</h1>
	</div>
	<div id="bodyWrapper">
		<div class="treeNodeDiv" id="qbTree">
			<input type="text" placeholder="Search/Edit Existing Question" name="existingQuest" id="existingQuest"/>
			<input type="text" placeholder="Search/Edit Existing Technology" name="existingTech" id="existingTech"/>
		
		</div>	
	</div>
</div>
<div style="display:none;" id="techTemplate">
	<div class="treeNodeDiv">
		<form name="<%=TechnologyForm.FORM_NAME%>" id="technologyForm">
			<input type="text" placeholder="Technology Name" id="name"  name="<%=TechnologyForm.NAME_FIELD_NAME%>">
			<input type="hidden" id="id" name="<%=TechnologyForm.ID_FIELD_NAME%>">
			<input type="hidden" id="parentId" name="<%=TechnologyForm.PARENT_ID_FIELD_NAME%>">
			<input type="hidden" id="questionId" name="<%=TechnologyForm.QUESTION_ID_FIELD_NAME%>">
			<textarea  style="display:none" placeholder="Existing Question" name="existingQuest" id="existingQuest"></textarea>
			<textarea  style="display:none" placeholder="New Question"  id="newQuest" name="<%=TechnologyForm.QUESTION_FIELD_NAME%>"></textarea>
			<a id="addNewQuestBtn">New Question</a>
			<a id="addExistingQuestBtn">Existing Question</a>
			<a id="addAC" style="display:none" class="aac"> Add Answer Choice</a>
			<a id="addST" style="display:none" class="ast"> Add Sub Technology</a>
			<a id="update">Save</a>
		</form>
	</div>
</div>			
<div style="display:none;" id="acTemplate">
	<div class="treeNodeDiv">
		<form name="<%=AnswerChoicesForm.FORM_NAME%>" id="answerChoiceForm">
			<input type="hidden" id="<%=AnswerChoicesForm.ID_FIELD_NAME%>">
			<input type="text" placeholder="Answer Choice" id="ansChoice"  name="<%=AnswerChoicesForm.ANS_CHOICE_FIELD_NAME%>"/>
			<input type="hidden" id="currentQuestionId" name="<%=AnswerChoicesForm.CURRENT_QUESTION_ID_FIELD_NAME%>">
			<textarea  style="display:none" placeholder="Existing Question" name="existingQuest" id="existingQuest"></textarea>
			<textarea  style="display:none" placeholder="New Question"  id="newQuest" name="<%=AnswerChoicesForm.NEXT_QUESTION_FIELD_NAME%>"></textarea>
			<input type="hidden" id="questionId" name="<%=AnswerChoicesForm.NEXT_QUESTION_ID_FIELD_NAME%>">
			<a id="addNewQuestBtn">New Question</a>
			<a id="addExistingQuestBtn">Existing Question</a>
			<a id="addAC" style="display:none" class="aac"> Add Answer Choice</a>
			<a id="update">Save</a>
		</form>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script src="/stp/assets/js/questionBank.js"></script>
<link href="/stp/assets/css/questionBank.css" type="text/css" rel="stylesheet">
