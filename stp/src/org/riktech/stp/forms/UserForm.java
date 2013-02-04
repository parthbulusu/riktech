package org.riktech.stp.forms;

import javax.servlet.http.HttpServletRequest;

import com.riktech.stp.dto.Users;



public class UserForm {
	public static final String FORM_NAME="stp_uf";
	public static final String USER_NAME_FIELD_NAME=FORM_NAME+"_un";
	public static final String USER_PASS_FIELD_NAME=FORM_NAME+"_pwd";
	public static final String FIRST_NAME_FIELD_NAME=FORM_NAME+"_fn";
	public static final String LAST_NAME_FIELD_NAME=FORM_NAME+"_ln";
	public static final String EMAIL_FIELD_NAME=FORM_NAME+"_ln";
	protected Users getVO(HttpServletRequest request){
		Users up=new Users();
		up.setFirstName(request.getParameter(FIRST_NAME_FIELD_NAME));
		up.setLastName(request.getParameter(LAST_NAME_FIELD_NAME));
		up.setUserName(request.getParameter(USER_NAME_FIELD_NAME));
		up.setUserPass(request.getParameter(USER_PASS_FIELD_NAME));
		up.setEmail(request.getParameter(EMAIL_FIELD_NAME));
		return up;
	}
}
