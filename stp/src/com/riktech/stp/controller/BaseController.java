package com.riktech.stp.controller;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riktech.stp.dao.UsersDao;
import com.riktech.stp.dto.BasicDTO;
import com.riktech.stp.dto.Users;
import com.riktech.stp.exceptions.UsersDaoException;
import com.riktech.stp.factory.UsersDaoFactory;
import com.riktech.stp.utils.StpServletUtils;

/**
 * Servlet implementation class BaseController
 */
public class BaseController extends HttpServlet {
    private static final SimpleDateFormat df = new SimpleDateFormat( "dd-MMM-yyyy" );
	private static final long serialVersionUID = 1L;
	public static final String[] reserveWords = { "package" };
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
       
	}
	protected void redirect(HttpServletResponse response, HttpServletRequest request, String url) {
		try {
			response.setHeader("Referer", request.getHeader("Referer"));
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//log.error(e, e);
		}
	}

	protected void redirect(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//log.error(e, e);
		}
	}

	protected void redirect(HttpServletResponse response, String url, Exception ex) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//log.error(e, e);
		}
	}

	protected void errorRedirect(HttpServletRequest request, HttpServletResponse response, Throwable e) {
		//log.error(e, e);
		errorRedirect(request, response, "/jsp/common/errorpage.jsp", e);
	}

	protected void errorRedirect(HttpServletRequest request, HttpServletResponse response, String errorPageURL, Throwable e) {
		//log.error(e, e);
		request.setAttribute("jspException", e);
		dispatch(request, response, errorPageURL);
	}	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String jspRelativePath) {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(jspRelativePath);
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.error(e, e);
			redirect(response, "/Xplore/error.jsp");
		}
	}	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletAction = null;
		try {
			//log.debug(" called from ---->  " + getMethodName(true, 2));
			String action = request.getRequestURI();
			if (action.indexOf("/") >= 0) {
				action = action.substring(action.lastIndexOf("/") + 1);
			}

			if (action.toLowerCase().indexOf(".jsp") >= 0) {
				action = action.substring(0, action.toLowerCase().indexOf(".jsp"));
			}

			request.setAttribute("action", action);
			servletAction = action;
			if (isActionReserveWord(servletAction)) {
				servletAction = "m_" + servletAction;
			}
			Method method = this.getClass().getMethod(servletAction, HttpServletRequest.class, HttpServletResponse.class);
			System.out.println(request.getRequestURI() + " --> " + servletAction);
			//log.debug(request.getRequestURI() + " --> " + servletAction);
			method.invoke(this, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			//log.error(e, e);
			this.errorRedirect(request, response, "/jsp/common/errorpage.jsp", e);
		} 

	}
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (beforeProcess(request, response)) {
				perform(request, response);
			} else {
				redirect(response, "/Xplore/error.jsp");
			}
		} catch (UsersDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redirect(response, "/Xplore/error.jsp");
		}
	}
	protected boolean beforeProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, UsersDaoException{
		Users user=null;
		if(request.getSession().getAttribute("userProfile")==null)
	  	{
	  		UsersDao udao=UsersDaoFactory.create();
	  		user=udao.findByPrimaryKey(request.getRemoteUser());
	  		request.getSession().setAttribute("userProfile", user);
	  	}
		return true;
	}
	private boolean isActionReserveWord(String action) {
		for (String reserveWord : reserveWords) {
			if (reserveWord.equalsIgnoreCase(action)) {
				return true;
			}
		}
		return false;

	}
   protected static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
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
