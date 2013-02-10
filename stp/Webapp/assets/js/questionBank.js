function showNewQuestionFields(){
	$(this).siblings('#existingQuest').val('');
	$(this).siblings('#questionId').val('');
	$(this).siblings('#newQuest').show('slow');
	$(this).siblings('#existingQuest').hide();;
	$(this).siblings('#addExistingQuestBtn').show('slow');
	$(this).hide();;
}
function showExistingQuestionFields(){
	$(this).siblings('#newQuest').val('');
	$(this).siblings('#newQuest').hide();;
	$(this).siblings('#existingQuest').show('slow');
	$(this).hide();;
	$(this).siblings('#addNewQuestBtn').show('slow');
}
function hideQuestionFields(){
	if($.trim($(this).val()).length<1)
	{
		$(this).siblings('#existingQuest').val('');
		$(this).siblings('#questionId').val('');
		$(this).siblings('#newQuest').val('');
		$(this).hide();;
		$(this).siblings('#addNewQuestBtn').show('slow');
		$(this).siblings('#addExistingQuestBtn').show('slow');
	}
}
var showNextActionsForT = function(ref) {
	ref.siblings('input').attr('readonly','readonly');
	ref.siblings('textarea').attr('readonly','readonly');
	ref.siblings('#addExistingQuestBtn').hide();
	ref.siblings('#addNewQuestBtn').hide();
	ref.hide();
	return function(data) {
    	var jsonObj=data;
    	var techObj=jsonObj['json-result'][0];
    	ref.siblings("#id").val(techObj['id']);
    	if(techObj.questionId>1)
    	{
    		ref.siblings('#questionId').val(techObj.questionId);
    		ref.siblings('#addAC').show('slow');
    		ref.siblings('#addST').hide();
    	}else{
    		ref.siblings('#addAC').hide;
    		ref.siblings('#addST').show('slow');    	
    	}
    	
    	
    };
};
var showNextActionsForAc = function(ref) {
	ref.siblings('input').attr('readonly','readonly');
	ref.siblings('textarea').attr('readonly','readonly');
	ref.siblings('#addExistingQuestBtn').hide();
	ref.siblings('#addNewQuestBtn').hide();
	ref.hide();
	return function(data) {
    	var jsonObj=data;
    	var techObj=jsonObj['json-result'][0];
    	ref.siblings("#id").val(techObj['nextQuestId']);
    	if(techObj.nextQuestId>1)
    	{
    		ref.siblings('#questionId').val(techObj.nextQuestId);
    		ref.siblings('#addAC').show('slow');
    	}
    	
    	
    };
};
function searchAutoCompleteQB()
{
	 $(this).autocomplete({
		 source: function( request, response ) {
			 $.ajax({
				 url: "/stp/searchQuestionBank",
				 dataType: "json",
				 data:{
					q_term:request.term
				 },
				 success: function( data ) {

					 response( $.map( data['json-result'], function( item ) {
						 return{
							 label:item.question,
							 value:item.question,
							 id:item.id
						 }
					 }));
				 }
			 });
				
		},	
 		minLength: 2,
		cacheLength:20,
		delay:400,
		dataType: 'json',
		select: function( event, ui ) {
			$(this).siblings('#questionId').val(ui.item.id);

		}
     });
 
}
function saveSubTechnology(){

	var name=$(this).siblings('#name').val();
	
	name=$.trim(name);
	if(name){
		var data=$(this).parent().serializeObject();
		ajaxPost("/stp/saveTechnology",data,showNextActionsForT($(this)),error);
	}else{
		alert("Technology is required");
		return false;
	}
}
function saveAnswerChoice(){
	var newQuest=$(this).siblings('#newQuest').val();
	var questionId=$(this).siblings('#questionId').val();
	var ansChoice=$(this).siblings('#ansChoice').val();
	newQuest=$.trim(newQuest);
	ansChoice=$.trim(ansChoice);
	if(ansChoice && (questionId || newQuest))
	{
		var data=$(this).parent().serializeObject();
		ajaxPost("/stp/saveAnswerChoice",data,showNextActionsForAc($(this)),error);
	}else{
		if(!ansChoice){
			alert("Answer Choice is required");
		}else{
			alert("Question is required");
		}
		return false;
	}
}
$(document).ready(function() {
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #addAC,.treeNodeDiv #answerChoiceForm #addAC','click',addAnswerChoiceDiv);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #addST','click',addSubTechnologyDiv);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #addNewQuestBtn,.treeNodeDiv #answerChoiceForm #addNewQuestBtn','click',showNewQuestionFields);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #addExistingQuestBtn,.treeNodeDiv #answerChoiceForm #addExistingQuestBtn','click',showExistingQuestionFields);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #existingQuest,.treeNodeDiv #answerChoiceForm #existingQuest','blur',hideQuestionFields);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #newQuest,.treeNodeDiv #answerChoiceForm #newQuest','blur',hideQuestionFields);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #update','click',saveSubTechnology);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #answerChoiceForm #update','click',saveAnswerChoice);
	 $('#bodyWrapper .treeNodeDiv').delegate('.treeNodeDiv #technologyForm #existingQuest,.treeNodeDiv #answerChoiceForm #existingQuest','focusin',searchAutoCompleteQB);

	 $("#existingQuest").autocomplete({
			 source: function( request, response ) {
				 $.ajax({
					 url: "/stp/searchQuestionBank",
					 dataType: "json",
					 data:{
						q_term:request.term
					 },
					 success: function( data ) {

						 response( $.map( data['json-result'], function( item ) {
							 return{
								 label:item.question,
								 value:item.question,
								 id:item.id
							 }
						 }));
					 }
				 });
					
			},	
	 		minLength: 2,
			cacheLength:20,
			delay:400,
			dataType: 'json',
			select: function( event, ui ) {

				addAnswerChoiceFirstDiv(ui.item,$(this));
			}
		 });
	 $("#existingTech").autocomplete({
		 source: function( request, response ) {
			 $.ajax({
				 url: "/stp/searchTechnology",
				 dataType: "json",
				 data:{
					t_term:request.term
				 },
				 success: function( data ) {

					 response( $.map( data['json-result'], function( item ) {
						 return{
							 label:item.name,
							 value:item.name,
							 name:item.name,
							 id:item.id,
							 questionId:item.questionId,
							 parentId:item.parentId,
							 question:item.question
							 
						 }
					 }));
				 }
			 });
				
		},	
 		minLength: 2,
		cacheLength:20,
		delay:400,
		dataType: 'json',
		select: function( event, ui ) {
			addTechnologyFirstDiv(ui.item,$(this));
			

		}
	 });		 	

});
function addAnswerChoiceDiv()
{
	$(this).parent().parent().append($('#acTemplate').html());
	var answerChoiceForm=$(this).parent().siblings('.treeNodeDiv').children("#answerChoiceForm");
	answerChoiceForm.children('#currentQuestionId').val($(this).siblings('#questionId').val());
}
function addAnswerChoiceFirstDiv(item,elem)
{
	elem.siblings().hide();
	elem.parent().append($('#acTemplate').html());
	var answerChoiceForm=elem.siblings('.treeNodeDiv').children("#answerChoiceForm");
	answerChoiceForm.children('#currentQuestionId').val(item.id);
	elem.hide();
	
}
function addSubTechnologyDiv()
{

	$(this).parent().parent().append($('#techTemplate').html());
	var technologyForm=$(this).parent().siblings('.treeNodeDiv').children("#technologyForm");
	technologyForm.children('#parentId').val($(this).siblings('#id').val());
}
function addTechnologyFirstDiv(item,elem){
	elem.siblings().hide();
	elem.parent().append($('#techTemplate').html());
	var technologyForm=elem.siblings('.treeNodeDiv').children("#technologyForm");
	technologyForm.children('#id').val(item.id);
	technologyForm.children('#name').val(item.name);
	technologyForm.children('#name').attr('readonly','readonly');
	technologyForm.children('#addNewQuestBtn').hide();
	technologyForm.children('#addExistingQuestBtn').hide();
	technologyForm.children('#questionId').val(item.questionId);
	technologyForm.children('#parentId').val(item.parentId);
	technologyForm.children('#update').hide();
	if(item.questionId>1){$('#addAC').show();}else{$('#addST').show();}
	elem.hide();
	//$(this).siblings('.showOnSelect').show('slow');	
	
}
