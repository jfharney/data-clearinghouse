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
import org.esgf.dc.Dataset;
import org.esgf.dc.Model;
import org.esgf.dc.SubModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class SolrRecordReader {

	
	private String datasetId;
	
	
	public static String host = "http://esg-datanode.jpl.nasa.gov";
	
	private static String searchAPIURL;// = host + "/esg-search/search?query=*&limit=1&";

	
	public SolrRecordReader() {
		this.setDatasetName("");
	}
	
	public SolrRecordReader(String datasetName) {
		this.setDatasetName(datasetName);
	}
	
	
	
	public Dataset assembleDataset() {
		String responseBody = null;

        // create an http client
        HttpClient client = new HttpClient();
        
        searchAPIURL = host + "/esg-search/search?query=*&limit=1&" + "id=" + this.datasetId;
        
        System.out.println("searchapi url: " + searchAPIURL);
        
      //attact the dataset id to the query string
        GetMethod method = new GetMethod(searchAPIURL);
        
        
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        
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
        

        Dataset dataset = this.fromXML(responseBody);
        
        
		return dataset;
		
	}
	
	
	/** Description 
     * 
     * @param xmlStr
     */
    public Dataset fromXML(String xmlStr) {

    	Dataset dataset = new Dataset();
    	
    	try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlStr));

            
            Document doc = db.parse(is);
            
            doc.getDocumentElement().normalize();
            
            org.w3c.dom.Element fileElement = doc.getDocumentElement();
            
            
            Map<String,String> metadata = new HashMap<String,String>();
            String id = "";
            
            int docLength = fileElement.getElementsByTagName("doc").getLength();
            
            //iterate over the doc element (should only be one)
            if(docLength > 1) {
            	System.out.println("Multiple docs not supported yet");
            } else if (docLength < 1) {
            	System.out.println("No docs found in the query");
            } else {
            	Node docElement = fileElement.getElementsByTagName("doc").item(0);

                NodeList nList = docElement.getChildNodes();
                
                System.out.println(nList.getLength());
                
                for(int i=0;i<nList.getLength();i++) {
                	Node nNode = nList.item(i);
                	//only interested in "Element" node types
                	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                		
                		//for the bool attributes
                		if(nNode.getNodeName().equals("bool")) {
                			
                			//grab the name of the "key" from the attribute of the bool tag
                			Node attribute = nNode.getAttributes().item(0);
                			
                			//grab the key (usually only latest or replica)
                			int firstIndex = attribute.toString().indexOf("\"");
                			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
                			
                			//get the value of the key from the text of the child node
                			if(nNode.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
          			    		
          			    		String value = nNode.getChildNodes().item(0).getNodeValue();
          					   
          			    		metadata.put(attr, value);
          					}
                		} 
                		//for the str attributes
                		else if (nNode.getNodeName().equals("str")) {
                			//grab the name of the "key" from the attribute of the str tag
                			Node attribute = nNode.getAttributes().item(0);
                			
                			//grab the key (usually only latest or replica)
                			int firstIndex = attribute.toString().indexOf("\"");
                			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
                			
                			//get the value of the key from the text of the child node
                			if(nNode.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
          			    		
          			    		String value = nNode.getChildNodes().item(0).getNodeValue();
          					   
          			    		//special attr id 
          			    		if(attr.equals("id")) {
          			    			dataset.setId(value);
          			    		}
          			    		
          			    		metadata.put(attr, value);
          					}
                		}
                		//for the arr attributes
                		else if(nNode.getNodeName().equals("arr")) {
                			//grab the name of the "key" from the attribute of the bool tag
                			Node attribute = nNode.getAttributes().item(0);
                			
                			//grab the key (usually only latest or replica)
                			int firstIndex = attribute.toString().indexOf("\"");
                			String attr = attribute.toString().substring(firstIndex+1,attribute.toString().length()-1);
                			
                			//get the value of the key as a concatenation of the child nodes delimited by a ";"
                			String totalValue = "";
          			    	for(int k=0;k<nNode.getChildNodes().getLength();k++) {
          			    		Node item = nNode.getChildNodes().item(k);

          			    		
          			    		if(item.getNodeType() == Node.ELEMENT_NODE) {
          			    			String value = item.getChildNodes().item(0).getNodeValue().toString();
          			        		totalValue += value + ";";
          			    		}
          			    		
          			    	}
      			    		metadata.put(attr, totalValue);
          			    	
                		
                		}//end else if arr
                	}//end if element node
                }//end traveral over nodes in doc
            }//end doclength == 0
            
            dataset.setMetadata(metadata);
            
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return dataset;
    }

	public String getDatasetName() {
		return datasetId;
	}

	public void setDatasetName(String datasetName) {
		this.datasetId = datasetName;
	}
	
    
	public static void main(String [] args) {
		/*
		
		String datasetId = "cmip5.output1.CMCC.CMCC-CM.decadal2005.mon.atmos.Amon.r1i1p1.v20120604%7Cadm07.cmcc.it";
		SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
		
		Dataset dataset = solrRecordReader.assembleDataset();
		
		System.out.println(dataset.getId());
		*/
	}
	
}
