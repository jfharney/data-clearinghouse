package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <localp>
					<localpd></localpd>
					<localpgi></localpgi>
				</localp>
 */
public class Localp {

	private String localpd;
	private String localpgi;
	
	public Localp() {
		this.setLocalpd("");
		this.setLocalpgi("");
	}

	public String getLocalpd() {
		return localpd;
	}

	public void setLocalpd(String localpd) {
		this.localpd = localpd;
	}

	public String getLocalpgi() {
		return localpgi;
	}

	public void setLocalpgi(String localpgi) {
		this.localpgi = localpgi;
	}
	
	public Element toElement() {
        Element localpEl = new Element("localp");

        Element localpdEl = new Element("localpd");
        localpdEl.addContent(this.localpd);
        localpEl.addContent(localpdEl);

        Element localpgiEl = new Element("localpgi");
        localpgiEl.addContent(this.localpgi);
        localpEl.addContent(localpgiEl);
        
        
        return localpEl;
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
