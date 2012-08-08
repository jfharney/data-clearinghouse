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
 * <lineage>
			<method>
				<methtype></methtype>
				<methdesc></methdesc>
				<methcite>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
						<onlink></onlink>
					</citeinfo>
				</methcite>
			</method>
			<srcinfo>
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
			<procstep>
				<procdesc></procdesc>
				<srcused></srcused>
				<procdate></procdate>
			</procstep>
		</lineage>
 */
public class Lineage {


	private Method method;
	private Srcinfo srcinfo;
	private Procstep procstep;
	
	public Lineage() {
		this.method = new Method();
		this.srcinfo = new Srcinfo();
		this.procstep = new Procstep();
	}
	
	
	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Srcinfo getSrcinfo() {
		return srcinfo;
	}

	public void setSrcinfo(Srcinfo srcinfo) {
		this.srcinfo = srcinfo;
	}

	public Procstep getProcstep() {
		return procstep;
	}

	public void setProcstep(Procstep procstep) {
		this.procstep = procstep;
	}

	
	public Element toElement() {
        Element lineageEl = new Element("lineage");


        lineageEl.addContent(this.method.toElement());
        lineageEl.addContent(this.srcinfo.toElement());
        lineageEl.addContent(this.procstep.toElement());
        
        return lineageEl;
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
		 Lineage l = new Lineage();
		 
Method m = new Method();
		 
		 Methcite methcite = new Methcite();
		 
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
		 	     
		 	     
		 		 methcite.setCiteinfo(ci);
		 		 
		 
		 m.setMethcite(methcite);
		 
		 m.setMethdesc("methdesc1");
		 m.setMethtype("methtype1");
		 
		 l.setMethod(m);
		 
		 
Srcinfo srcinfo = new Srcinfo();
		 
		 Srccite srccite = new Srccite();
		 
		 /*
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
		 */	     
		 	     
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
		 
		 l.setSrcinfo(srcinfo);
		 
		 Procstep procstep = new Procstep();
		 procstep.setProcdate("procdate1");
		 procstep.setProcdesc("procdesc1");
		 procstep.setSrcused("srcused1");
		 
		 l.setProcstep(procstep);
		 
		 l.toFile("lineage.xml");
		 
	 }
	
	
	
}
