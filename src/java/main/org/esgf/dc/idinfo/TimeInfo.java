package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class TimeInfo {

	private RngDates rngDates;
	
	public TimeInfo() {
		this.setRngDates(new RngDates());
	}

	public RngDates getRngDates() {
		return rngDates;
	}

	public void setRngDates(RngDates rngDates) {
		this.rngDates = rngDates;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element timeinfoEl = new Element("timeinfo");

        timeinfoEl.addContent(this.rngDates.toElement());
        
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
		TimeInfo t = new TimeInfo();


		 RngDates r = new RngDates();

		 r.setBegDate("begDate1");
		 r.setEndDate("endDate1");
		
		t.setRngDates(r);
		 
		 t.toFile("TimeInfo.xml");
	}
	
}
