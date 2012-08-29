package org.esgf.dc.io.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
import org.esgf.dc.utils.NumFound;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import org.esgf.dc.io.model.Model;


public class SolrModelRecordReader {
	
	public static String HOST = "http://pcmdi9.llnl.gov";//"http://esg-datanode.jpl.nasa.gov";
	private static String LIMIT = "100";
	
	
	private String modelName;
	
	private Model model;
	
	private int totalNumFiles;
	
	
	public SolrModelRecordReader() {
		this.modelName = "";
		this.setTotalNumFiles(0);
		model = new Model(this.modelName);
		//this.searchAPIURL = "";
	}
	
	public SolrModelRecordReader(String modelName) {
		this.modelName = modelName;
		this.setTotalNumFiles(0);
		model = new Model(this.modelName);
		//this.searchAPIURL = HOST + "/esg-search/search?query=*&model=" + this.modelName + "&limit=" + LIMIT + "&offset=0";
	}
	
	
	public void doQueries() {
		

		int numFound = NumFound.getNumFoundForModel(modelName);
		
		String searchAPIURL = HOST + "/esg-search/search?query=*&model=" + this.modelName + "&limit=" + LIMIT + "&offset=0";
		
		int offset = 0;
		
		Map<String,List<String>> metadataMap = new TreeMap<String,List<String>>();
        
		
		while(numFound > 0) {

			//System.out.println("Beg numFound: " + numFound + " offset: " + offset);
			
			//this.searchAPIURL.replace(oldChar, newChar)
			int prevOffset = offset;
			System.out.println("\tissuing query " + searchAPIURL);
			
			
			String responseBody = this.getResponseBody(searchAPIURL);
			

			
			
			try {
	            DocumentBuilderFactory dbf =
	                DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            InputSource is = new InputSource();
	            is.setCharacterStream(new StringReader(responseBody));

	            Document doc = db.parse(is);
	            
	            doc.getDocumentElement().normalize();
	            
	            org.w3c.dom.Element fileElement = doc.getDocumentElement();
	            

	            int countLimit = Integer.parseInt(LIMIT);
	            if(countLimit > numFound) {
	            	countLimit = numFound;
	            }
	            
	            for(int docindex=0;docindex<countLimit;docindex++) {
	            	
	            	Node docElement = fileElement.getElementsByTagName("doc").item(docindex);
		            
	            	NodeList nList = docElement.getChildNodes();
		            
		            for(int i=0;i<nList.getLength();i++) {
		            	Node nNode = nList.item(i);
		            	
		            	//only interested in "Element" node types
		            	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
		            		
		            		//System.out.println("nNode: " + nNode.getNodeName());
		            		if(nNode.getNodeName().equals("arr")) {
		            			
		            			
		            			Node attribute = nNode.getAttributes().item(0);
	                			
	                			//grab the key (usually only latest or replica)
	                			int firstIndex = attribute.toString().indexOf("\"");
	                			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
	                			
	                			//System.out.println("arr attr: " + attr);
	                			
	                			List<String> prevValues = metadataMap.get(attr);
	                			
	                			if(prevValues == null) {
	                				prevValues = new ArrayList<String>();
	                			}

	                			
	                			
	                			if(attr.equalsIgnoreCase("variable")
	                					
	                					|| 
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
		            			}
		            			
		            			
		            		} else if (nNode.getNodeName().equals("str")) {
		            			
		            			//grab the name of the "key" from the attribute of the str tag
		            			Node attribute = nNode.getAttributes().item(0);
		            		
		            			//grab the key (usually only latest or replica)
		            			int firstIndex = attribute.toString().indexOf("\"");
		            			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
		            			
		            			//put id into the metadata map
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
		                			
		                		}
		            			//put title into the metadata map
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
	            
	            
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
			
			numFound -= Integer.parseInt(LIMIT);
			offset += Integer.parseInt(LIMIT);
			searchAPIURL = searchAPIURL.replace("offset="+prevOffset, "offset="+offset);
			//System.out.println("\tReplacing offset=" + prevOffset + " with offset=" + offset);
		}

        model.setMetadata(metadataMap);
        model.setTotalNumFiles(this.totalNumFiles);
		
		
		
		
	}
	
	
	private String getResponseBody(String searchAPIURL) {
		

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
        
        return responseBody;
	}
	
	
	public static void main(String [] args) {
		
		String modelName = "EC-EARTH";
		
		SolrModelRecordReader reader = new SolrModelRecordReader(modelName);
		
		reader.doQueries();
		
		Model model = reader.getModel();
		

        System.out.println(model.getMetadata().get("variable").size());
        System.out.println(model.getTotalNumFiles());
	}

	public int getTotalNumFiles() {
		return totalNumFiles;
	}

	public void setTotalNumFiles(int totalNumFiles) {
		this.totalNumFiles = totalNumFiles;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
