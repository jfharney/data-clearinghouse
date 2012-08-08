package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class SecInfo {


	private String secsys;
	private String secclass;
	private String sechandl;
	
	public SecInfo() {
		this.secsys = "";
		this.secclass = "";
		this.sechandl = "";
	}

	public String getSecsys() {
		return secsys;
	}

	public void setSecsys(String secsys) {
		this.secsys = secsys;
	}

	public String getSecclass() {
		return secclass;
	}

	public void setSecclass(String secclass) {
		this.secclass = secclass;
	}

	public String getSechandl() {
		return sechandl;
	}

	public void setSechandl(String sechandl) {
		this.sechandl = sechandl;
	}
	

/*
 * 
		<secinfo>
			<secsys></secsys>
			<secclass></secclass>
			<sechandl></sechandl>
		</secinfo>
 */
	public Element toElement() {
        Element secinfoEl = new Element("secinfo");

        Element secsysEl = new Element("secsys");
        secsysEl.addContent(this.secsys);
        secinfoEl.addContent(secsysEl);
        
        Element secclassEl = new Element("secclass");
        secclassEl.addContent(this.secclass);
        secinfoEl.addContent(secclassEl);

        Element sechandlEl = new Element("sechandl");
        sechandlEl.addContent(this.sechandl);
        secinfoEl.addContent(sechandlEl);
        
        return secinfoEl;
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
		 
		 
		 SecInfo s = new SecInfo();
		 
		 s.setSecclass("secclass1");
		 s.setSechandl("sechandl1");
		 s.setSecsys("secsys1");
		 
		 s.toFile("secinfo.xml");
		 
		 
	 }
	 
}
