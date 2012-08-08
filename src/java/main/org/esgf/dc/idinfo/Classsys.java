package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
<classsys>
<classcit>
	<citeinfo>
		<origin></origin>
		<pubdate></pubdate>
		<title></title>
		<geoform></geoform>
		<pubinfo>
			<pubplace></pubplace>
			<publish></publish>
		</pubinfo>
		<onlink></onlink>
	</citeinfo>
</classcit>
<classmod></classmod>
</classsys>
*/
public class Classsys {

	private Classcit classcit;
	private String classmod;
	
	public Classsys() {
		this.classcit = new Classcit();
		this.setClassmod("");
	}

	public Classcit getClasscit() {
		return classcit;
	}

	public void setClasscit(Classcit classcit) {
		this.classcit = classcit;
	}
	
	public String getClassmod() {
		return classmod;
	}

	public void setClassmod(String classmod) {
		this.classmod = classmod;
	}
	
	
	public Element toElement() {
        Element classsysEl = new Element("classsys");
        
        classsysEl.addContent(this.classcit.toElement());
        
        Element classModEl = new Element("classmod");
        classModEl.addContent(this.classmod);
        classsysEl.addContent(classModEl);
        
        
        return classsysEl;
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
		 
		 Classsys c = new Classsys();
		 
		 Classcit classcit = new Classcit();
	    	
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
		     
		     classcit.setCiteInfo(ci);
		     
		 c.setClasscit(classcit);
		 c.setClassmod("classmod1");
		 
		 c.toFile("classsys.xml");
	 }
	 
	
}
