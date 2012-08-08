package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <networka>
								<networkr></networkr>
							</networka>
 */
public class Networka {

	private String networkr;
	
	public Networka() {
		this.setNetworkr("");
	}

	public String getNetworkr() {
		return networkr;
	}

	public void setNetworkr(String networkr) {
		this.networkr = networkr;
	}
	
	public Element toElement() {
        Element networkaEl = new Element("networka");

        Element networkrEl = new Element("networkr");
        networkrEl.addContent(this.networkr);
        networkaEl.addContent(networkrEl);
        
        
        return networkaEl;
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
