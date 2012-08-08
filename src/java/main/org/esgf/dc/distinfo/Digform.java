package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <digform>
				<digtinfo>
					<formname></formname>
				</digtinfo>
				<digtopt>
					<onlinopt>
						<computer>
							<networka>
								<networkr></networkr>
							</networka>
						</computer>
					</onlinopt>
				</digtopt>
			</digform>
 */
public class Digform {

	private Digtinfo digtinfo;
	private Digtopt digtopt;
	
	public Digform() {
		this.setDigtinfo(new Digtinfo());
		this.setDigtopt(new Digtopt());
	}

	public Digtinfo getDigtinfo() {
		return digtinfo;
	}

	public void setDigtinfo(Digtinfo digtinfo) {
		this.digtinfo = digtinfo;
	}

	public Digtopt getDigtopt() {
		return digtopt;
	}

	public void setDigtopt(Digtopt digtopt) {
		this.digtopt = digtopt;
	}
	
	
	public Element toElement() {
        Element digformEl = new Element("digform");

        digformEl.addContent(this.digtinfo.toElement());
        digformEl.addContent(this.digtopt.toElement());
        
        return digformEl;
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
