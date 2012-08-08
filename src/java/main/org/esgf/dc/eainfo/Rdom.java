package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * 
				<rdom>
					<rdommin></rdommin>
					<rdommax></rdommax>
				</rdom>
 */
public class Rdom {

	private String rdommin;
	private String rdommax;
	
	public Rdom() {
		this.setRdommin("");
		this.setRdommax("");
	}

	public String getRdommin() {
		return rdommin;
	}

	public void setRdommin(String rdommin) {
		this.rdommin = rdommin;
	}

	public String getRdommax() {
		return rdommax;
	}

	public void setRdommax(String rdommax) {
		this.rdommax = rdommax;
	}
	
	public Element toElement() {
        Element rdomEl = new Element("rdom");

        Element rdomminEl = new Element("rdommin");
        rdomminEl.addContent(this.rdommin);
        rdomEl.addContent(rdomminEl);

        Element rdommaxEl = new Element("rdommax");
        rdommaxEl.addContent(this.rdommax);
        rdomEl.addContent(rdommaxEl);
        
        
        return rdomEl;
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
