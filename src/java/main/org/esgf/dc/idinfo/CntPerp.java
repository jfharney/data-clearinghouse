package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
				<cntperp>
					<cntper></cntper>
					<cntorg></cntorg>
				</cntperp>
 */
public class CntPerp {

	

	private String cntPer;
	private String cntOrg;
	
	public CntPerp() {
		this.cntPer = "";
		this.cntOrg = "";
	}
	
	public String getCntPer() {
		return cntPer;
	}

	public void setCntPer(String cntPer) {
		this.cntPer = cntPer;
	}

	public String getCntOrg() {
		return cntOrg;
	}

	public void setCntOrg(String cntOrg) {
		this.cntOrg = cntOrg;
	}
	

	public Element toElement() {
        Element cntperpEl = new Element("cntperp");

        Element cntperEl = new Element("cntper");
        cntperEl.addContent(this.cntPer);
        cntperpEl.addContent(cntperEl);

        Element cntorgEl = new Element("cntorg");
        cntorgEl.addContent(this.cntOrg);
        cntperpEl.addContent(cntorgEl);
        
        return cntperpEl;
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
		 CntPerp c = new CntPerp();
		 
		 c.setCntOrg("cntOrg1");
		 c.setCntPer("cntPer1");
		 
		 c.toFile("cntperp.xml");
		 
	 }
	 
}
