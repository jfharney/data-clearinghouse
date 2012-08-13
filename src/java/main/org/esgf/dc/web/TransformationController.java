package org.esgf.dc.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TransformationController {

	
	@RequestMapping(method=RequestMethod.GET, value="/metadata")
    public @ResponseBody String getSRMRequest(HttpServletRequest request) throws Exception {
		System.out.println("In getSRMRequest");
		System.out.println("TIME: "+System.currentTimeMillis());
		String response = "<srm_url>srm</srm_url>";
		
				
		return response;
	}
	
	
	/**
	 * For testing purpose only      
	 *
	 * A dummy main program to be used for testing purpose
	 *     
	 */
	
	public static void main(String args[]){
		final MockHttpServletRequest mockRequest = new MockHttpServletRequest();

		//Set dummy parameter for testing purpose.
		
	}
	
	
}
