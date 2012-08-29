package org.esgf.dc.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NumFound {

	private static String HOST = "http://esg-datanode.jpl.nasa.gov";
	
	public static int getNumFoundForModel(String modelName) {
		
		int numFound = -1;
		
		
		//assemble the search api url
        String searchAPIURL = HOST + "/esg-search/search?query=*&limit=1&" + "model=" + modelName;
        
        

		// create an http client
        HttpClient client = new HttpClient();
        
        GetMethod method = new GetMethod(searchAPIURL);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        

        String responseBody = "";
        
        
        try {
            // execute the method
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                    
            	System.out.println("error in retrieving page");
            }

            responseBody = method.getResponseBodyAsString();
        } catch (HTTPException e) {
            //LOG.error("Fatal protocol violation");
            e.printStackTrace();
        } catch (IOException e) {
            //LOG.error("Fatal transport error");
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        
        
        NodeList resultNodes = null;
        try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(responseBody));

            Document doc = db.parse(is);
            
            doc.getDocumentElement().normalize();
            
            org.w3c.dom.Element fileElement = doc.getDocumentElement();
            

            resultNodes = fileElement.getElementsByTagName("result");
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
        if(resultNodes != null) {
        	
        	for(int i=0;i<resultNodes.item(0).getAttributes().getLength();i++) {
            	if((resultNodes.item(0).getAttributes().item(i).toString()).contains("numFound")) {

                    //System.out.println(resultNodes.item(0).getAttributes().item(i).toString());
                    
                    String numFoundStr = resultNodes.item(0).getAttributes().item(i).toString().split("=")[1];
                    
                    numFoundStr = numFoundStr.substring(1,numFoundStr.length()-1);
                    
                    
                    numFound = Integer.parseInt(numFoundStr);
                    
            	}
            }
        }
        
		
		
		
		
		
		return numFound;
	}
	
}
