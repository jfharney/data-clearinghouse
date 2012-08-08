package org.esgf.dc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.dataqual.Dataqual;
import org.esgf.dc.distinfo.Distinfo;
import org.esgf.dc.eainfo.Eainfo;
import org.esgf.dc.idinfo.IDInfo;
import org.esgf.dc.idinfo.XmlFormatter;
import org.esgf.dc.metainfo.Metainfo;
import org.esgf.dc.spdoinfo.Spdoinfo;
import org.esgf.dc.spref.Spref;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class Metadata {

	private IDInfo idinfo;
	private Dataqual dataqual;
	private Spdoinfo spdoinfo;
	private Spref spref;
	private Eainfo eainfo;
	private Distinfo distinfo;
	private Metainfo metainfo;
	
	public Metadata() {
		this.setIdinfo(new IDInfo());
		this.setDataqual(new Dataqual());
		this.setSpdoinfo(new Spdoinfo());
		this.setSpref(new Spref());
		this.setEainfo(new Eainfo());
		this.setDistinfo(new Distinfo());
		this.setMetainfo(new Metainfo());
	}

	public IDInfo getIdinfo() {
		return idinfo;
	}

	public void setIdinfo(IDInfo idinfo) {
		this.idinfo = idinfo;
	}

	public Dataqual getDataqual() {
		return dataqual;
	}

	public void setDataqual(Dataqual dataqual) {
		this.dataqual = dataqual;
	}

	public Spdoinfo getSpdoinfo() {
		return spdoinfo;
	}

	public void setSpdoinfo(Spdoinfo spdoinfo) {
		this.spdoinfo = spdoinfo;
	}

	public Spref getSpref() {
		return spref;
	}

	public void setSpref(Spref spref) {
		this.spref = spref;
	}

	public Eainfo getEainfo() {
		return eainfo;
	}

	public void setEainfo(Eainfo eainfo) {
		this.eainfo = eainfo;
	}

	public Distinfo getDistinfo() {
		return distinfo;
	}

	public void setDistinfo(Distinfo distinfo) {
		this.distinfo = distinfo;
	}

	public Metainfo getMetainfo() {
		return metainfo;
	}

	public void setMetainfo(Metainfo metainfo) {
		this.metainfo = metainfo;
	}
	
	
	public Element toElement() {
        Element metadataEl = new Element("metadata");


        metadataEl.addContent(this.idinfo.toElement());
        metadataEl.addContent(this.dataqual.toElement());
        metadataEl.addContent(this.spdoinfo.toElement());
        metadataEl.addContent(this.spref.toElement());
        metadataEl.addContent(this.eainfo.toElement());
        metadataEl.addContent(this.distinfo.toElement());
        metadataEl.addContent(this.metainfo.toElement());
        
        return metadataEl;
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
		 
		 Metadata metadata = new Metadata();
		 metadata.toFile("metadata.xml");
	 }
	
}
