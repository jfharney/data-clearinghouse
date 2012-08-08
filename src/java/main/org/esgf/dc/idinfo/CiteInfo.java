package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class CiteInfo {

	private List<String> origin;
	private List<String> onlink;
	private String pubdate;
	private String title;
	private String geoform;
	private SerInfo serinfo;
	private PubInfo pubinfo;
	
	public CiteInfo() {
		this.origin = new ArrayList<String>();
		this.origin.add("");
		this.onlink = new ArrayList<String>();
		this.onlink.add("");
		this.pubdate = "";
		this.title = "";
		this.geoform = "";
		this.serinfo = new SerInfo();
		this.pubinfo = new PubInfo();
	}
	

	public List<String> getOrigin() {
		return origin;
	}

	public void setOrigin(List<String> origin) {
		this.origin = origin;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGeoform() {
		return geoform;
	}

	public void setGeoform(String geoform) {
		this.geoform = geoform;
	}

	public List<String> getOnlink() {
		return onlink;
	}

	public void setOnlink(List<String> onlink) {
		this.onlink = onlink;
	}

	public SerInfo getSerinfo() {
		return serinfo;
	}

	public void setSerinfo(SerInfo serinfo) {
		this.serinfo = serinfo;
	}

	public PubInfo getPubinfo() {
		return pubinfo;
	}

	public void setPubinfo(PubInfo pubinfo) {
		this.pubinfo = pubinfo;
	}


/*
	<citeinfo>
		<origin></origin>
		<pubdate></pubdate>
		<title></title>
		<geoform></geoform>
		<onlink></onlink>
		<serinfo>
			<sername></sername>
			<issue></issue>
		</serinfo>
		<pubinfo>
			<pubplace></pubplace>
			<publish></publish>
		</pubinfo>	
	</citeinfo>
*/
	 public Element toElement() {
        Element citeinfoEl = new Element("citeinfo");
        
        for(int i=0;i<this.origin.size();i++) {
            Element originEl = new Element("origin");
            originEl.addContent(this.origin.get(i));
            citeinfoEl.addContent(originEl);
        }

        for(int i=0;i<this.onlink.size();i++) {
            Element onlinkEl = new Element("onlink");
            onlinkEl.addContent(this.onlink.get(i));
            citeinfoEl.addContent(onlinkEl);
        }
        
        
        Element pubdateEl = new Element("pubdate");
        pubdateEl.addContent(this.pubdate);
        citeinfoEl.addContent(pubdateEl);
        
        Element titleEl = new Element("title");
        titleEl.addContent(this.title);
        citeinfoEl.addContent(titleEl);
        
        Element geoformEl = new Element("geoform");
        geoformEl.addContent(this.geoform);
        citeinfoEl.addContent(geoformEl);
        
        
        citeinfoEl.addContent(this.serinfo.toElement()); 
        
        citeinfoEl.addContent(this.pubinfo.toElement());
        
        return citeinfoEl;
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
	     
	     ci.toFile("citeinfo.xml");
	     		
		 		
	 }
	
	
}
