package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class Attrac {

	private String attraccr;
	private Qattrac qattrac;
	
	public Attrac() {
		this.setAttraccr("");
		this.setQattrac(new Qattrac());
	}

	public String getAttraccr() {
		return attraccr;
	}

	public void setAttraccr(String attraccr) {
		this.attraccr = attraccr;
	}

	public Qattrac getQattrac() {
		return qattrac;
	}

	public void setQattrac(Qattrac qattrac) {
		this.qattrac = qattrac;
	}
	
	/*
	 * <attracc>
				<attraccr></attraccr>
				<qattracc>
					<attraccv></attraccv>
					<attracce></attracce>
				</qattracc>
			</attracc>
	 */
	public Element toElement() {
        Element qattracEl = new Element("attrac");

        Element attraccrEl = new Element("attraccr");
        attraccrEl.addContent(this.attraccr);
        qattracEl.addContent(attraccrEl);
        
        qattracEl.addContent(this.qattrac.toElement());
        
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
		 
		 Attrac a = new Attrac();
		 
		 
		 Qattrac q = new Qattrac();
		 
		 q.setAttracce("attracce1");
		 q.setAttraccv("attraccv1");
		 
		 a.setQattrac(q);
		 
		 a.setAttraccr("attraccr1");
		 
		 a.toFile("attrac.xml");
		 
	 }
	 
	
}
