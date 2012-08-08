package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <onlinopt>
						<computer>
							<networka>
								<networkr></networkr>
							</networka>
						</computer>
					</onlinopt>
 */
public class Onlinopt {

	private Computer computer;
	
	public Onlinopt() {
		this.setComputer(new Computer());
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
	public Element toElement() {
        Element onlinoptEl = new Element("onlinopt");

        onlinoptEl.addContent(this.computer.toElement());
        
        return onlinoptEl;
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
