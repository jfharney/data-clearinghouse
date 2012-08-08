package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Edom {

	private String edomv;
	private String edomvd;
	private String edomvds;
	
	public Edom() {
		this.setEdomv("");
		this.setEdomvd("");
		this.setEdomvds("");
	}

	public String getEdomv() {
		return edomv;
	}

	public void setEdomv(String edomv) {
		this.edomv = edomv;
	}

	public String getEdomvd() {
		return edomvd;
	}

	public void setEdomvd(String edomvd) {
		this.edomvd = edomvd;
	}

	public String getEdomvds() {
		return edomvds;
	}

	public void setEdomvds(String edomvds) {
		this.edomvds = edomvds;
	}
	

/*
<edom>
<edomv></edomv>
<edomvd></edomvd>
<edomvds></edomvds>
</edom>
*/
	public Element toElement() {
        Element edomEl = new Element("edom");

        Element edomvEl = new Element("edomv");
        edomvEl.addContent(this.edomv);
        edomEl.addContent(edomvEl);

        Element edomvdEl = new Element("edomvd");
        edomvdEl.addContent(this.edomvd);
        edomEl.addContent(edomvdEl);

        Element edomvdsEl = new Element("edomvds");
        edomvdsEl.addContent(this.edomvds);
        edomEl.addContent(edomvdsEl);
        
        
        return edomEl;
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
