package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <taxonsys>
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
				<idref>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
					</citeinfo>
				</idref>
				<taxonpro></taxonpro>
				<taxoncom></taxoncom>
			</taxonsys>
 */
public class TaxonSys {

	private Classsys classys;
	private IDRef idref;
	private String taxonpro;
	private String taxoncom;
	
	public TaxonSys() {
		this.classys = new Classsys();
		this.idref = new IDRef();
		this.taxoncom = "";
		this.taxonpro = "";
	}
	

	public Classsys getClassys() {
		return classys;
	}

	public void setClassys(Classsys classys) {
		this.classys = classys;
	}

	public IDRef getIdref() {
		return idref;
	}

	public void setIdref(IDRef idref) {
		this.idref = idref;
	}

	public String getTaxonpro() {
		return taxonpro;
	}

	public void setTaxonpro(String taxonpro) {
		this.taxonpro = taxonpro;
	}

	public String getTaxoncom() {
		return taxoncom;
	}

	public void setTaxoncom(String taxoncom) {
		this.taxoncom = taxoncom;
	}

	public Element toElement() {
        Element taxonsysEl = new Element("taxonsys");

        taxonsysEl.addContent(this.classys.toElement());
        taxonsysEl.addContent(this.idref.toElement());
        
        Element taxonproEl = new Element("taxonpro");
        taxonproEl.addContent(this.taxonpro);
        taxonsysEl.addContent(taxonproEl);
        
        Element taxoncomEl = new Element("taxoncom");
        taxoncomEl.addContent(this.taxoncom);
        taxonsysEl.addContent(taxoncomEl);
        
        
        return taxonsysEl;
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
		 
		 TaxonSys ts = new TaxonSys();
		 
		 
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
		 
		 ts.setClassys(c);
		 
		 
IDRef idref = new IDRef();
		 
		
	     idref.setCiteinfo(ci);
	     
	     
	     ts.setIdref(idref);
	     
	     
	     ts.setTaxoncom("taxoncom1");
	     ts.setTaxonpro("taxonpro1");
	     
	     
	     ts.toFile("TaxonSys.xml");
	 }
	
	
	
}
