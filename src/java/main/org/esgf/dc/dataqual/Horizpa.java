package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <horizpa>
				<horizpar></horizpar>
			</horizpa>
 */
public class Horizpa {


	private String horizpar;
	
	public Horizpa() {
		this.horizpar = "";
	}
	
	public Element toElement() {
        Element horizpaEl = new Element("horizpa");

        Element horizparEl = new Element("horizpar");
        horizparEl.addContent(this.horizpar);
        horizpaEl.addContent(horizparEl);
        
        
        return horizpaEl;
	 }

	public String getHorizpar() {
		return horizpar;
	}

	public void setHorizpar(String horizpar) {
		this.horizpar = horizpar;
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
		 Horizpa h = new Horizpa();
	
		 h.setHorizpar("horizpar1");
		 
		 h.toFile("horizpa.xml");
		 
	 }
	
}
