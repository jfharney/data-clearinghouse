package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <sngdate>
							<caldate></caldate>
						</sngdate>
 */
public class Sngdate {

	private String caldate;
	
	public Sngdate() {
		this.setCaldate("");
	}

	public String getCaldate() {
		return caldate;
	}

	public void setCaldate(String caldate) {
		this.caldate = caldate;
	}
	
	public Element toElement() {
        Element sngdateEl = new Element("sngdate");

        Element caldateEl = new Element("caldate");
        caldateEl.addContent(this.caldate);
        sngdateEl.addContent(caldateEl);
        //posaccEl.addContent(this.horizpa.toElement());
        
        return sngdateEl;
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
		 Sngdate s = new Sngdate();
		 
		 s.setCaldate("caldate1");
		 
		 s.toFile("caldate.xml");
	 }
	
}
