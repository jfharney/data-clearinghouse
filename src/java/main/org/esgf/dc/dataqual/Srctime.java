package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;



public class Srctime {

	private Timeinfo timeinfo;
	private String srccurr;
	
	public Srctime() {
		this.setTimeinfo(new Timeinfo());
		this.setSrccurr("");
	}

	public Timeinfo getTimeinfo() {
		return timeinfo;
	}

	public void setTimeinfo(Timeinfo timeinfo) {
		this.timeinfo = timeinfo;
	}

	public String getSrccurr() {
		return srccurr;
	}

	public void setSrccurr(String srccurr) {
		this.srccurr = srccurr;
	}
	
	/*
	 * <srctime>
						<timeinfo>
							<sngdate>
								<caldate></caldate>
							</sngdate>
						</timeinfo>
						<srccurr></srccurr>
					</srctime>
	 */
	public Element toElement() {
        Element srctimeEl = new Element("srctime");

        
        srctimeEl.addContent(this.timeinfo.toElement());
        
        Element srccurrEl = new Element("srccurr");
        srccurrEl.addContent(this.srccurr);
        srctimeEl.addContent(srccurrEl);
        
        return srctimeEl;
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
		 
		 Srctime src = new Srctime();
		 
Timeinfo t = new Timeinfo();
		 
		 Sngdate s = new Sngdate();
		 
		 s.setCaldate("caldate1");
		 
		 t.setSngdate(s);
		 
		 src.setTimeinfo(t);
		 src.setSrccurr("srccurr1");
		 
		 src.toFile("srctime.xml");
		 
	 }
	
	
}
