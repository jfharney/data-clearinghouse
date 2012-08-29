package org.esgf.dc.io.dataset;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.esgf.dc.io.model.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class SolrModelRecordReader {

	
	private String modelId;
	
	private Model model;
	
	private int totalNumFiles;
	
	public static String host = "http://esg-datanode.jpl.nasa.gov";
	
	private static String searchAPIURL;// = host + "/esg-search/search?query=*&limit=1&";

	private static String SAMPLE_FILE = "/Users/8xo";
	
	public static void main(String [] args) {
		
		String modelId = "CAM5";
		
		SolrModelRecordReader solr = new SolrModelRecordReader(modelId);
		
		
		Model m = solr.assembleModel();
		
		
		Map<String,List<String>> metadataMap = solr.getModel().getMetadata();
		for(String key : metadataMap.keySet()) {
			System.out.println("key:  " + key);
		}
	}
	
	public SolrModelRecordReader() {
	}
	
	public SolrModelRecordReader(String modelId) {
		this.setModelId(modelId);
		this.setTotalNumFiles(0);
	}
	
	
	public Model assembleModel() {
		
		System.out.println("Assembling model..." + modelId);
		
		
		// create an http client
        HttpClient client = new HttpClient();
        
      //assemble the search api url
        searchAPIURL = host + "/esg-search/search?query=*&limit=2001&" + "model=" + this.modelId;
        
        
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
        
        

		
		
      
        

        Model model = new Model();
        
        
        try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(responseBody));

            Document doc = db.parse(is);
            
            doc.getDocumentElement().normalize();
            
            org.w3c.dom.Element fileElement = doc.getDocumentElement();
            
            
            model.setModelName(this.modelId);

            NodeList resultAttributes = fileElement.getElementsByTagName("result");
            
            //get the num found here
            
            int numFound = getNumFound(resultAttributes);
            
            System.out.println("Numfound: " + numFound);
            
            
            
            //Node docElement1 = fileElement.getElementsByTagName("doc").item(docindex);
        	
            
            Map<String,List<String>> metadataMap = new TreeMap<String,List<String>>();
            
            if(numFound > 2001) {
            	numFound = 2001;
            }
            
            
            for(int docindex=0;docindex<numFound;docindex++) {
            	
            
	            Node docElement = fileElement.getElementsByTagName("doc").item(docindex);
	
	            System.out.println("docIndex: " + docindex + " " + docElement.getNodeName());
	            
	            //System.exit(0);
	            
	            NodeList nList = docElement.getChildNodes();
	            
	            for(int i=0;i<nList.getLength();i++) {
	            	Node nNode = nList.item(i);
	            	//only interested in "Element" node types
	            	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	            		//System.out.println("nNode: " + nNode.getNodeName());
	            		if(nNode.getNodeName().equals("arr")) {
	            			//grab the name of the "key" from the attribute of the bool tag
                			Node attribute = nNode.getAttributes().item(0);
                			
                			//grab the key (usually only latest or replica)
                			int firstIndex = attribute.toString().indexOf("\"");
                			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
                			
                			//System.out.println("arr attr: " + attr);
                			
                			List<String> prevValues = metadataMap.get(attr);
                			
                			if(prevValues == null) {
                				prevValues = new ArrayList<String>();
                			}

                			
                			
                			if(attr.equalsIgnoreCase("variable") || 
                					attr.equalsIgnoreCase("cf_standard_name") ||
                					attr.equalsIgnoreCase("variable_long_name") ||
                					attr.equalsIgnoreCase("experiment") ||
                					attr.equalsIgnoreCase("experiment_family") ||
                					attr.equalsIgnoreCase("model") ||
                					attr.equalsIgnoreCase("realm") ||
                					attr.equalsIgnoreCase("institute") ||
                					attr.equalsIgnoreCase("product") ||
                					attr.equalsIgnoreCase("description") ||
                					attr.equalsIgnoreCase("project")
                					) {
                				
                    			
                    			
              			    	for(int k=0;k<nNode.getChildNodes().getLength();k++) {
              			    		Node item = nNode.getChildNodes().item(k);

              			    		
              			    		if(item.getNodeType() == Node.ELEMENT_NODE) {
              			    			String value = item.getChildNodes().item(0).getNodeValue().toString();
              			    			
              			    			//System.out.println(value);
              			    			
              			    			if(!prevValues.contains(value)) {
                            				prevValues.add(value);
                            			}
                            			
              			    		}
              			    		
              			    	}
              			    	
              			    	metadataMap.put(attr, prevValues);
                    			
                				
                			}

                			
                			
	            		} else if (nNode.getNodeName().equals("int")) {
	            			//grab the name of the "key" from the attribute of the str tag
	            			Node attribute = nNode.getAttributes().item(0);
	            		
	            			//grab the key (usually only latest or replica)
	            			int firstIndex = attribute.toString().indexOf("\"");
	            			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
	            			
	            			if(attr.equals("number_of_files")) {
	            			
	            				String value = nNode.getChildNodes().item(0).getNodeValue();
	                			
	            				int numFiles = Integer.parseInt(value);
	            				
	            				//System.out.println("number of files " + numFiles);
	            				
	            				this.setTotalNumFiles(this.getTotalNumFiles()
										+ numFiles);
	            				//System.exit(0);
	            			}
	            		}
	            		else if (nNode.getNodeName().equals("str")) {
	            			
	            			
	            			//grab the name of the "key" from the attribute of the str tag
	            			Node attribute = nNode.getAttributes().item(0);
	            		
	            			//grab the key (usually only latest or replica)
	            			int firstIndex = attribute.toString().indexOf("\"");
	            			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
	            			
	            			if(attr.equals("id")) {
	

	                			String value = nNode.getChildNodes().item(0).getNodeValue();
	                			
	                			List<String> prevValues = metadataMap.get(attr);
	                			if(prevValues == null) {
	                				prevValues = new ArrayList<String>();
	                			}

	                			
	                			if(!prevValues.contains(value)) {
	                				prevValues.add(value);
	                			}
	                			metadataMap.put(attr, prevValues);
	                			
	                			
	                			//System.exit(0);
	                			
	            			}
	            			else if(attr.equals("title")) {
	            				

	                			String value = nNode.getChildNodes().item(0).getNodeValue();
	                			
	                			List<String> prevValues = metadataMap.get(attr);
	                			if(prevValues == null) {
	                				prevValues = new ArrayList<String>();
	                			}

	                			
	                			if(!prevValues.contains(value)) {
	                				prevValues.add(value);
	                			}
	                			metadataMap.put(attr, prevValues);
	                			
	                			
	                			
	            			}
	            			
	            		}
	            		
	            		
	            	}
	            }
	            
            }
            
            model.setMetadata(metadataMap);
            
            
            
        }catch(Exception e) {
        	
        	e.printStackTrace();
        
        
        }
        
        model.setTotalNumFiles(this.totalNumFiles);
        
        this.setTotalNumFiles(this.totalNumFiles);
        
        this.setModel(model);
        
        
        
        
		return model;
	}
	
	private int getNumFound(NodeList resultNodes) {
		int numFound = -1;
		
		for(int i=0;i<resultNodes.item(0).getAttributes().getLength();i++) {
        	if((resultNodes.item(0).getAttributes().item(i).toString()).contains("numFound")) {

                //System.out.println(resultNodes.item(0).getAttributes().item(i).toString());
                
                String numFoundStr = resultNodes.item(0).getAttributes().item(i).toString().split("=")[1];
                
                numFoundStr = numFoundStr.substring(1,numFoundStr.length()-1);
                
                
                numFound = Integer.parseInt(numFoundStr);
                
        	}
        }
		return numFound;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public int getTotalNumFiles() {
		return totalNumFiles;
	}

	public void setTotalNumFiles(int totalNumFiles) {
		this.totalNumFiles = totalNumFiles;
	}
    
	
	
}
