
$(document).ready(function() {
	$("#kb-container-helper").tabs();
	$(".submitComment").bind("click",saveComments);
	//$("#kb-container-helper").delegate("a[href='#notes-tab']","click",scrollOnCommnetTabClick);
	//$("#kb-container-helper").delegate("a[href='#blog-tab']","click",scrollOnCommnetTabClick);
	ajaxPost("/stp/action?action=BrowseKnowledgeBase&ts_id="+isScenarioBased,'',showTile,error);
	$("#kb-container #kb-tiles").delegate(".kb-tile ul li div a","click",getNextQBRecord);	
	$("#kb-container #kb-tiles").delegate(".kb-tile","click",showHelpTabs);
	  //toggle the component with class msg_body
	  jQuery("a#expandPanelDev").click(function()
	  {
		$("#bkbHelp").slideToggle("slow");
	  });	
	
});
function scrollOnCommnetTabClick()
{
	                    
	var tabdiv=$(this).attr('href');
	$(tabdiv).find("div[id*='commentsList']").jScrollPane(
			{
				showArrows: true,
				horizontalGutter: 10
			}
		);
}
function showHelpTabs()
{
	$("#kb-tiles .kb-tile").each(function(){$(this).removeClass("selected");});
	$(this).addClass("selected");
	var qid=$(this).find('.currentQuestionid').html();
	
	if(!qid)return;
	qid=$.trim(qid);
	showHelperImage(qid);
	showComments('blog-tab',qid);
	showComments('notes-tab',qid);
}

function showComments(tabName,qid){
	var data={};
	data['visibilityIdentifierName']=$("#"+tabName).find("#visibility").attr('name');
	data['questionIdIdentifierName']=$("#"+tabName).find("#questionId").attr('name');
	data[$("#"+tabName).find("#visibility").attr('name')]=$("#"+tabName).find("#visibility").val();
	data[$("#"+tabName).find("#user_comment").attr('name')]=$("#"+tabName).find("#user_comment").val();
	data[$("#"+tabName).find("#questionId").attr('name')]=qid;
	ajaxPost("/stp/showComments",data,displayComments(data),error);
}
function saveComments()
{
	if(!$(this).siblings('#user_comment').val())return;
	var data={};
	data['visibilityIdentifierName']=$(this).siblings('#visibility').attr('name');
	data['questionIdIdentifierName']=$(this).siblings("#questionId").attr('name');	
	data[$(this).siblings('#visibility').attr('name')]=$(this).siblings('#visibility').val();
	data[$(this).siblings('#user_comment').attr('name')]=$(this).siblings('#user_comment').val();
	data[$(this).siblings("#questionId").attr('name')]=$(this).siblings('#questionId').val();
	ajaxPost("/stp/addComment",data,displayComments(data),error);
	

}
var displayComments = function(input) {
   return function(output) {
		var source = $("#hb-commentslist").html(); 
		var template = Handlebars.compile(source); 
		var jsonObj=template(output);
		var clistdiv=$("#commentsList"+input[input['visibilityIdentifierName']]);
		clistdiv.html(jsonObj);
		clistdiv.siblings('#addComments').children('form').children('#questionId').val(input[input['questionIdIdentifierName']]);
		/*$("#commentsList"+input[input['visibilityIdentifierName']]).css('height:20%;','width: 100%;','overflow: auto;');
		$("#commentsList"+input[input['visibilityIdentifierName']]).jScrollPane(
				{
					showArrows: true,
					horizontalGutter: 10
				}
			);	*/	
   };
};



function applyScroll()
{

	$('.scroll-pane').jScrollPane();
	$('.scroll-pane-arrows').jScrollPane(
		{
			showArrows: true,
			horizontalGutter: 10
		}
	);
}
function showHelperImage(qid)
{

	
     $('#mainImage').hide();
     $('#kb-container-helper').css('background-image', "url('../assets/images/ajax-loader.gif')");
     $('#mainImage').attr('src', '/stp/action?action=ShowQuestionImage&q_id='+qid);
     $('#kb-container-helper').css('background-image', 'none');
     $('#mainImage').fadeIn();
}
function getNextQBRecord()
{
	$("#bkbHelp").hide("slow");

	var hilit_elem=$(this).siblings('#hilit_element').html();
	$(this).closest(hilit_elem).siblings('.selected').removeClass("selected");
	$(this).closest(hilit_elem).addClass("selected");
	var currentId = $(this).parents('.kb-tile').attr('id');
	$(this).parents('.kb-tile').siblings('.kb-tile').each(function(){

		var removeId=$(this).attr('id');
		if(removeId>currentId)
		{
			$(this).remove();	
		}
	});
	var data={
		"action":"BrowseKnowledgeBase",
		"t_id":$(this).siblings('#t_id').html(),
		"q_id":$(this).siblings('#q_id').html(),
	}
	ajaxPost("/stp/action",data,showTileDetails(data),error);
}
function showTile(data){
	var source = $("#hb-technologies").html(); 
	var template = Handlebars.compile(source); 	

	$("#kb-tiles").prepend(template(data));
	$(".kb-tile").show('slow');
}
var showTileDetails = function(input) {
	   return function(data) {
			$(".kb-tile").each(function(){$(this).removeClass("selected");});
			showTile(data);
			applyScroll();
			if(!input.q_id)return;
			showHelperImage(input.q_id);
			showComments('blog-tab',input.q_id);
			showComments('notes-tab',input.q_id);
			event.stopPropagation(); 
	   };
};

