package com.riktech.stp.servlet.actions.browse;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.riktech.stp.dao.AnswerChoicesDao;
import com.riktech.stp.dao.QuestionBankDao;
import com.riktech.stp.dao.TechnologyDao;
import com.riktech.stp.dto.AnswerChoices;
import com.riktech.stp.dto.QuestionBank;
import com.riktech.stp.dto.QuestionBankPk;
import com.riktech.stp.dto.Technology;
import com.riktech.stp.factory.AnswerChoicesDaoFactory;
import com.riktech.stp.factory.QuestionBankDaoFactory;
import com.riktech.stp.factory.TechnologyDaoFactory;
import com.riktech.stp.servlet.WebAction;
import com.riktech.stp.servlet.WebController;



public class BrowseKnowledgeBaseWebAction extends WebAction {

	@Override
	public void execute(WebController servlet, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		String technologyId=request.getParameter("t_id");
		String questionId=request.getParameter("q_id");
		String tsTypeId=request.getParameter("ts_id");
		int i_tsTypeId=TechnologyDaoFactory.TECHNOLOGY_BASED_TS;
		response.setContentType("application/json");
		long l_questionId=-1;
		long l_technologyId=-1;
		try{
			l_questionId=Long.parseLong(questionId);
		}
		catch(Exception e){
		}	
		try{
			l_technologyId=Long.parseLong(technologyId);
		}
		catch(Exception e){
		}		
		try{
			i_tsTypeId=Integer.parseInt(tsTypeId);
		}catch(Exception e){
		}	
		try{
			if(l_questionId>0){
				
				QuestionBankDao qbDao=QuestionBankDaoFactory.create();
				AnswerChoicesDao acDao=AnswerChoicesDaoFactory.create();
				QuestionBank question=qbDao.findByPrimaryKey(l_questionId);
				ArrayList<AnswerChoices> answerChoices=acDao.findWhereCurrentQuestIdEquals(l_questionId);
				question.setAnswerChoices(answerChoices);
				ajaxForward(question,request,response);
			}else if (l_technologyId>0){
				
				TechnologyDao tDao=TechnologyDaoFactory.create(i_tsTypeId);
				ArrayList<Technology> technologies=tDao.findWhereParentIdEquals(l_technologyId);
				ajaxForward(technologies,request,response);
			}else{
				TechnologyDao tDao=TechnologyDaoFactory.create(i_tsTypeId);
				ArrayList<Technology> technologies=tDao.findAllParents();
				ajaxForward(technologies,request,response);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}				
		
	}

}

