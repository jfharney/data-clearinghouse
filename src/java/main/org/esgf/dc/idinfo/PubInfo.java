package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class PubInfo {

	private String pubPlace;

	private String publish;
	
	public PubInfo() {
		this.pubPlace = "";
		this.publish = "";
	}
	

	public String getPubPlace() {
		return pubPlace;
	}
	public void setPubPlace(String pubPlace) {
		this.pubPlace = pubPlace;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element pubinfoEl = new Element("pubinfo");
        

        Element pubplaceEl = new Element("pubplace");
        pubplaceEl.addContent(this.pubPlace);     
        
        Element publishEl = new Element("publish");
        publishEl.addContent(this.publish);
        
        pubinfoEl.addContent(pubplaceEl);
        pubinfoEl.addContent(publishEl);
        
        return pubinfoEl;
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
    	PubInfo p = new PubInfo();
    	p.setPublish("publish1");
    	p.setPubPlace("pubPlace1");
    	System.out.println(p.toXML());
    	p.toFile("pubinfo.xml");
    }
	
}
