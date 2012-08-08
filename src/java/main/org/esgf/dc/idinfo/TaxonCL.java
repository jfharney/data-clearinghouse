package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * 
			<taxoncl>
				<taxonrn></taxonrn>
				<taxonrv></taxonrv>
			</taxoncl>
 */
public class TaxonCL {


	private String taxonrn;
	private String taxonrv;
	
	public TaxonCL() {
		this.taxonrn = "";
		this.taxonrv = "";
	}
	

	public String getTaxonrn() {
		return taxonrn;
	}

	public void setTaxonrn(String taxonrn) {
		this.taxonrn = taxonrn;
	}

	public String getTaxonrv() {
		return taxonrv;
	}

	public void setTaxonrv(String taxonrv) {
		this.taxonrv = taxonrv;
	}
	
	public Element toElement() {
        Element taxonclEl = new Element("taxoncl");

        Element taxonrnEl = new Element("taxonrn");
        taxonrnEl.addContent("taxonrn1");
        taxonclEl.addContent(taxonrnEl);

        Element taxonrvEl = new Element("taxonrv");
        taxonrvEl.addContent("taxonrv1");
        taxonclEl.addContent(taxonrvEl);
        
        return taxonclEl;
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
		
		TaxonCL taxoncl = new TaxonCL();
		
		taxoncl.setTaxonrn("taxonrn1");
		taxoncl.setTaxonrv("taxonrv1");
		
		taxoncl.toFile("taxoncl.xml");
		
	}
	
}
