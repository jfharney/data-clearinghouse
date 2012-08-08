package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
		<browse>
			<browsen></browsen>
			<browsed></browsed>
			<browset></browset>
		</browse>
 */
public class Browse {

	private String browsen;
	private String browsed;
	private String browset;
	
	public Browse() {
		this.browsen = "";
		this.browsed = "";
		this.browset = "";
	}

	public String getBrowsen() {
		return browsen;
	}

	public void setBrowsen(String browsen) {
		this.browsen = browsen;
	}

	public String getBrowsed() {
		return browsed;
	}

	public void setBrowsed(String browsed) {
		this.browsed = browsed;
	}

	public String getBrowset() {
		return browset;
	}

	public void setBrowset(String browset) {
		this.browset = browset;
	}

	public Element toElement() {
        Element browseEl = new Element("browse");

        Element browsenEl = new Element("browsen");
        browsenEl.addContent(this.browsen);
        browseEl.addContent(browsenEl);

        Element browsedEl = new Element("browsed");
        browsedEl.addContent(this.browsed);
        browseEl.addContent(browsedEl);

        Element browsetEl = new Element("browset");
        browsetEl.addContent(this.browset);
        browseEl.addContent(browsetEl);
        
        return browseEl;
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
		 Browse b = new Browse();
		 
		 b.setBrowsed("browsed1");
		 b.setBrowsen("browsen1");
		 b.setBrowset("browset1");
		 
		 b.toFile("browse.xml");
	 }
	 
	 
	 
}
