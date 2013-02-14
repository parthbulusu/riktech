package com.riktech.stp.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riktech.stp.dao.AnswerChoicesDao;
import com.riktech.stp.dao.QuestionBankDao;
import com.riktech.stp.dao.QuestionImagesDao;
import com.riktech.stp.dao.TechnologyDao;
import com.riktech.stp.dto.AnswerChoices;
import com.riktech.stp.dto.QuestionBank;
import com.riktech.stp.dto.QuestionImages;
import com.riktech.stp.dto.Technology;
import com.riktech.stp.dto.Users;
import com.riktech.stp.exceptions.QuestionImagesDaoException;
import com.riktech.stp.factory.AnswerChoicesDaoFactory;
import com.riktech.stp.factory.QuestionBankDaoFactory;
import com.riktech.stp.factory.QuestionImagesDaoFactory;
import com.riktech.stp.factory.TechnologyDaoFactory;

public class BrowseController extends BaseController {
	QuestionImagesDao qidao=QuestionImagesDaoFactory.create();
	QuestionBankDao qbDao=QuestionBankDaoFactory.create();
	AnswerChoicesDao acDao=AnswerChoicesDaoFactory.create();
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.	
	public void discuss(HttpServletRequest request, HttpServletResponse response){
		dispatch(request, response, "/browse/discuss.jsp");

	}
	public void nextQuestion(HttpServletRequest request, HttpServletResponse response){
		
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
		Users userProfile =(Users)request.getSession().getAttribute("userProfile");
		boolean deletePermission=false;
		if(userProfile.isAdminRole())deletePermission=true;		
		try{
			if(l_questionId>0){
				

				QuestionBank question=qbDao.findByPrimaryKey(l_questionId);
				

					
				ArrayList<AnswerChoices> answerChoices=acDao.findWhereCurrentQuestIdEquals(l_questionId,deletePermission);
				question.setAnswerChoices(answerChoices);
				ajaxForward(question,request,response);
			}else if (l_technologyId>0){
				
				TechnologyDao tDao=TechnologyDaoFactory.create(i_tsTypeId);
				ArrayList<Technology> technologies=tDao.findWhereParentIdEquals(l_technologyId,deletePermission);
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
	public void showImage(HttpServletRequest request, HttpServletResponse response){
	       // Get ID from request.
     String imageId = request.getParameter("q_id");
     // Prepare streams.
     BufferedOutputStream output = null;        
     try {
	        // Check if ID is supplied to the request.
	        if (imageId == null) {
	            // Do your thing if the ID is not supplied to the request.
	            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
	        }
	
	        // Lookup Image by ImageId in database.
	        // Do your "SELECT * FROM Image WHERE ImageID" thing.
	        
	        ArrayList<QuestionImages> qimages= qidao.findWhereQuestIdEquals(Long.parseLong(imageId));
	        if (qimages == null || qimages.size()<1) {
	            // Do your thing if the image does not exist in database.
	            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
	        }	        
			QuestionImages qimage=qimages.get(0);
	        // Check if image is actually retrieved from database.

	
	        // Init servlet response.
	        response.reset();
	        response.setBufferSize(DEFAULT_BUFFER_SIZE);
	        response.setContentType(qimage.getFileType());
	        response.setContentLength(qimage.getImage().length);
	        response.setHeader("Content-Disposition", "inline; filename=\"" + qimage.getFileName() + "\"");
	



         // Open streams.
         output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

         // Write file contents to response.
         output.write(qimage.getImage());
     } catch (NumberFormatException | QuestionImagesDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
         // Do your thing with the exception. Print it, log it or mail it.
         e.printStackTrace();
     }finally {
         // Gently close streams.
         close(output);
     }
	}
}
