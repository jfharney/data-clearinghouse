package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;



public class Qattrac {

	

	private String attraccv;
	private String attracce;
	
	public Qattrac() {
		this.attraccv = "";
		this.attracce = "";
	}
	
	public String getAttraccv() {
		return attraccv;
	}

	public void setAttraccv(String attraccv) {
		this.attraccv = attraccv;
	}

	public String getAttracce() {
		return attracce;
	}

	public void setAttracce(String attracce) {
		this.attracce = attracce;
	}
	
	/*
	 * <qattracc>
					<attraccv></attraccv>
					<attracce></attracce>
				</qattracc>
	 */
	public Element toElement() {
        Element qattracEl = new Element("qattrac");

        Element attraccvEl = new Element("attraccv");
        attraccvEl.addContent(this.attraccv);
        qattracEl.addContent(attraccvEl);
        
        Element attracceEl = new Element("attracce");
        attracceEl.addContent(this.attracce);
        qattracEl.addContent(attracceEl);
        
        
        return qattracEl;
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
		 
		 Qattrac q = new Qattrac();
		 
		 q.setAttracce("attracce1");
		 q.setAttraccv("attraccv1");
		 
		 q.toFile("qattrac.xml");
		 
	 }
	 
	
}
