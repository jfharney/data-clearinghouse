package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <digtopt>
					<onlinopt>
						<computer>
							<networka>
								<networkr></networkr>
							</networka>
						</computer>
					</onlinopt>
				</digtopt>
 */
public class Digtopt {

	private Onlinopt onlinopt;
	
	public Digtopt() {
		this.setOnlinopt(new Onlinopt());
	}

	public Onlinopt getOnlinopt() {
		return onlinopt;
	}

	public void setOnlinopt(Onlinopt onlinopt) {
		this.onlinopt = onlinopt;
	}
	
	public Element toElement() {
        Element digtoptEl = new Element("digtopt");

        
        digtoptEl.addContent(this.onlinopt.toElement());
        
        return digtoptEl;
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
