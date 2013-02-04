package com.riktech.stp.servlet;

import com.riktech.stp.servlet.actions.*;
import com.riktech.stp.servlet.actions.browse.BrowseKnowledgeBaseWebAction;
import com.riktech.stp.servlet.actions.browse.ShowQuestionImageWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindAllWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindByPrimaryKeyWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindByQuestionBank2WebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindByQuestionBankWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindWhereAnsChoiceEqualsWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindWhereCurrentQuestIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindWhereIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.AnswerChoicesFindWhereNextQuestIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.QuestionBankFindAllWebAction;
import com.riktech.stp.servlet.actions.find.QuestionBankFindByPrimaryKeyWebAction;
import com.riktech.stp.servlet.actions.find.QuestionBankFindWhereIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.QuestionBankFindWhereQuestionEqualsWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindAllWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindByPrimaryKeyWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindWhereIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindWhereNameEqualsWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindWhereParentIdEqualsWebAction;
import com.riktech.stp.servlet.actions.find.TechnologyFindWhereQuestionIdEqualsWebAction;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WebController extends HttpServlet
{
	protected Map actionMap;

	/**
	 * Method 'WebController'
	 * 
	 */
	public WebController()
	{
		actionMap = new HashMap();
		actionMap.put( "AnswerChoicesFindByPrimaryKey", AnswerChoicesFindByPrimaryKeyWebAction.class );
		actionMap.put( "AnswerChoicesFindAll", AnswerChoicesFindAllWebAction.class );
		actionMap.put( "AnswerChoicesFindByQuestionBank", AnswerChoicesFindByQuestionBankWebAction.class );
		actionMap.put( "AnswerChoicesFindByQuestionBank2", AnswerChoicesFindByQuestionBank2WebAction.class );
		actionMap.put( "AnswerChoicesFindWhereIdEquals", AnswerChoicesFindWhereIdEqualsWebAction.class );
		actionMap.put( "AnswerChoicesFindWhereCurrentQuestIdEquals", AnswerChoicesFindWhereCurrentQuestIdEqualsWebAction.class );
		actionMap.put( "AnswerChoicesFindWhereAnsChoiceEquals", AnswerChoicesFindWhereAnsChoiceEqualsWebAction.class );
		actionMap.put( "AnswerChoicesFindWhereNextQuestIdEquals", AnswerChoicesFindWhereNextQuestIdEqualsWebAction.class );
		actionMap.put( "QuestionBankFindByPrimaryKey", QuestionBankFindByPrimaryKeyWebAction.class );
		actionMap.put( "QuestionBankFindAll", QuestionBankFindAllWebAction.class );
		actionMap.put( "QuestionBankFindWhereIdEquals", QuestionBankFindWhereIdEqualsWebAction.class );
		actionMap.put( "QuestionBankFindWhereQuestionEquals", QuestionBankFindWhereQuestionEqualsWebAction.class );
		actionMap.put( "TechnologyFindByPrimaryKey", TechnologyFindByPrimaryKeyWebAction.class );
		actionMap.put( "TechnologyFindAll", TechnologyFindAllWebAction.class );
		actionMap.put( "TechnologyFindWhereIdEquals", TechnologyFindWhereIdEqualsWebAction.class );
		actionMap.put( "TechnologyFindWhereNameEquals", TechnologyFindWhereNameEqualsWebAction.class );
		actionMap.put( "TechnologyFindWhereParentIdEquals", TechnologyFindWhereParentIdEqualsWebAction.class );
		actionMap.put( "TechnologyFindWhereQuestionIdEquals", TechnologyFindWhereQuestionIdEqualsWebAction.class );
		actionMap.put( "BrowseKnowledgeBase", BrowseKnowledgeBaseWebAction.class );
		actionMap.put( "ShowQuestionImage", ShowQuestionImageWebAction.class );

	}

	/**
	 * Method 'doPost'
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try {
			// get the action parameter
			String action = request.getParameter( "action" );
			if (action == null) {
				throw new ServletException( "Parameter 'action' is required" );
			}
		
			// create the appropriate WebAction class
			Class webActionClass = (Class) actionMap.get( action );
			if (webActionClass == null) {
				throw new ServletException( "Invalid action '" + action + "'" );
			}
		
			// instantiate the action class and execute it
			WebAction webAction = (WebAction) webActionClass.newInstance();
			webAction.execute( this, request, response );
		}
		catch (ServletException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request:" + e.getMessage(), e );
		}
		
	}

	/**
	 * Method 'doGet'
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		doPost( request, response );
	}

}
