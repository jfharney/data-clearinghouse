package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <bounding>
				<westbc></westbc>
				<eastbc></eastbc>
				<northbc></northbc>
				<southbc></southbc>
			</bounding>
 */
public class Bounding {

	private String westbc;
	private String eastbc;
	private String northbc;
	private String southbc;
	

	public String getWestbc() {
		return westbc;
	}
	public void setWestbc(String westbc) {
		this.westbc = westbc;
	}
	public String getEastbc() {
		return eastbc;
	}
	public void setEastbc(String eastbc) {
		this.eastbc = eastbc;
	}
	public String getNorthbc() {
		return northbc;
	}
	public void setNorthbc(String northbc) {
		this.northbc = northbc;
	}
	public String getSouthbc() {
		return southbc;
	}
	public void setSouthbc(String southbc) {
		this.southbc = southbc;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element boundingEl = new Element("bounding");
        
        Element westbcEl = new Element("westbc");
        westbcEl.addContent(this.westbc);     
        boundingEl.addContent(westbcEl);
        
        Element eastbcEl = new Element("eastbc");
        eastbcEl.addContent(this.eastbc);
        boundingEl.addContent(eastbcEl);
        
        Element southbcEl = new Element("southbc");
        southbcEl.addContent(this.southbc);     
        boundingEl.addContent(southbcEl);
        
        Element northbcEl = new Element("northbc");
        northbcEl.addContent(this.northbc);
        boundingEl.addContent(northbcEl);
        
        return boundingEl;
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
    	Bounding b = new Bounding();
    	
    	b.setEastbc("eastbc1");
    	b.setNorthbc("northbc1");
    	b.setSouthbc("southbc1");
    	b.setWestbc("westbc1");
    	
    	b.toFile("bounding.xml");
    }
    
	
}
