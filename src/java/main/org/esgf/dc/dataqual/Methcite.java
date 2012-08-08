package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.esgf.dc.idinfo.CiteInfo;
import org.esgf.dc.idinfo.PubInfo;
import org.esgf.dc.idinfo.SerInfo;
import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <methcite>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
						<onlink></onlink>
					</citeinfo>
				</methcite>
 */
public class Methcite {

	private CiteInfo citeinfo;
	
	public Methcite() {
		this.setCiteinfo(new CiteInfo());
		
	}

	public CiteInfo getCiteinfo() {
		return citeinfo;
	}

	public void setCiteinfo(CiteInfo citeinfo) {
		this.citeinfo = citeinfo;
	}
	
	public Element toElement() {
        Element methciteEl = new Element("methcite");
        
        methciteEl.addContent(this.citeinfo.toElement());
        
        return methciteEl;
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
		 
		 Methcite m = new Methcite();
		 
CiteInfo ci = new CiteInfo();
		 
		 List<String> onlinkList = new ArrayList<String>();
		 onlinkList.add("onlink1");
		 onlinkList.add("onlink2");
		 ci.setOnlink(onlinkList);

		 List<String> originList = new ArrayList<String>();
		 originList.add("origin1");
		 originList.add("origin2");
		 ci.setOrigin(originList);
		 
		 
		 ci.setGeoform("geoform1");
		 ci.setPubdate("pubdate1");
		 ci.setTitle("title1");
		 
		 PubInfo p = new PubInfo();
	    	p.setPublish("publish1");
	    	p.setPubPlace("pubPlace1");
	     ci.setPubinfo(p);
	     
	     SerInfo s = new SerInfo();
	    	
	    	s.setSerName("serName1");
	    	s.setIssue("issue1");
	    	
	     ci.setSerinfo(s);
	     
	     
		 m.setCiteinfo(ci);
		 
		 m.toFile("methcite.xml");
		 
	 }
	
}
