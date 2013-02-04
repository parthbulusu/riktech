package com.riktech.stp.servlet.actions.find;

import java.util.ArrayList;

import com.riktech.stp.servlet.*;
import com.riktech.stp.dao.*;
import com.riktech.stp.dto.*;
import com.riktech.stp.factory.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TechnologyFindWhereQuestionIdEqualsWebAction extends WebAction
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
			long questionId = parseLong( request, "questionId" );
		
			// create the DAO class
			TechnologyDao dao = TechnologyDaoFactory.create();
		
			// execute the finder
			ArrayList<Technology> list = dao.findWhereQuestionIdEquals(questionId);
		
			// store the results
			request.setAttribute( "result", list);
		
			// forward request on to the appropriate JSP page to display the results
			forward( servlet, request, response, "/find/TechnologyFindWhereQuestionIdEqualsResult.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}
		
	}

}
