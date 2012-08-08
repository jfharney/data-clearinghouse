package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
<descript>
<abstract></abstract>
<purpose></purpose>
<supplinf></supplinf>
</descript>
*/
public class Descript {

	private String abstractD;
	private String purpose;
	private String supplinf;
	
	
	public String getAbstractD() {
		return abstractD;
	}
	public void setAbstractD(String abstractD) {
		this.abstractD = abstractD;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getSupplinf() {
		return supplinf;
	}
	public void setSupplinf(String supplinf) {
		this.supplinf = supplinf;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element descriptEl = new Element("descript");
        
        Element abstractEl = new Element("abstract");
        abstractEl.addContent(this.abstractD);
        descriptEl.addContent(abstractEl);

        Element purposeEl = new Element("purpose");
        purposeEl.addContent(this.purpose);
        descriptEl.addContent(purposeEl);
        
        Element supplinfEl = new Element("supplinf");
        supplinfEl.addContent(this.supplinf);
        descriptEl.addContent(supplinfEl);
        
        
        return descriptEl;
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
		 Descript d = new Descript();
		 
		 d.setAbstractD("abstractD1");
		 d.setPurpose("purpose1");
		 d.setSupplinf("supplinf1");
		 
		 d.toFile("Descript.xml");
		 
		 
	 }
	
}
