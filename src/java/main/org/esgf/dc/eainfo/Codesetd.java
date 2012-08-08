package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
				<codesetd>
					<codesetn></codesetn>
					<codesets></codesets>
				</codesetd>
 */
public class Codesetd {

	private String codesetn;
	private String codesets;
	
	public Codesetd() {
		this.setCodesetn("");
		this.setCodesets("");
	}

	public String getCodesetn() {
		return codesetn;
	}

	public void setCodesetn(String codesetn) {
		this.codesetn = codesetn;
	}

	public String getCodesets() {
		return codesets;
	}

	public void setCodesets(String codesets) {
		this.codesets = codesets;
	}
	
	public Element toElement() {
        Element codesetdEl = new Element("codesetd");

        Element codesetnEl = new Element("codesetn");
        codesetnEl.addContent(this.codesetn);
        codesetdEl.addContent(codesetnEl);

        Element codesetsEl = new Element("codesets");
        codesetsEl.addContent(this.codesets);
        codesetdEl.addContent(codesetsEl);
        
        
        return codesetdEl;
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
