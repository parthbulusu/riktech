package com.riktech.stp.servlet.actions.find;

import com.riktech.stp.servlet.*;
import com.riktech.stp.dao.*;
import com.riktech.stp.dto.*;
import com.riktech.stp.factory.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QuestionBankFindByPrimaryKeyWebAction extends WebAction
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
			QuestionBankDao dao = QuestionBankDaoFactory.create();
		
			// execute the finder
			QuestionBank list = dao.findByPrimaryKey(id);
		
			// store the results
			request.setAttribute( "result", list);
		
			// forward request on to the appropriate JSP page to display the results
			forward( servlet, request, response, "/find/QuestionBankFindByPrimaryKeyResult.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}
		
	}

}
