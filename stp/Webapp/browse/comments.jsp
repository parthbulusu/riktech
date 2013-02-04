    <%@page import="org.riktech.stp.forms.UserCommentsForm"%>
    <div id="commentsList${param.visibility}">

    </div>
	<div id="addComments" class="addComments">
     	<form id='<%=UserCommentsForm.FORM_NAME %>' name='<%=UserCommentsForm.FORM_NAME %>'>
     		<textarea name ="<%=UserCommentsForm.USER_COMMENT_FIELD_NAME%>" id="user_comment"></textarea>
     		<input type="hidden" name="<%=UserCommentsForm.VISIBILITY_FIELD_NAME%>" id="visibility" value="${param.visibility}">
     		
     		<input type="button" class="submitComment" value="Add">
		</form>
    </div>