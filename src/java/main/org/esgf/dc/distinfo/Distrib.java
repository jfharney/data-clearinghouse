package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.CntInfo;
import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Distrib {

	private String transfer_size;
	private CntInfo cntinfo;

	public Distrib() {
		this.setTransfer_size("");
		this.cntinfo = new CntInfo();
	}
	
	
	public CntInfo getCntinfo() {
		return cntinfo;
	}

	public void setCntinfo(CntInfo cntinfo) {
		this.cntinfo = cntinfo;
	}
	
	
	public Element toElement() {
        Element distribEl = new Element("distrib");

        Element transfer_sizeEl = new Element("transfer_size");
        transfer_sizeEl.addContent(this.transfer_size);
        distribEl.addContent(transfer_sizeEl);
        
        distribEl.addContent(this.cntinfo.toElement());
        
        return distribEl;
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


	public String getTransfer_size() {
		return transfer_size;
	}


	public void setTransfer_size(String transfer_size) {
		this.transfer_size = transfer_size;
	}
	
}
