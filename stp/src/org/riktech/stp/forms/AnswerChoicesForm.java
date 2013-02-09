package org.riktech.stp.forms;

import javax.servlet.http.HttpServletRequest;

import com.riktech.stp.dto.AnswerChoices;
import com.riktech.stp.dto.UserComments;
import com.riktech.stp.dto.Users;

public class AnswerChoicesForm {
	public static final String FORM_NAME="stp_acf";
	public static final String ANS_CHOICE_FIELD_NAME=FORM_NAME+"_ac";
	public static final String CURRENT_QUESTION_ID_FIELD_NAME=FORM_NAME+"_cqi";
	public static final String NEXT_QUESTION_ID_FIELD_NAME=FORM_NAME+"_nqi";
	public static final String NEXT_QUESTION_FIELD_NAME=FORM_NAME+"_nq";
	public static final String ID_FIELD_NAME=FORM_NAME+"_id";
	public static AnswerChoices getVO(HttpServletRequest request){
		AnswerChoices ac=new AnswerChoices();
		ac.setAnsChoice(request.getParameter(ANS_CHOICE_FIELD_NAME));
		try{
			ac.setNextQuestId(Long.parseLong(request.getParameter(NEXT_QUESTION_ID_FIELD_NAME)));	
		}catch(NumberFormatException nfe){
			//nfe.printStackTrace();
		}
		try{
			ac.setCurrentQuestId(Long.parseLong(request.getParameter(CURRENT_QUESTION_ID_FIELD_NAME)));	
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		try{
			ac.setId(Long.parseLong(request.getParameter(ID_FIELD_NAME)));
		}catch(NumberFormatException nfe){
			ac.setId(-1);
		}		
		ac.setNextQuestion(request.getParameter(NEXT_QUESTION_FIELD_NAME));
		return ac;
	}
}
