package com.riktech.stp.servlet.actions.browse;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riktech.stp.dao.QuestionImagesDao;
import com.riktech.stp.dto.QuestionImages;
import com.riktech.stp.exceptions.QuestionImagesDaoException;
import com.riktech.stp.factory.QuestionImagesDaoFactory;
import com.riktech.stp.servlet.WebAction;
import com.riktech.stp.servlet.WebController;

public class ShowQuestionImageWebAction extends WebAction {
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	QuestionImagesDao qidao=QuestionImagesDaoFactory.create();
	@Override
	public void execute(WebController servlet, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
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
	            //response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            //return;
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
   private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
}
