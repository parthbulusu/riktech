package com.riktech.stp.utils;

import com.riktech.stp.dao.UsersDao;
import com.riktech.stp.dto.Users;
import com.riktech.stp.exceptions.UsersDaoException;
import com.riktech.stp.factory.UsersDaoFactory;


public class StpServletUtils {
	private static Users ref;
    public static Users getUserProfile(String userName) throws UsersDaoException
    {
      if (ref == null)
      {
    	  UsersDao udao=UsersDaoFactory.create();
    	  ref=udao.findByPrimaryKey(userName);
      }
      return ref;
    }	

}
