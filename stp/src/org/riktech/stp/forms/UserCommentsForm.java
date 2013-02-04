package org.riktech.stp.forms;

import javax.servlet.http.HttpServletRequest;

import com.riktech.stp.dto.UserComments;
import com.riktech.stp.dto.Users;

public class UserCommentsForm {
	public static final String FORM_NAME="stp_ucf";
	public static final String USER_COMMENT_FIELD_NAME=FORM_NAME+"_uc";
	public static final String VISIBILITY_FIELD_NAME=FORM_NAME+"_v";

	public static UserComments getVO(HttpServletRequest request){
		UserComments uc=new UserComments();
		int currentVisibility=UserComments.VISIBILITY_PRIVATE;
		try{
			currentVisibility=Integer.parseInt(request.getParameter(VISIBILITY_FIELD_NAME));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Users currentUser=(Users)request.getSession().getAttribute("userProfile");
		uc.setUserName(currentUser.getUserName());
		uc.setUserComment(request.getParameter(USER_COMMENT_FIELD_NAME));
		uc.setVisibility(currentVisibility);
		return uc;
	}
}
