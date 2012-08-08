package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class Enttyp {

	private String enttypl;
	private String enttypd;
	private String enttypds;
	
	public Enttyp() {
		this.setEnttypl("");
		this.setEnttypd("");
		this.setEnttypds("");
	}

	public String getEnttypl() {
		return enttypl;
	}

	public void setEnttypl(String enttypl) {
		this.enttypl = enttypl;
	}

	public String getEnttypd() {
		return enttypd;
	}

	public void setEnttypd(String enttypd) {
		this.enttypd = enttypd;
	}

	public String getEnttypds() {
		return enttypds;
	}

	public void setEnttypds(String enttypds) {
		this.enttypds = enttypds;
	}
	

/*
 * 
			<enttyp>
				<enttypl></enttypl>
				<enttypd></enttypd>
				<enttypds></enttypds>
			</enttyp>
 */
	public Element toElement() {
        Element enttypEl = new Element("enttyp");

        Element enttyplEl = new Element("enttypl1");
        enttyplEl.addContent(this.enttypl);
        enttypEl.addContent(enttyplEl);

        Element enttypdEl = new Element("enttypd");
        enttypdEl.addContent(this.enttypd);
        enttypEl.addContent(enttypdEl);

        Element enttypdsEl = new Element("enttypds");
        enttypdsEl.addContent(this.enttypds);
        enttypEl.addContent(enttypdsEl);
        
        
        return enttypEl;
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
