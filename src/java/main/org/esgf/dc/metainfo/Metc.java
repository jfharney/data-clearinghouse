package org.esgf.dc.metainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.CntInfo;
import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Metc {

	private CntInfo cntinfo;
	
	public Metc() {
		this.setCntinfo(new CntInfo());
	}

	public CntInfo getCntinfo() {
		return cntinfo;
	}

	public void setCntinfo(CntInfo cntinfo) {
		this.cntinfo = cntinfo;
	}
	
	public Element toElement() {
        Element metcEl = new Element("metc");

        metcEl.addContent(this.cntinfo.toElement());
        
        return metcEl;
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
