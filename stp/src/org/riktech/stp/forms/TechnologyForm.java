package org.riktech.stp.forms;

import javax.servlet.http.HttpServletRequest;
import com.riktech.stp.dto.Technology;


public class TechnologyForm {
	public static final String FORM_NAME="stp_tf";
	public static final String NAME_FIELD_NAME=FORM_NAME+"_n";
	public static final String ID_FIELD_NAME=FORM_NAME+"_id";
	public static final String QUESTION_ID_FIELD_NAME=FORM_NAME+"_qi";
	public static final String PARENT_ID_FIELD_NAME=FORM_NAME+"_pid";
	public static final String QUESTION_FIELD_NAME=FORM_NAME+"_nq";

	public static Technology getVO(HttpServletRequest request){
		Technology t=new Technology();
		t.setName(request.getParameter(NAME_FIELD_NAME));
		try{
			t.setId(Long.parseLong(request.getParameter(ID_FIELD_NAME)));
		}catch(NumberFormatException nfe){
			t.setId(-1);
		}
		try{
			t.setParentId(Long.parseLong(request.getParameter(PARENT_ID_FIELD_NAME)));
		}catch(NumberFormatException nfe){
			t.setId(-1);
		}	
		try{
			t.setParentId(Long.parseLong(request.getParameter(PARENT_ID_FIELD_NAME)));
		}catch(NumberFormatException nfe){
			t.setId(-1);
		}
		try{
			t.setQuestionId(Long.parseLong(request.getParameter(QUESTION_ID_FIELD_NAME)));
		}catch(NumberFormatException nfe){
			t.setId(-1);
		}		
		t.setQuestion(request.getParameter(QUESTION_FIELD_NAME));
		
		return t;		
	}
}
