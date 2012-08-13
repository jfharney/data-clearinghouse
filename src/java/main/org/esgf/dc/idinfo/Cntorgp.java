package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Cntorgp {

	private String cntorg;
	private String cntper;
	
	
	public Cntorgp() {
		this.setCntorg("");
		this.setCntper("");
	}


	public String getCntorg() {
		return cntorg;
	}


	public void setCntorg(String cntorg) {
		this.cntorg = cntorg;
	}


	public String getCntper() {
		return cntper;
	}


	public void setCntper(String cntper) {
		this.cntper = cntper;
	}
	
	
	public Element toElement() {
        Element cntorgpEl = new Element("cntorgp");

        
        Element cntOrgEl = new Element("cntorg");
        cntOrgEl.addContent(this.cntorg);
        cntorgpEl.addContent(cntOrgEl);

        Element cntPerEl = new Element("cntper");
        cntPerEl.addContent(this.cntper);
        cntorgpEl.addContent(cntPerEl);

        
        return cntorgpEl;
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
	
}
