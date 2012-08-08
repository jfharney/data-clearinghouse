package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <geodetic>
				<horizdn></horizdn>
				<ellips></ellips>
				<semiaxis></semiaxis>
				<denflat></denflat>
			</geodetic>
 */
public class Geodetic {

	private String horizdn;
	private String ellips;
	private String semiaxis;
	private String denflat;
	
	public Geodetic() {
		this.setHorizdn("");
		this.setEllips("");
		this.setSemiaxis("");
		this.setDenflat("");
	}

	public String getHorizdn() {
		return horizdn;
	}

	public void setHorizdn(String horizdn) {
		this.horizdn = horizdn;
	}

	public String getEllips() {
		return ellips;
	}

	public void setEllips(String ellips) {
		this.ellips = ellips;
	}

	public String getSemiaxis() {
		return semiaxis;
	}

	public void setSemiaxis(String semiaxis) {
		this.semiaxis = semiaxis;
	}

	public String getDenflat() {
		return denflat;
	}

	public void setDenflat(String denflat) {
		this.denflat = denflat;
	}
	
	public Element toElement() {
        Element geodeticEl = new Element("geodetic");

        Element horizdnEl = new Element("horizdn");
        horizdnEl.addContent(this.horizdn);
        geodeticEl.addContent(horizdnEl);

        Element ellipsEl = new Element("ellips");
        ellipsEl.addContent(this.ellips);
        geodeticEl.addContent(ellipsEl);

        Element semiaxisEl = new Element("semiaxis");
        semiaxisEl.addContent(this.semiaxis);
        geodeticEl.addContent(semiaxisEl);

        Element denflatEl = new Element("denflat");
        denflatEl.addContent(this.denflat);
        geodeticEl.addContent(denflatEl);
        
        
        return geodeticEl;
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
