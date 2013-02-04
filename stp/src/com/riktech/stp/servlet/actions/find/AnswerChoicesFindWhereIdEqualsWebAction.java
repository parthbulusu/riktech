package com.riktech.stp.servlet.actions.find;

import java.util.ArrayList;

import com.riktech.stp.servlet.*;
import com.riktech.stp.dao.*;
import com.riktech.stp.dto.*;
import com.riktech.stp.factory.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AnswerChoicesFindWhereIdEqualsWebAction extends WebAction
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
			long id = parseLong( request, "id" );
		
			// create the DAO class
			AnswerChoicesDao dao = AnswerChoicesDaoFactory.create();
		
			// execute the finder
			ArrayList<AnswerChoices> list= dao.findWhereIdEquals(id);
		
			// store the results
			request.setAttribute( "result", list );
		
			// forward request on to the appropriate JSP page to display the results
			forward( servlet, request, response, "/find/AnswerChoicesFindWhereIdEqualsResult.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}
		
	}

}
