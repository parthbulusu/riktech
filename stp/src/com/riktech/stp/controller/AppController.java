package com.riktech.stp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.riktech.stp.forms.AnswerChoicesForm;
import org.riktech.stp.forms.TechnologyForm;
import org.riktech.stp.forms.UserCommentsForm;

import com.riktech.stp.dao.AnswerChoicesDao;
import com.riktech.stp.dao.QuestionBankDao;
import com.riktech.stp.dao.TechnologyDao;
import com.riktech.stp.dao.UserCommentsDao;
import com.riktech.stp.dto.AnswerChoices;
import com.riktech.stp.dto.QuestionBank;
import com.riktech.stp.dto.QuestionBankPk;
import com.riktech.stp.dto.Technology;
import com.riktech.stp.dto.TechnologyPk;
import com.riktech.stp.dto.UserComments;
import com.riktech.stp.exceptions.AnswerChoicesDaoException;
import com.riktech.stp.exceptions.QuestionBankDaoException;
import com.riktech.stp.exceptions.TechnologyDaoException;
import com.riktech.stp.exceptions.UserCommentsDaoException;
import com.riktech.stp.factory.AnswerChoicesDaoFactory;
import com.riktech.stp.factory.QuestionBankDaoFactory;
import com.riktech.stp.factory.TechnologyDaoFactory;
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
	public void searchTechnology(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  TechnologyDaoException {
		String t_term=request.getParameter("t_term");
		if(StringUtils.isNotEmpty(t_term))
		{
			t_term=t_term.trim();
			TechnologyDao dao=TechnologyDaoFactory.create();
			ArrayList<Technology> qbList=dao.findWhereNameLike("%"+t_term+"%");
			this.ajaxForward(qbList, request, response);			
		}	
	}
	public void searchQuestionBank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, QuestionBankDaoException {
		String q_term=request.getParameter("q_term");
		if(StringUtils.isNotEmpty(q_term))
		{
			q_term=q_term.trim();
			QuestionBankDao dao=QuestionBankDaoFactory.create();
			ArrayList<QuestionBank> qbList=dao.findWhereQuestionLike("%"+q_term+"%");
			this.ajaxForward(qbList, request, response);
		}
		
	}
	public void qbAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request,response,"/admin/questionBank.jsp");
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
	public void saveTechnology(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, QuestionBankDaoException{
		Technology t=TechnologyForm.getVO(request);
		boolean insertt=true;
		boolean newquestion=false;
		QuestionBankDao qdao=QuestionBankDaoFactory.create();
		QuestionBankPk questPk=null;
		if(t.getQuestionId()<1 && StringUtils.isNotEmpty(t.getQuestion()))
		{
			try {
				newquestion=true;

				QuestionBank quest=new QuestionBank();
				quest.setQuestion(t.getQuestion());
				questPk = qdao.insert(quest);
				t.setQuestionId(questPk.getId());
			} catch (QuestionBankDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				insertt=false;
				
			}
			
		}
		if(insertt)
		{
			try {
				TechnologyDao tdao=TechnologyDaoFactory.create();
				if(t.getId()<1){
					tdao.insert(t);
				}else{
					tdao.update(new TechnologyPk(t.getId()), t);
				}
			} catch (TechnologyDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(newquestion){
					qdao.delete(questPk);
				}
			}
		}
		
		this.ajaxForward(t, request, response);
	}	
	public void saveAnswerChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, QuestionBankDaoException{
		AnswerChoices ac=AnswerChoicesForm.getVO(request);
		boolean insertac=true;
		boolean newquestion=false;
		QuestionBankDao qdao=QuestionBankDaoFactory.create();
		QuestionBankPk questPk=null;
		if(ac.getNextQuestId()<1)
		{

			try {
				newquestion=true;

				QuestionBank quest=new QuestionBank();
				quest.setQuestion(ac.getNextQuestion());
				questPk = qdao.insert(quest);
				ac.setNextQuestId(questPk.getId());
			} catch (QuestionBankDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				insertac=false;
				
			}
			
		}
		if(insertac)
		{
			try {
				AnswerChoicesDao adao=AnswerChoicesDaoFactory.create();
				adao.insert(ac);
			} catch (AnswerChoicesDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(newquestion){
					qdao.delete(questPk);
				}
			}
		}
		this.ajaxForward(ac, request, response);
		
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
