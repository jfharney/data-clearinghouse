package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * 
			<geograph>
				<latres></latres>
				<longres></longres>
				<geogunit></geogunit>
			</geograph>
 */
public class Geograph {

	private String latres;
	private String longres;
	private String geogunit;
	
	public Geograph() {
		this.setLatres("");
		this.setLongres("");
		this.setGeogunit("");
	}

	public String getLatres() {
		return latres;
	}

	public void setLatres(String latres) {
		this.latres = latres;
	}

	public String getLongres() {
		return longres;
	}

	public void setLongres(String longres) {
		this.longres = longres;
	}

	public String getGeogunit() {
		return geogunit;
	}

	public void setGeogunit(String geogunit) {
		this.geogunit = geogunit;
	}
	
	public Element toElement() {
        Element geographEl = new Element("geograph");

        Element latresEl = new Element("latres");
        latresEl.addContent(this.latres);
        geographEl.addContent(latresEl);

        Element longresEl = new Element("longres");
        longresEl.addContent(this.longres);
        geographEl.addContent(longresEl);

        Element geogunitEl = new Element("geogunit");
        geogunitEl.addContent(this.geogunit);
        geographEl.addContent(geogunitEl);
        
        
        return geographEl;
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
