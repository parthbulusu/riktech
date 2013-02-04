package com.riktech.stp.servlet.actions.find;

import java.util.ArrayList;

import com.riktech.stp.servlet.*;
import com.riktech.stp.dao.*;
import com.riktech.stp.dto.*;
import com.riktech.stp.factory.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AnswerChoicesFindWhereAnsChoiceEqualsWebAction extends WebAction
{
	/**
	 * Method 'execute'
	 * 
	 * @param servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	public void execute(WebController servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try {
			// parse parameters
			java.lang.String ansChoice = parseString( request, "ansChoice" );
		
			// create the DAO class
			AnswerChoicesDao dao = AnswerChoicesDaoFactory.create();
		
			// execute the finder
			ArrayList<AnswerChoices> list= dao.findWhereAnsChoiceEquals(ansChoice);
		
			// store the results
			request.setAttribute( "result", list );
		
			// forward request on to the appropriate JSP page to display the results
			forward( servlet, request, response, "/find/AnswerChoicesFindWhereAnsChoiceEqualsResult.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}
		
	}

}
