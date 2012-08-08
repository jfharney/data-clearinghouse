package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
			<keywtax>
				<taxonkt></taxonkt>
				<taxonkey></taxonkey>
			</keywtax>
 */
public class Keywtax {

	private String taxonkt;
	private String taxonkey;
	
	public Keywtax() {
		this.taxonkt = "";
		this.taxonkey = "";
	}

	public String getTaxonkt() {
		return taxonkt;
	}

	public void setTaxonkt(String taxonkt) {
		this.taxonkt = taxonkt;
	}

	public String getTaxonkey() {
		return taxonkey;
	}

	public void setTaxonkey(String taxonkey) {
		this.taxonkey = taxonkey;
	}
	
	public Element toElement() {
        Element keywtaxEl = new Element("keywtax");

        Element taxonktEl = new Element("taxonkt");
        taxonktEl.addContent("taxonkt1");
        keywtaxEl.addContent(taxonktEl);

        Element taxonkeyEl = new Element("taxonkey");
        taxonkeyEl.addContent("taxonkey1");
        keywtaxEl.addContent(taxonkeyEl);
        
        
        return keywtaxEl;
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
		
		Keywtax keywtax = new Keywtax();
		
		keywtax.setTaxonkey("taxonkey1");
		keywtax.setTaxonkt("taxonkt1");
		
		keywtax.toFile("keywtax.xml");
		
	}
	
}
