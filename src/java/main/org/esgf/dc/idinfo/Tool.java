package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
		<tool>
			<tooldesc></tooldesc>
			<toolacc>
				<onlink></onlink>
				<toolinst></toolinst>
				<toolcomp></toolcomp>
			</toolacc>
			<toolcont>
				<cntinfo>
					<cntperp>
						<cntper></cntper>
						<cntorg></cntorg>
					</cntperp>
					<cntaddr>
						<addrtype>mailing and physical</addrtype>
						<address></address>
						<city></city>
						<state></state>
						<postal></postal>
						<country></country>
					</cntaddr>
					<cntvoice></cntvoice>
					<cntfax></cntfax>
					<cntemail></cntemail>
				</cntinfo>
			</toolcont>
			<toolcite>
				<citeinfo>
					<origin></origin>
					<pubdate></pubdate>
					<title></title>
					<geoform></geoform>
					<onlink></onlink>
				</citeinfo>
			</toolcite>
		</tool>
 */
public class Tool {


	private String tooldesc;
	private ToolAcc toolacc;
	private ToolCont toolcont;
	private ToolCite toolcite;
	
	public Tool() {
		this.tooldesc = "";
		this.toolacc = new ToolAcc();
		this.toolcont = new ToolCont();
		this.toolcite = new ToolCite();
	}

	
	public String getTooldesc() {
		return tooldesc;
	}

	public void setTooldesc(String tooldesc) {
		this.tooldesc = tooldesc;
	}

	public ToolAcc getToolacc() {
		return toolacc;
	}

	public void setToolacc(ToolAcc toolacc) {
		this.toolacc = toolacc;
	}

	public ToolCont getToolcont() {
		return toolcont;
	}

	public void setToolcont(ToolCont toolcont) {
		this.toolcont = toolcont;
	}

	public ToolCite getToolcite() {
		return toolcite;
	}

	public void setToolcite(ToolCite toolcite) {
		this.toolcite = toolcite;
	}
	
	public Element toElement() {
        Element toolEl = new Element("tool");

        Element tooldescEl = new Element("tooldesc");
        tooldescEl.addContent(this.tooldesc);
        toolEl.addContent(tooldescEl);

        toolEl.addContent(this.toolacc.toElement());
        toolEl.addContent(this.toolcont.toElement());
        toolEl.addContent(this.toolcite.toElement());
       
        return toolEl;
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
		 
		 Tool tool = new Tool();
		 tool.setTooldesc("tooldesc1");
		 
ToolAcc ta = new ToolAcc();
		 
		 ta.setOnlink("onlink1");
		 ta.setToolinst("toolinst1");
		 ta.setToolcomp("toolcomp1");
		 
		 tool.setToolacc(ta);
		 
ToolCont tc = new ToolCont();
		 
		 CntInfo cntinfo = new CntInfo();
		 
		 Cntaddr c = new Cntaddr();
		 		 
		 		 c.setAddress("address1");
		 		 c.setAddrtype("addrtype1");
		 		 c.setCity("city1");
		 		 c.setCountry("country1");
		 		 c.setPostal("postal1");
		 		 c.setState("state1");
		 		 
		 		 cntinfo.setCntAddr(c);
		 		 
		 CntPerp cPerp = new CntPerp();
		 		 
		 		 cPerp.setCntOrg("cntOrg1");
		 		 cPerp.setCntPer("cntPer1");
		 		 
		 		 cntinfo.setCntPerp(cPerp);
		 		 
		 		 cntinfo.setCntVoice("cntVoice1");
		 		 cntinfo.setCntEmail("cntEmail1");
		 		 cntinfo.setCntFax("cntFax1");
		 		 
		 		 
	     
	     tc.setCntinfo(cntinfo);
	     
	     tool.setToolcont(tc);
	     
	     ToolCite t = new ToolCite();
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
		     
		     t.setCiteinfo(ci);
		     
		 
		 tool.setToolcite(t);
		 
		 tool.toFile("tool.xml");
	     
		 
		 
	 }
	 
	
}
