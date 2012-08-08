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
 * <srcinfo>
				<srccite>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
						<onlink></onlink>
					</citeinfo>
				</srccite>
				<srcscale></srcscale>
				<typesrc></typesrc>
				<srctime>
					<timeinfo>
						<sngdate>
							<caldate></caldate>
						</sngdate>
					</timeinfo>
					<srccurr></srccurr>
				</srctime>
				<srccitea></srccitea>
				<srccontr></srccontr>
			</srcinfo>
 */
public class Srcinfo {

	private Srccite srccite;
	private String srcscale;
	private String typesrc;
	private Srctime srctime;
	private String srccitea;
	private String srccontr;
	
	public Srcinfo() {
		this.srccite = new Srccite();
		this.srcscale = "";
		this.typesrc = "";
		this.srctime = new Srctime();
		this.srccitea = "";
		this.srccontr = "";
	}
	
	
	public Srccite getSrccite() {
		return srccite;
	}

	public void setSrccite(Srccite srccite) {
		this.srccite = srccite;
	}

	public String getSrcscale() {
		return srcscale;
	}

	public void setSrcscale(String srcscale) {
		this.srcscale = srcscale;
	}

	public String getTypesrc() {
		return typesrc;
	}

	public void setTypesrc(String typesrc) {
		this.typesrc = typesrc;
	}

	public Srctime getSrctime() {
		return srctime;
	}

	public void setSrctime(Srctime srctime) {
		this.srctime = srctime;
	}

	public String getSrccitea() {
		return srccitea;
	}

	public void setSrccitea(String srccitea) {
		this.srccitea = srccitea;
	}

	public String getSrccontr() {
		return srccontr;
	}

	public void setSrccontr(String srccontr) {
		this.srccontr = srccontr;
	}

	public Element toElement() {
        Element srcinfoEl = new Element("srcinfo");

        srcinfoEl.addContent(this.srccite.toElement());
        
        Element srcscaleEl = new Element("srcscale");
        srcscaleEl.addContent(this.srcscale);
        srcinfoEl.addContent(srcscaleEl);

        Element typesrcEl = new Element("typesrc");
        typesrcEl.addContent(this.typesrc);
        srcinfoEl.addContent(typesrcEl);

        srcinfoEl.addContent(this.srctime.toElement());

        Element srcciteaEl = new Element("srccitea");
        srcciteaEl.addContent(this.srccitea);
        srcinfoEl.addContent(srcciteaEl);

        Element srccontrEl = new Element("srccontr");
        srccontrEl.addContent(this.srccontr);
        srcinfoEl.addContent(srccontrEl);
        
        
        
        return srcinfoEl;
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
		 
		 Srcinfo srcinfo = new Srcinfo();
		 
		 Srccite srccite = new Srccite();
		 
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
		 	     
		 	     
		 		 srccite.setCiteinfo(ci);
		 
		 srcinfo.setSrccite(srccite);
		 
		 srcinfo.setSrcscale("srcscale1");
		 
		 Srctime srctime = new Srctime();
		 
		 Srctime src = new Srctime();
		 
		 Timeinfo t = new Timeinfo();
		 		 
		 		 Sngdate sngdate = new Sngdate();
		 		 
		 		 sngdate.setCaldate("caldate1");
		 		 
		 		 t.setSngdate(sngdate);
		 		 
		 		 src.setTimeinfo(t);
		 		 src.setSrccurr("srccurr1");
		 
		 srcinfo.setSrctime(srctime);
		 
		 srcinfo.setSrccontr("srccontr1");
		 
		 srcinfo.toFile("srcinfo.xml");
		 
		 
	 }
	
	
}
