package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <procstep>
				<procdesc></procdesc>
				<srcused></srcused>
				<procdate></procdate>
			</procstep>
 */
public class Procstep {


	private String procdesc;
	private String srcused;
	private String procdate;
	
	public Procstep() {
		this.procdesc = "";
		this.srcused = "";
		this.procdate = "";
	}
	

	public String getProcdesc() {
		return procdesc;
	}

	public void setProcdesc(String procdesc) {
		this.procdesc = procdesc;
	}

	public String getSrcused() {
		return srcused;
	}

	public void setSrcused(String srcused) {
		this.srcused = srcused;
	}

	public String getProcdate() {
		return procdate;
	}

	public void setProcdate(String procdate) {
		this.procdate = procdate;
	}
	
	
	public Element toElement() {
        Element procstepEl = new Element("procstep");

        Element procdescEl = new Element("procdesc");
        procdescEl.addContent(this.procdesc);
        procstepEl.addContent(procdescEl);

        Element srcusedEl = new Element("srcused");
        srcusedEl.addContent(this.srcused);
        procstepEl.addContent(srcusedEl);

        Element procdateEl = new Element("procdate");
        procdateEl.addContent(this.procdate);
        procstepEl.addContent(procdateEl);
        
        return procstepEl;
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
		 
		 Procstep p = new Procstep();
		 p.setProcdate("procdate1");
		 p.setProcdesc("procdesc1");
		 p.setSrcused("srcused1");
		 
		 
	 }
	
	
	
	
	
}
