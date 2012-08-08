package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
		<overview>
			<eaover></eaover>
			<eadetcit></eadetcit>
		</overview>
 */
public class Overview {

	private String eaover;
	private String eadetcit;
	
	public Overview() {
		this.setEaover("");
		this.setEadetcit("");
	}

	public String getEaover() {
		return eaover;
	}

	public void setEaover(String eaover) {
		this.eaover = eaover;
	}

	public String getEadetcit() {
		return eadetcit;
	}

	public void setEadetcit(String eadetcit) {
		this.eadetcit = eadetcit;
	}
	
	public Element toElement() {
        Element overviewEl = new Element("overview");

        Element eaoverEl = new Element("eaover");
        eaoverEl.addContent(this.eaover);
        overviewEl.addContent(eaoverEl);

        Element eadetcitEl = new Element("eadetcit");
        eadetcitEl.addContent(this.eadetcit);
        overviewEl.addContent(eadetcitEl);
        
        return overviewEl;
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
