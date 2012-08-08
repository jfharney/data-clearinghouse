package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <ptcontac>
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
		</ptcontac>
 */
public class PtContac {


	private CntInfo cntInfo;
	
	public PtContac() {
		this.cntInfo = new CntInfo();
	}

	public CntInfo getCntInfo() {
		return cntInfo;
	}


	public void setCntInfo(CntInfo cntInfo) {
		this.cntInfo = cntInfo;
	}

	public Element toElement() {
        Element ptContacEl = new Element("ptcontac");

        ptContacEl.addContent(this.cntInfo.toElement());
        
        return ptContacEl;
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
		 PtContac pt = new PtContac();
		 
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
			
			 pt.setCntInfo(cntinfo);
			 
			 pt.toFile("ptcontac.xml");
			 
	 }
	 
}
