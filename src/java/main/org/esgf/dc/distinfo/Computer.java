package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <computer>
							<networka>
								<networkr></networkr>
							</networka>
						</computer>
 */
public class Computer {

	private Networka networka;
	
	public Computer() {
		this.setNetworka(new Networka());
	}

	public Networka getNetworka() {
		return networka;
	}

	public void setNetworka(Networka networka) {
		this.networka = networka;
	}
	
	public Element toElement() {
        Element computerEl = new Element("computer");

        computerEl.addContent(this.networka.toElement());
        
        return computerEl;
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
