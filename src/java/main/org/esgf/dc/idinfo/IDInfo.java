package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class IDInfo {

	

	private Citation citation;
	private Descript descript;
	private TimePerd timeperd;
	private Status status;
	private SpDom spdom;
	private Keywords keywords;
	private Taxonomy taxonomy;
	private String acconst;
	private String useconst;
	private String datacred;
	private PtContac ptcontac;
	private Browse browse;
	private SecInfo secinfo;
	private Tool tool;
	
	public IDInfo() {
		this.citation = new Citation();
		this.descript = new Descript();
		this.timeperd = new TimePerd();
		this.status = new Status();
		this.spdom = new SpDom();
		this.keywords = new Keywords();
		this.taxonomy = new Taxonomy();
		this.acconst = "";
		this.useconst = "";
		this.datacred = "";
		this.ptcontac = new PtContac();
		this.browse = new Browse();
		this.secinfo = new SecInfo();
		this.tool = new Tool();
	}
	
	

	public Citation getCitation() {
		return citation;
	}

	public void setCitation(Citation citation) {
		this.citation = citation;
	}

	public Descript getDescript() {
		return descript;
	}

	public void setDescript(Descript descript) {
		this.descript = descript;
	}

	public TimePerd getTimeperd() {
		return timeperd;
	}

	public void setTimeperd(TimePerd timeperd) {
		this.timeperd = timeperd;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SpDom getSpdom() {
		return spdom;
	}

	public void setSpdom(SpDom spdom) {
		this.spdom = spdom;
	}

	public Keywords getKeywords() {
		return keywords;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	public String getAcconst() {
		return acconst;
	}

	public void setAcconst(String acconst) {
		this.acconst = acconst;
	}

	public String getUseconst() {
		return useconst;
	}

	public void setUseconst(String useconst) {
		this.useconst = useconst;
	}

	public String getDatacred() {
		return datacred;
	}

	public void setDatacred(String datacred) {
		this.datacred = datacred;
	}

	public PtContac getPtcontac() {
		return ptcontac;
	}

	public void setPtcontac(PtContac ptcontac) {
		this.ptcontac = ptcontac;
	}

	public Browse getBrowse() {
		return browse;
	}

	public void setBrowse(Browse browse) {
		this.browse = browse;
	}

	public SecInfo getSecinfo() {
		return secinfo;
	}

	public void setSecinfo(SecInfo secinfo) {
		this.secinfo = secinfo;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
	
	public Element toElement() {
        Element idinfoEl = new Element("idinfo");

        idinfoEl.addContent(this.citation.toElement());
        idinfoEl.addContent(this.descript.toElement());
        idinfoEl.addContent(this.timeperd.toElement());
        idinfoEl.addContent(this.status.toElement());
        idinfoEl.addContent(this.spdom.toElement());
        idinfoEl.addContent(this.keywords.toElement());
        idinfoEl.addContent(this.taxonomy.toElement());
        
        Element acconstEl = new Element("acconst");
        acconstEl.addContent(this.acconst);
        idinfoEl.addContent(acconstEl);

        Element useconstEl = new Element("useconst");
        useconstEl.addContent(this.useconst);
        idinfoEl.addContent(useconstEl);

        Element datacredEl = new Element("datacred");
        datacredEl.addContent(this.datacred);
        idinfoEl.addContent(datacredEl);
        

        idinfoEl.addContent(this.ptcontac.toElement());
        idinfoEl.addContent(this.browse.toElement());
        idinfoEl.addContent(this.secinfo.toElement());
        idinfoEl.addContent(this.tool.toElement());
        
        return idinfoEl;
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
		
		IDInfo idinfo = new IDInfo();
		
		Citation citation = new Citation();
    	
    	CiteInfo ci = new CiteInfo();
		 
	    citation.setCiteInfo(ci);
		
		idinfo.setCitation(citation);
		
		
		Descript d = new Descript();
		 
		 d.setAbstractD("abstractD1");
		 d.setPurpose("purpose1");
		 d.setSupplinf("supplinf1");
		 
		
		idinfo.setDescript(d);
		
		TimePerd tp = new TimePerd();
		 
		TimeInfo t = new TimeInfo();

		RngDates r = new RngDates();

		r.setBegDate("begDate1");
		r.setEndDate("endDate1");
		
		t.setRngDates(r);
		
		tp.setTimeinfo(t);
		tp.setCurrent("current1");
		
		idinfo.setTimeperd(tp);
		
		Status s = new Status();
		 
		 s.setProgress("progress1");
		 s.setUpdate("update1");
		 
		idinfo.setStatus(s);
		
SpDom sp = new SpDom();
    	
    	sp.setDescgeog("descgeog1");
    	
    	Bounding b = new Bounding();
    	
    	b.setEastbc("eastbc1");
    	b.setNorthbc("northbc1");
    	b.setSouthbc("southbc1");
    	b.setWestbc("westbc1");
    	
    	sp.setBounding(b);
    	
    	idinfo.setSpdom(sp);
    	
Keywords keywords = new Keywords();
    	
    	Place place = new Place();
		
		String placekt = "placekt1";
		
		List<String> placekey = new ArrayList<String>();
		placekey.add("placekey1");
		placekey.add("placekey2");
		
		place.setPlacekt(placekt);
		place.setPlacekey(placekey);
		
    	keywords.setPlace(place);
    	
    	
    	
    	Theme theme = new Theme();
		
		String themekt = "themekt1";
		
		List<String> themekey = new ArrayList<String>();
		themekey.add("themekey1");
		themekey.add("themekey2");
		
		theme.setThemekt(themekt);
		theme.setThemekey(themekey);
		
		//keywords.setTheme(theme);
		
		
Stratum stratum = new Stratum();
		
		String stratumkt = "stratumkt1";
		
		List<String> stratumkey = new ArrayList<String>();
		stratumkey.add("stratumkey1");
		stratumkey.add("stratumkey2");
		
		stratum.setStratumkt(stratumkt);
		stratum.setStratumkey(stratumkey);
		
		keywords.setStratum(stratum);
		
		
Temporal temporal = new Temporal();
		
		String temporalkt = "temporalkt1";
		
		List<String> temporalkey = new ArrayList<String>();
		temporalkey.add("temporalkey1");
		temporalkey.add("temporalkey2");
		
		temporal.setTemporalkt(temporalkt);
		temporal.setTemporalkey(temporalkey);
		
		
		keywords.setTemporal(temporal);
		
		
		idinfo.setKeywords(keywords);
		
		Taxonomy taxonomy = new Taxonomy();
		 
		 Keywtax keywtax = new Keywtax();
			
			keywtax.setTaxonkey("taxonkey1");
			keywtax.setTaxonkt("taxonkt1");
		
		 taxonomy.setKeywtax(keywtax);
		 
		 TaxonSys ts = new TaxonSys();
		 
		 
		 Classsys c = new Classsys();
		 		 
		 		 Classcit classcit = new Classcit();
		 	    	
		 	    	//CiteInfo ci = new CiteInfo();
		 			 
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
		 		     
		 		     SerInfo si = new SerInfo();
		 		    	
		 		    	si.setSerName("serName1");
		 		    	si.setIssue("issue1");
		 		    	
		 		     ci.setSerinfo(si);
		 		     
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
		 
		 
		 idinfo.setTaxonomy(taxonomy);
		 
		 idinfo.setAcconst("acconst1");
		 idinfo.setUseconst("useconst1");
		 idinfo.setDatacred("datacred1");
		 
		 PtContac pt = new PtContac();
		 
		 CntInfo cntinfo = new CntInfo();
		 
Cntaddr caddr = new Cntaddr();
		 
caddr.setAddress("address1");
caddr.setAddrtype("addrtype1");
caddr.setCity("city1");
caddr.setCountry("country1");
caddr.setPostal("postal1");
caddr.setState("state1");
		 
		 cntinfo.setCntAddr(caddr);
		 
CntPerp cPerp = new CntPerp();
		 
		 cPerp.setCntOrg("cntOrg1");
		 cPerp.setCntPer("cntPer1");
		 
		 cntinfo.setCntPerp(cPerp);
		 
		 cntinfo.setCntVoice("cntVoice1");
		 cntinfo.setCntEmail("cntEmail1");
		 cntinfo.setCntFax("cntFax1");
		
		 pt.setCntInfo(cntinfo);
		 
		 
		 idinfo.setPtcontac(pt);
		 
		 
Browse browse = new Browse();
		 
		 browse.setBrowsed("browsed1");
		 browse.setBrowsen("browsen1");
		 browse.setBrowset("browset1");
		 
		 idinfo.setBrowse(browse);
		 
SecInfo sinfo = new SecInfo();
		 
		 sinfo.setSecclass("secclass1");
		 sinfo.setSechandl("sechandl1");
		 sinfo.setSecsys("secsys1");
		 
		 
		 idinfo.setSecinfo(sinfo);
		 
		 Tool tool = new Tool();
		 tool.setTooldesc("tooldesc1");
		 
ToolAcc ta = new ToolAcc();
		 
		 ta.setOnlink("onlink1");
		 ta.setToolinst("toolinst1");
		 ta.setToolcomp("toolcomp1");
		 
		 tool.setToolacc(ta);
		 
ToolCont tc = new ToolCont();
		 
		 CntInfo ccntinfo = new CntInfo();
		 
		 Cntaddr ccc = new Cntaddr();
		 		 
		 		 ccc.setAddress("address1");
		 		 ccc.setAddrtype("addrtype1");
		 		 ccc.setCity("city1");
		 		 ccc.setCountry("country1");
		 		 ccc.setPostal("postal1");
		 		 ccc.setState("state1");
		 		 
		 		 ccntinfo.setCntAddr(ccc);
		 		 
		 CntPerp ccPerp = new CntPerp();
		 		 
		 		 ccPerp.setCntOrg("cntOrg1");
		 		 ccPerp.setCntPer("cntPer1");
		 		 
		 		 cntinfo.setCntPerp(ccPerp);
		 		 
		 		 cntinfo.setCntVoice("cntVoice1");
		 		 cntinfo.setCntEmail("cntEmail1");
		 		 cntinfo.setCntFax("cntFax1");
		 		 
		 		 
	     
	     tc.setCntinfo(ccntinfo);
	     
	     tool.setToolcont(tc);
	     
	     ToolCite tt = new ToolCite();
			CiteInfo cip = new CiteInfo();
			 
			 List<String> onlinkListp = new ArrayList<String>();
			 onlinkListp.add("onlink1");
			 onlinkListp.add("onlink2");
			 cip.setOnlink(onlinkListp);

			 List<String> originListp = new ArrayList<String>();
			 originListp.add("origin1");
			 originListp.add("origin2");
			 cip.setOrigin(originListp);
			 
			 
			 cip.setGeoform("geoform1");
			 cip.setPubdate("pubdate1");
			 cip.setTitle("title1");
			 
			 PubInfo pp = new PubInfo();
		    	pp.setPublish("publish1");
		    	pp.setPubPlace("pubPlace1");
		     cip.setPubinfo(p);
		     
		     SerInfo ss = new SerInfo();
		    	
		    	ss.setSerName("serName1");
		    	ss.setIssue("issue1");
		    	
		     cip.setSerinfo(ss);
		     
		     tt.setCiteinfo(cip);
		     
		 
		 tool.setToolcite(tt);
		 
		 idinfo.setTool(tool);
		
		 idinfo.toFile("idinfo.xml");
		
	}
	
	
	
}
