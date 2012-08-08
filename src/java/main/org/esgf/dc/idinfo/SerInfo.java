package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class SerInfo {

	private String serName;
	private String issue;
	
	public SerInfo() {
		this.serName = "";
		this.issue = "";
	}
	
	
	public String getSerName() {
		return serName;
	}

	public void setSerName(String serName) {
		this.serName = serName;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element serinfoEl = new Element("serinfo");
        

        Element sernameEl = new Element("sername");
        sernameEl.addContent(this.serName);     
        
        Element issueEl = new Element("issue");
        issueEl.addContent(this.issue);
        /*
        <serinfo>
        	<sername></sername>
        	<issue></issue>
        </serinfo>
        */
        
        serinfoEl.addContent(sernameEl);
        serinfoEl.addContent(issueEl);
        
        return serinfoEl;
    }
    

    /** Description of toXML()
     * 
     * @return
     */
    public String toXML() {
        String xml = "";
        
        Element servicesEl = this.toElement();

        XMLOutputter outputter = new XMLOutputter();
        xml = outputter.outputString(servicesEl);
        
        return xml;
    }
    
    
    /** Description of toFile()
     * 
     * @param file Filename of the output
     */
    public void toFile(String file) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(new XmlFormatter().format(this.toXML()));
            out.close();
        } 
        catch (IOException e) { 
            e.printStackTrace();
            System.out.println("Exception ");

        }
    }
	
    public static void main(String [] args) {
    	SerInfo s = new SerInfo();
    	
    	s.setSerName("serName1");
    	s.setIssue("issue1");
    	
    	System.out.println(s.toXML());
    	s.toFile("serinfo.xml");
    }
	
}
