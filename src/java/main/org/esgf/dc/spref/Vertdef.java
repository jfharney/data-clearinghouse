package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <vertdef>
			<altsys>
				<altenc></altenc>
				<altdatum></altdatum>
				<altunits></altunits>
				<altres></altres>
			</altsys>
			<depthsys>
				<depthdn></depthdn>
				<depthres></depthres>
				<depthdu></depthdu>
				<depthem></depthem>
			</depthsys>
		</vertdef>
 */
public class Vertdef {

	private Altsys altsys;
	private Depthsys depthsys;
	
	public Vertdef() {
		this.setAltsys(new Altsys());
		this.setDepthsys(new Depthsys());
	}

	public Altsys getAltsys() {
		return altsys;
	}

	public void setAltsys(Altsys altsys) {
		this.altsys = altsys;
	}

	public Depthsys getDepthsys() {
		return depthsys;
	}

	public void setDepthsys(Depthsys depthsys) {
		this.depthsys = depthsys;
	}
	
	public Element toElement() {
        Element vertdefEl = new Element("vertdef");

        vertdefEl.addContent(this.altsys.toElement());
        vertdefEl.addContent(this.depthsys.toElement());
        
        return vertdefEl;
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
