package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class RngDates {

	private String begDate;
	private String endDate;
	
	public RngDates() {
		this.begDate = "";
		this.endDate = "";
	}

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element rngdatesEl = new Element("rngdates");

        Element begdateEl = new Element("begdate");
        begdateEl.addContent(this.begDate);
        rngdatesEl.addContent(begdateEl);

        Element enddateEl = new Element("enddate");
        enddateEl.addContent(this.endDate);
        rngdatesEl.addContent(enddateEl);
        
        return rngdatesEl;
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
		 RngDates r = new RngDates();

		 r.setBegDate("begDate1");
		 r.setEndDate("endDate1");
		 
		 r.toFile("RngDates.xml");
	 }
	
}
