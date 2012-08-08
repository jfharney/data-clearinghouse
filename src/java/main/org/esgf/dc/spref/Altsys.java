package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <altsys>
				<altenc></altenc>
				<altdatum></altdatum>
				<altunits></altunits>
				<altres></altres>
			</altsys>
 */
public class Altsys {

	private String altenc;
	private String altdatum;
	private String altunits;
	private String altres;
	
	public Altsys() {
		this.setAltenc("");
		this.setAltdatum("");
		this.setAltunits("");
		this.setAltres("");
	}

	public String getAltenc() {
		return altenc;
	}

	public void setAltenc(String altenc) {
		this.altenc = altenc;
	}

	public String getAltdatum() {
		return altdatum;
	}

	public void setAltdatum(String altdatum) {
		this.altdatum = altdatum;
	}

	public String getAltunits() {
		return altunits;
	}

	public void setAltunits(String altunits) {
		this.altunits = altunits;
	}

	public String getAltres() {
		return altres;
	}

	public void setAltres(String altres) {
		this.altres = altres;
	}
	
	
	public Element toElement() {
        Element altsysEl = new Element("altsys");

        Element altencEl = new Element("altenc");
        altencEl.addContent(this.altenc);
        altsysEl.addContent(altencEl);

        Element altdatumEl = new Element("altdatum");
        altdatumEl.addContent(this.altdatum);
        altsysEl.addContent(altdatumEl);

        Element altunitsEl = new Element("altunits");
        altunitsEl.addContent(this.altunits);
        altsysEl.addContent(altunitsEl);

        Element altresEl = new Element("altres");
        altresEl.addContent(this.altres);
        altsysEl.addContent(altresEl);
        
        
        return altsysEl;
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
