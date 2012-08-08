package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <posacc>
			<horizpa>
				<horizpar></horizpar>
			</horizpa>
		</posacc>
 */
public class Posacc {

	private Horizpa horizpa;
	
	public Posacc() {
		this.setHorizpa(new Horizpa());
	}

	public Horizpa getHorizpa() {
		return horizpa;
	}

	public void setHorizpa(Horizpa horizpa) {
		this.horizpa = horizpa;
	}
	
	public Element toElement() {
        Element posaccEl = new Element("posaccc");

        
        posaccEl.addContent(this.horizpa.toElement());
        
        return posaccEl;
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
		 Posacc p = new Posacc();
		 
		 Horizpa h = new Horizpa();
			
		 h.setHorizpar("horizpar1");
		 
		 p.setHorizpa(h);
		 
		 p.toFile("posacc.xml");
	 }
	
}
