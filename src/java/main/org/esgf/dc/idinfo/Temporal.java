package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
*/
public class Temporal {

	private String temporalkt;
	private List<String> temporalkey;
	
	public String getTemporalkt() {
		return temporalkt;
	}

	public void setTemporalkt(String temporalkt) {
		this.temporalkt = temporalkt;
	}

	public List<String> getTemporalkey() {
		return temporalkey;
	}

	public void setTemporalkey(List<String> temporalkey) {
		this.temporalkey = temporalkey;
	}

	
	public Temporal() {
		this.temporalkey = new ArrayList<String>();
		this.temporalkey.add("");
		this.temporalkt = "";
	}
	

	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element temporalEl = new Element("temporal");
        
        Element temporalktEl = new Element("temporalkt");
        temporalktEl.addContent(this.temporalkt);
        temporalEl.addContent(temporalktEl);
        
        for(int i=0;i<this.temporalkey.size();i++) {
            Element temporalkeyEl = new Element("temporalkey");
            temporalkeyEl.addContent(this.temporalkey.get(i));
            temporalEl.addContent(temporalkeyEl);
        }
        return temporalEl;
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
		Temporal temporal = new Temporal();
		
		String temporalkt = "temporalkt1";
		
		List<String> temporalkey = new ArrayList<String>();
		temporalkey.add("temporalkey1");
		temporalkey.add("temporalkey2");
		
		temporal.setTemporalkt(temporalkt);
		temporal.setTemporalkey(temporalkey);
		
		temporal.toFile("temporal.xml");
		
	}
	
	
}
