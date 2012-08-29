package org.esgf.dc.web;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.esgf.dc.Metadata;
import org.esgf.dc.io.model.FGDCModelRecordWriter;
import org.esgf.dc.io.model.Model;
import org.esgf.dc.io.model.SolrModelRecordReader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TransformationController {

	
	@RequestMapping(method=RequestMethod.GET, value="/metadata")
    public void getMetadataRequest(HttpServletRequest request,final HttpServletResponse response1)  {
		//System.out.println("In getMetadataRequest");
		//System.out.println("TIME: "+System.currentTimeMillis());
		
		String response = null;
		
		String modelName = request.getParameter("model");
		
		if(modelName == null) {
			modelName = "nullModel";
		}
		
		String format = request.getParameter("format");
		if(format == null) {
			format = "xml";
		}
		
		String type = request.getParameter("type");
		if(type == null) {
			type = "fgdc";
		}
		
		SolrModelRecordReader reader = new SolrModelRecordReader(modelName);
		reader.doQueries();
		Model model = reader.getModel();
		
		FGDCModelRecordWriter fgdc = new FGDCModelRecordWriter(true);
		
		fgdc.setModel(model);

		fgdc.setFileName("fgdc-" + model.getModelName() + ".xml");
		
		fgdc.writeFGDC();
		
		Metadata metadata = fgdc.getMetadata();
		
		
		if(format.equalsIgnoreCase("json")) {
			response1.setContentType("text/json");
			try {
				response = metadata.toJSON();
				
				response1.getWriter().write( response );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response1.setContentType("text/xml");
			try {
				response = metadata.toXML();
				
				response1.getWriter().write( response );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//return response;
	}
	
	
	/**
	 * For testing purpose only      
	 *
	 * A dummy main program to be used for testing purpose
	 *     
	 */
	
	public static void main(String args[]){
		final MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		final MockHttpServletResponse mockResponse = new MockHttpServletResponse();

		mockRequest.setParameter("model", "ACCESS1.0");
		
		//Set dummy parameter for testing purpose.
		TransformationController trans = new TransformationController();
		
		trans.getMetadataRequest(mockRequest,mockResponse);
		
		
		//System.out.println(response);
	}
	
	
}
