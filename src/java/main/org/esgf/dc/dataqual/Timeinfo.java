package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <timeinfo>
						<sngdate>
							<caldate></caldate>
						</sngdate>
					</timeinfo>
 */
public class Timeinfo {

	private Sngdate sngdate;
	
	public Timeinfo() {
		this.setSngdate(new Sngdate());
	}

	public Sngdate getSngdate() {
		return sngdate;
	}

	public void setSngdate(Sngdate sngdate) {
		this.sngdate = sngdate;
	}

	public Element toElement() {
        Element timeinfoEl = new Element("timeinfo");

        
        timeinfoEl.addContent(this.sngdate.toElement());
        
        return timeinfoEl;
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
		 Timeinfo t = new Timeinfo();
		 
		 Sngdate s = new Sngdate();
		 
		 s.setCaldate("caldate1");
		 
		 t.setSngdate(s);
		 
		 t.toFile("timeinfo.xml");
		
		 
	 }
	
}
