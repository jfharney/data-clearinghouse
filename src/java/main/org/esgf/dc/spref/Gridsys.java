package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <gridsys>
					<gridsysn></gridsysn>
				</gridsys>
 */
public class Gridsys {

	private String gridsysn;
	
	public Gridsys() {
		this.setGridsysn("");
	}

	public String getGridsysn() {
		return gridsysn;
	}

	public void setGridsysn(String gridsysn) {
		this.gridsysn = gridsysn;
	}
	
	public Element toElement() {
        Element gridsysEl = new Element("gridsys");

        Element gridsysnEl = new Element("gridsysn");
        gridsysnEl.addContent(this.gridsysn);
        gridsysEl.addContent(gridsysnEl);
        
        
        return gridsysEl;
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
