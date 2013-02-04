package com.riktech.stp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riktech.stp.forms.UserCommentsForm;

import com.riktech.stp.dao.UserCommentsDao;
import com.riktech.stp.dto.UserComments;
import com.riktech.stp.exceptions.UserCommentsDaoException;
import com.riktech.stp.factory.UserCommentsDaoFactory;
import com.riktech.stp.jdbc.UserCommentsDaoImpl;

/**
 * Servlet implementation class AppController
 */
public class AppController extends BaseController implements Servlet {
	private static final long serialVersionUID = 1L;
	public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request,response,"/browse/browseKnowledgeBase.jsp");
	}
	public void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserComments dto=UserCommentsForm.getVO(request);
		UserCommentsDao dao=UserCommentsDaoFactory.create();
		try {
			dao.insert(dto);
			showComments(request,response);
		} catch (UserCommentsDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addAnswerChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void showComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserComments dto=UserCommentsForm.getVO(request);
		UserCommentsDao dao=UserCommentsDaoFactory.create();
		try {
			ArrayList<UserComments> ucList=dao.findByDynamicWhere("USER_NAME=? AND VISIBILITY=? ORDER BY MODIFIED_DATE desc", new Object[]{dto.getUserName(),dto.getVisibility()});
			this.ajaxForward(ucList, request, response);
		} catch (UserCommentsDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		redirect(response,"/stp/home");
	}

}
