
$(document).ready(function() {
	$("#kb-container-helper").tabs();
	$(".submitComment").bind("click",saveComments);
	//$("#kb-container-helper").delegate("a[href='#notes-tab']","click",scrollOnCommnetTabClick);
	//$("#kb-container-helper").delegate("a[href='#blog-tab']","click",scrollOnCommnetTabClick);
	ajaxPost("/stp/action?action=BrowseKnowledgeBase&ts_id="+isScenarioBased,'',addTechnologyTile,error);
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
	showHelperImage($(this));
	showPublicComments($(this));
	showPrivateComments($(this));
}
function showPublicComments(obj){
	var q_id=obj.find('#q_id').html();
	//alert(q_id);
	showComments('blog-tab');
}
function showPrivateComments(obj){
	var q_id=obj.find('#q_id').html();
	//alert(q_id);
	showComments('notes-tab');
}
function showComments(tabName){
	var data={};
	data['identifierName']=$("#"+tabName).find("#visibility").attr('name');
	data[$("#"+tabName).find("#visibility").attr('name')]=$("#"+tabName).find("#visibility").val();
	data[$("#"+tabName).find("#user_comment").attr('name')]=$("#"+tabName).find("#user_comment").val();
	ajaxPost("/stp/showComments",data,displayComments(data),error);
}
function saveComments()
{
	if(!$(this).siblings('#user_comment').val())return;
	var data={};
	data['identifierName']=$(this).siblings('#visibility').attr('name');
	data[$(this).siblings('#visibility').attr('name')]=$(this).siblings('#visibility').val();
	data[$(this).siblings('#user_comment').attr('name')]=$(this).siblings('#user_comment').val();
	ajaxPost("/stp/addComment",data,displayComments(data),error);
	

}
var displayComments = function(input) {
   return function(output) {
		var source = $("#hb-commentslist").html(); 
		var template = Handlebars.compile(source); 
		var jsonObj=template(output);
		$("#commentsList"+input[input['identifierName']]).html(jsonObj);
		/*$("#commentsList"+input[input['identifierName']]).css('height:20%;','width: 100%;','overflow: auto;');
		$("#commentsList"+input[input['identifierName']]).jScrollPane(
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
function showHelperImage(obj)
{
	$(".kb-tile").each(function(){obj.removeClass("selected");});
	obj.addClass("selected");
	var q_id=obj.find('#q_id').html();
	if(!q_id)return;
     $('#mainImage').hide();
     $('#kb-container-helper').css('background-image', "url('../assets/images/ajax-loader.gif')");
     $('#mainImage').attr('src', '/stp/action?action=ShowQuestionImage&q_id='+q_id);
     $('#kb-container-helper').css('background-image', 'none');
     $('#mainImage').fadeIn();
}
function getNextQBRecord()
{
	$("#bkbHelp").hide("slow");
	var a_id=$(this).attr('id');
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
	ajaxPost("/stp/action",data,addTechnologyTile,error);
}
function addTechnologyTile(data)
{
	 addTile(data,"#hb-technologies");
}
function addTile(data,templateId)
{
	$(".kb-tile").each(function(){$(this).removeClass("selected");});

	var source = $(templateId).html(); 
	var template = Handlebars.compile(source); 	

	$("#kb-tiles").prepend(template(data));
	$(".kb-tile").show('slow');
	applyScroll();
}
