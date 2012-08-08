import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.esgf.dc.Model;
import org.esgf.dc.SubModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class ESGFRecordReader {

	
    //private static String searchAPIURL = "http://pcmdi9.llnl.gov/esg-search/search?query=*&model=CESM1%28CAM5%29&limit=20";
	
	private String modelName;
	
	private static String searchAPIURL = "http://pcmdi9.llnl.gov/esg-search/search?query=*&limit=2&";//model=BNU-ESM&limit=30";
	

	
	public static void main(String [] args) {
		
		String modelName = "CAM5";
		ESGFRecordReader esgfRecordReader = new ESGFRecordReader(modelName);
		
		Model model = esgfRecordReader.assembleModel();
		
		System.out.println("Model size: " + model.getSubmodels().size());
		
		System.out.println("Model name: " + model.getModelName());
		
	}
	
	public ESGFRecordReader() {
		this.modelName = "";
	}
	
	public ESGFRecordReader(String modelName) {
		this.modelName = modelName;
	}
	
	public Model assembleModel() {
		
        
        String responseBody = null;

        // create an http client
        HttpClient client = new HttpClient();
        
        searchAPIURL += "model=" + this.modelName;

        
        System.out.println("Search API: " + searchAPIURL);
        
        //attact the dataset id to the query string
        GetMethod method = new GetMethod(searchAPIURL);
        
        
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        
        //method.setQueryString(this.solrQueryString);
        
        
        try {
            // execute the method
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                    //LOG.error("Method failed: " + method.getStatusLine());

            }

            // read the response
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
        
        
        //System.out.println("responseBody:\n" + responseBody);
        
        Model model = this.fromXML(responseBody);
        
		return model;
		
	}
	
	
	/** Description 
     * 
     * @param xmlStr
     */
    public Model fromXML(String xmlStr) {

        Model model = new Model();
        
        model.setModelName(this.modelName);
        
        try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlStr));

            Document doc = db.parse(is);
            
            doc.getDocumentElement().normalize();
            
            org.w3c.dom.Element fileElement = doc.getDocumentElement();
            
            
            System.out.println("Docs: " +  fileElement.getElementsByTagName("doc").getLength());
            
            Map<String,String> metadata = new HashMap<String,String>();
            String id = "";
            
            
            
            for(int i=0;i<fileElement.getElementsByTagName("doc").getLength();i++) {
            	
            	
            	
            	
            	
            	System.out.println("----Doc " + i + "----");
            	
            	Node docElement = fileElement.getElementsByTagName("doc").item(i);
                
                NodeList nList = docElement.getChildNodes();
                
                for (int temp = 0; temp < nList.getLength(); temp++) {
                	 
         		   Node nNode = nList.item(temp);
         		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         			   
         			  if(nNode.getNodeName().equals("bool")) {
         				 Node attribute = nNode.getAttributes().item(0);
      			    	int firstIndex = attribute.toString().indexOf("\"");
      			    	String attr = attribute.toString().substring(firstIndex);
      			    	String stripedAttr = attr.substring(1,attr.length()-1);
      				  
      			    	if(nNode.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
     			    		
     			    		String value = nNode.getChildNodes().item(0).getNodeValue();
     					   
     			    		metadata.put(stripedAttr, value);
     					}
         			  }
         			  if(nNode.getNodeName().equals("str")) {
         				   
         				 //  this.addStr(nNode);
         				   
         				  Node attribute = nNode.getAttributes().item(0);
         			    	int firstIndex = attribute.toString().indexOf("\"");
         			    	String attr = attribute.toString().substring(firstIndex);
         			    	String stripedAttr = attr.substring(1,attr.length()-1);
         			    	//System.out.print("\t"+ stripedAttr);
         				   
         			    	if(nNode.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
         			    		
         			    		
         			    		
         			    		String value = nNode.getChildNodes().item(0).getNodeValue();
         					   
         			    		if(stripedAttr.equals("id")) {
         			    			//System.out.println("ID IS: " + value);
         			    			id = value;
         			    		}
         			    		//System.out.println(" Value: " + value);
         					  
         			    		metadata.put(stripedAttr, value);
         					}
         			    	
         				   
         			   } else if(nNode.getNodeName().equals("arr")) {

         				   //this.addArr(nNode);
         				   
         			    	Node attribute = nNode.getAttributes().item(0);
         			    	int firstIndex = attribute.toString().indexOf("\"");
         			    	String attr = attribute.toString().substring(firstIndex);
         			    	String stripedAttr = attr.substring(1,attr.length()-1);
         				   
         			        String totalValue = "";
         			    	for(int k=0;k<nNode.getChildNodes().getLength();k++) {
         			    		Node item = nNode.getChildNodes().item(k);

         			    		
         			    		if(item.getNodeType() == Node.ELEMENT_NODE) {
         			    			//Node nn = nNode.getChildNodes().item(k);
         			        		
         			    			String value = item.getChildNodes().item(0).getNodeValue().toString();

         			        		totalValue += value + ";";
         			    			
         			    		}
         			    		
         			    	}
         			    	//if(k>0) {
         			    		metadata.put(stripedAttr, totalValue);
         						//System.out.println(metadata);
         			            //System.exit(0);
         					//}
         			    	
         				   
         				   
         			   
         			   }
         				   
         			   
         		   }
         		   
                }

            		
                    SubModel submodel = new SubModel();
                    submodel.setMetadata(metadata);
                    submodel.setId(id);
                    metadata = new HashMap<String,String>();
 	                id = "";
                    
 	                
 	                model.addSubmodel(submodel);
 	                
                
                
            	System.out.println("----End Doc " + i + "----");
                
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }
	
    
    public void addArr(Node nNode) {
    	
    }
	
}
