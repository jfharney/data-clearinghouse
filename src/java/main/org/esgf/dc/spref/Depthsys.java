package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
			<depthsys>
				<depthdn></depthdn>
				<depthres></depthres>
				<depthdu></depthdu>
				<depthem></depthem>
			</depthsys>
 */
public class Depthsys {

	private String depthdn;
	private String depthres;
	private String depthdu;
	private String depthem;
	
	public Depthsys() {
		this.setDepthdn("");
		this.setDepthres("");
		this.setDepthdu("");
		this.setDepthem("");
	}

	public String getDepthdn() {
		return depthdn;
	}

	public void setDepthdn(String depthdn) {
		this.depthdn = depthdn;
	}

	public String getDepthres() {
		return depthres;
	}

	public void setDepthres(String depthres) {
		this.depthres = depthres;
	}

	public String getDepthdu() {
		return depthdu;
	}

	public void setDepthdu(String depthdu) {
		this.depthdu = depthdu;
	}

	public String getDepthem() {
		return depthem;
	}

	public void setDepthem(String depthem) {
		this.depthem = depthem;
	}
	
	
	public Element toElement() {
        Element depthsysEl = new Element("depthsys");

        Element depthdnEl = new Element("depthdn");
        depthdnEl.addContent(this.depthdn);
        depthsysEl.addContent(depthdnEl);

        Element depthresEl = new Element("depthres");
        depthresEl.addContent(this.depthres);
        depthsysEl.addContent(depthresEl);

        Element depthduEl = new Element("depthdu");
        depthduEl.addContent(this.depthdu);
        depthsysEl.addContent(depthduEl);

        Element depthemEl = new Element("depthem");
        depthemEl.addContent(this.depthem);
        depthsysEl.addContent(depthemEl);
        
        
        return depthsysEl;
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
