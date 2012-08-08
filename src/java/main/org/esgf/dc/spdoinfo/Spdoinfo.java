package org.esgf.dc.spdoinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <spdoinfo>
		<indspref></indspref>
		<direct></direct>
	</spdoinfo>
 */
public class Spdoinfo {

	private String indspref;
	private String direct;
	
	public Spdoinfo() {
		this.setIndspref("");
		this.setDirect("");
	}

	public String getIndspref() {
		return indspref;
	}

	public void setIndspref(String indspref) {
		this.indspref = indspref;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element spdoinfoEl = new Element("spdoinfo");
        
        Element indsprefEl = new Element("indspref");
        indsprefEl.addContent(this.indspref);     
        spdoinfoEl.addContent(indsprefEl);
        
        Element directEl = new Element("direct");
        directEl.addContent(this.direct);
        spdoinfoEl.addContent(directEl);
        
        
        return spdoinfoEl;
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
