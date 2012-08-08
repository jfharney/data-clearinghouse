package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
		<timeperd>
			<timeinfo>
				<rngdates>
					<begdate></begdate>
					<enddate></enddate>
				</rngdates>
			</timeinfo>
			<current></current>
		</timeperd>
 */
public class TimePerd {

	

	private TimeInfo timeinfo;
	private String current;
	
	public TimePerd() {
		this.timeinfo = new TimeInfo();
		this.current = "";
	}
	
	public TimeInfo getTimeinfo() {
		return timeinfo;
	}

	public void setTimeinfo(TimeInfo timeinfo) {
		this.timeinfo = timeinfo;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}
	
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element timeperdEl = new Element("timeperd");

        timeperdEl.addContent(this.timeinfo.toElement());
        
        Element currentEl = new Element("current");
        currentEl.addContent(this.current);
        
        timeperdEl.addContent(currentEl);
        
        return timeperdEl;
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
		TimePerd tp = new TimePerd();
		 
		TimeInfo t = new TimeInfo();

		RngDates r = new RngDates();

		r.setBegDate("begDate1");
		r.setEndDate("endDate1");
		
		t.setRngDates(r);
		
		tp.setTimeinfo(t);
		tp.setCurrent("current1");
		
		tp.toFile("TimePerd.xml");
		 
	 }
    
	
}
