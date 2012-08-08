package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
<attrdomv>
<edom>
	<edomv></edomv>
	<edomvd></edomvd>
	<edomvds></edomvds>
</edom>
</attrdomv>
*/
public class Attrdomv {

	private Edom edom;
	
	public Attrdomv() {
		this.setEdom(new Edom());
	}

	public Edom getEdom() {
		return edom;
	}

	public void setEdom(Edom edom) {
		this.edom = edom;
	}
	
	public Element toElement() {
        Element attrdomvEl = new Element("attrdomv");

        attrdomvEl.addContent(this.edom.toElement());
        
        
        return attrdomvEl;
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
