package com.riktech.stp.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.riktech.stp.dto.BasicDTO;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public abstract class WebAction
{
    private static final SimpleDateFormat df = new SimpleDateFormat( "dd-MMM-yyyy" );

	/**
	 * Method 'execute'
	 *
	 * @param servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	public abstract void execute(WebController servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException;

	/**
	 * Method 'forward'
	 *
	 * @param servlet
	 * @param request
	 * @param response
	 * @param page
	 * @throws ServletException
	 */
	public void forward(WebController servlet, HttpServletRequest request, HttpServletResponse response, String page) throws ServletException
	{
		try {
			ServletContext ctx = servlet.getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher( page );
			if (dispatcher == null) {
				throw new RuntimeException( "No dispatcher found for " + page );
			}

			dispatcher.forward( request, response );
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException( "Failed to process request", e );
		}

	}
	public void ajaxForward(BasicDTO dto, HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList<BasicDTO> list=new ArrayList<BasicDTO>();
		list.add(dto);
		ajaxForward(list, request, response);
	}
	public void ajaxForward(ArrayList list, HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			
			out = response.getWriter();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.print(getJson(list));
				out.flush();
			}
		}
		return;
	}
	private StringBuffer getJson(ArrayList list){
		StringBuffer sbuff=new StringBuffer("{\n\"uid\":\""+System.currentTimeMillis()+"\",\"json-result\":[\n");
		for(int i=0;i<list.size();i++){
			BasicDTO dto=(BasicDTO)list.get(i);
			sbuff.append(dto.getJson());
			if((i+1)<list.size())
			sbuff.append(",");
		}
		sbuff.append("\n]\n}");
		return sbuff;
		
	}
    public String parseString(HttpServletRequest request, String paramName) {
        return request.getParameter( paramName );
    }

    public BigDecimal parseBigDecimal(HttpServletRequest request, String paramName) {
        return new BigDecimal( parseString(request, paramName) );
    }

    public Date parseDate(HttpServletRequest request, String paramName)
        throws ParseException {

        synchronized (df) {
            return df.parse(request.getParameter( paramName ));
        }
    }

    public short parseShort(HttpServletRequest request, String paramName) {
        return Short.parseShort( parseString(request, paramName) );
    }

    public int parseInt(HttpServletRequest request, String paramName) {
        return Integer.parseInt( parseString(request, paramName) );
    }

    public long parseLong(HttpServletRequest request, String paramName) {
        return Long.parseLong( parseString(request, paramName) );
    }

    public float parseFloat(HttpServletRequest request, String paramName) {
        return Float.parseFloat( parseString(request, paramName) );
    }

    public double parseDouble(HttpServletRequest request, String paramName) {
        return Double.parseDouble( parseString(request, paramName) );
    }
}


