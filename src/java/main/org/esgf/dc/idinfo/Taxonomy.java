package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;



/*
 * <taxonomy>
			<keywtax>
				<taxonkt></taxonkt>
				<taxonkey></taxonkey>
			</keywtax>
			<taxonsys>
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
			<taxongen></taxongen>
			<taxoncl>
				<taxonrn></taxonrn>
				<taxonrv></taxonrv>
			</taxoncl>
		</taxonomy>
 */
public class Taxonomy {


	private Keywtax keywtax;
	private TaxonSys taxonsys;
	private String taxongen;
	private TaxonCL taxoncl;
	
	public Taxonomy() {
		this.keywtax = new Keywtax();
		this.taxonsys = new TaxonSys();
		this.taxongen = "";
		this.taxoncl = new TaxonCL();
	}
	

	public Keywtax getKeywtax() {
		return keywtax;
	}

	public void setKeywtax(Keywtax keywtax) {
		this.keywtax = keywtax;
	}

	public TaxonSys getTaxonsys() {
		return taxonsys;
	}

	public void setTaxonsys(TaxonSys taxonsys) {
		this.taxonsys = taxonsys;
	}

	public String getTaxongen() {
		return taxongen;
	}

	public void setTaxongen(String taxongen) {
		this.taxongen = taxongen;
	}

	public TaxonCL getTaxoncl() {
		return taxoncl;
	}

	public void setTaxoncl(TaxonCL taxoncl) {
		this.taxoncl = taxoncl;
	}
	
	
	public Element toElement() {
        Element taxonomyEl = new Element("taxonomy");
        
        taxonomyEl.addContent(this.keywtax.toElement());
        
        taxonomyEl.addContent(this.taxonsys.toElement());
        
        Element taxongenEl = new Element("taxongen");
        taxongenEl.addContent("taxongen1");
        taxonomyEl.addContent(taxongenEl);
        
        taxonomyEl.addContent(this.taxoncl.toElement());
        
        
        return taxonomyEl;
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
		 Taxonomy taxonomy = new Taxonomy();
		 
		 Keywtax keywtax = new Keywtax();
			
			keywtax.setTaxonkey("taxonkey1");
			keywtax.setTaxonkt("taxonkt1");
		
		 taxonomy.setKeywtax(keywtax);
		 
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
		 
		 taxonomy.setTaxonsys(ts);
		 
		 taxonomy.setTaxongen("taxongen1");
		 
		 
		 
		 TaxonCL taxoncl = new TaxonCL();
			
			taxoncl.setTaxonrn("taxonrn1");
			taxoncl.setTaxonrv("taxonrv1");
	 
		 taxonomy.setTaxoncl(taxoncl);
		 
		 taxonomy.toFile("taxonomy.xml");
	 }
	 
	
	
}
