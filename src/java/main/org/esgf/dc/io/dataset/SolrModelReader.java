package org.esgf.dc.io.dataset;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SolrModelReader {

	
	private List<String> modelNames;
	
	private Map<String,List<String>> modelMap;


	public static String host = "http://esg-datanode.jpl.nasa.gov";
	
	//private static String searchAPIURL;// = host + "/esg-search/search?query=*&limit=1&";

	public static void main(String [] args) {
		
		SolrModelReader smr = new SolrModelReader();
		
		//smr.populateModelsList();
		
		
		//List<String> modelNames = smr.getModelNames();
		
		//Map<String,List<String>> modelMap = smr.getModelMap();
		
		//System.out.println(modelMap.size());
		
	}
	
	public SolrModelReader() {
		this.modelNames = new ArrayList<String>();
		
		
		this.modelMap = new TreeMap<String,List<String>>();
		//populateModelMap();
		
		
		
	}
	
	public void populateModelMap() {
		
		this.modelMap = new TreeMap<String,List<String>>();
		
		// create an http client
        HttpClient client = new HttpClient();
        
		System.out.println("Fetching model map");
		//for(int i=0;i<5;i++) {
		for(int i=0;i<modelNames.size();i++) {
			
			String searchAPIURL = host + "/esg-search/search?query=*&limit=0&facets=id&model=" + modelNames.get(i);
			
			System.out.println("\tissuing request: " + searchAPIURL);
			
			GetMethod method = new GetMethod(searchAPIURL);
	        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	                new DefaultHttpMethodRetryHandler(3, false));
	        
	        String responseBody = null;
	        
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
	        
	        //System.out.println("response: \n\n" + responseBody);
	        
	        
	        
	        try {
	            DocumentBuilderFactory dbf =
	                DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            InputSource is = new InputSource();
	            is.setCharacterStream(new StringReader(responseBody));

	            
	            Document doc = db.parse(is);
	            
	            doc.getDocumentElement().normalize();
	            
	            org.w3c.dom.Element fileElement = doc.getDocumentElement();
	            
	            
	            NodeList responseList = fileElement.getChildNodes();

	            //5th item is the facet_counts element
	            Node nNode = responseList.item(5);
	            
	            //Node nNodeAttribute = nNode.getAttributes().item(0);
				
	            //if(nNodeAttribute.toString().contains("facet_counts")) {

	            NodeList facet_countsList = nNode.getChildNodes();

	            //3rd item is the facet_fields element
	            Node facet_countsNode = facet_countsList.item(3);
	            if(facet_countsNode.getNodeType() == Node.ELEMENT_NODE) {
	            	Node modelNode = facet_countsNode.getChildNodes().item(1);
	            	NodeList modelNodeList = modelNode.getChildNodes();
	            	
	            	List<String> modelIdList = new ArrayList<String>();
	            	for(int k=0;k<modelNodeList.getLength();k++) {
	            		Node modelName = modelNodeList.item(k);
	            		if(modelName.getNodeType() == Node.ELEMENT_NODE) {
	            			Node model = modelName.getAttributes().item(0);
	                            	
	            			//grab the key (usually only latest or replica)
	            			int firstIndex = model.toString().indexOf("\"");
	            			String modelN = model.toString().substring(firstIndex+1,model.toString().length()-1);
	            			//System.out.println("modelName: " + modelNames.get(i) + " model: " + modelN);
	            			modelIdList.add(modelN);
	            			//this.modelMap.put(arg0, arg1)
	            		}
	            	}
	            	this.modelMap.put(modelNames.get(i), modelIdList);
	            }
	                	
	            
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
			
	        
			
		}
		
	}
	
	public void populateModelsList() {
		
		this.modelNames = new ArrayList<String>();
		
		// create an http client
        HttpClient client = new HttpClient();
        
		String searchAPIURL = host + "/esg-search/search?query=*&limit=1&facets=model";
		
		GetMethod method = new GetMethod(searchAPIURL);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        String responseBody = null;
        
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
        
		
        try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(responseBody));

            
            Document doc = db.parse(is);
            
            doc.getDocumentElement().normalize();
            
            org.w3c.dom.Element fileElement = doc.getDocumentElement();
            
            
            NodeList responseList = fileElement.getChildNodes();

            //5th item is the facet_counts element
            Node nNode = responseList.item(5);
            
            //Node nNodeAttribute = nNode.getAttributes().item(0);
			
            //if(nNodeAttribute.toString().contains("facet_counts")) {

            NodeList facet_countsList = nNode.getChildNodes();

            //3rd item is the facet_fields element
            Node facet_countsNode = facet_countsList.item(3);
            if(facet_countsNode.getNodeType() == Node.ELEMENT_NODE) {
            	Node modelNode = facet_countsNode.getChildNodes().item(1);
            	NodeList modelNodeList = modelNode.getChildNodes();
            	for(int k=0;k<modelNodeList.getLength();k++) {
            		Node modelName = modelNodeList.item(k);
            		if(modelName.getNodeType() == Node.ELEMENT_NODE) {
            			Node model = modelName.getAttributes().item(0);
                            	
            			//grab the key (usually only latest or replica)
            			int firstIndex = model.toString().indexOf("\"");
            			String modelN = model.toString().substring(firstIndex+1,model.toString().length()-1);
            			//System.out.println("model: " + modelN);
            			this.modelNames.add(modelN);
            		}
            	}
            }
                	
            
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	

	public List<String> getModelNames() {
		return modelNames;
	}

	public void setModelNames(List<String> modelNames) {
		this.modelNames = modelNames;
	}
	

	public Map<String, List<String>> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, List<String>> modelMap) {
		this.modelMap = modelMap;
	}
}
