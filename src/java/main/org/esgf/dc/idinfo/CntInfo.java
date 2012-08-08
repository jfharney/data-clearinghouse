package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class CntInfo {

	private CntPerp cntPerp;
	private Cntaddr cntAddr;
	
	private String cntVoice;
	private String cntFax;
	private String cntEmail;
	
	public CntInfo() {
		this.cntPerp = new CntPerp();
		this.cntAddr = new Cntaddr();
		
		this.cntVoice = "";
		this.cntFax = "";
		this.cntEmail = "";
	}
	

	public CntPerp getCntPerp() {
		return cntPerp;
	}

	public void setCntPerp(CntPerp cntPerp) {
		this.cntPerp = cntPerp;
	}

	public Cntaddr getCntAddr() {
		return cntAddr;
	}

	public void setCntAddr(Cntaddr cntAddr) {
		this.cntAddr = cntAddr;
	}

	public String getCntVoice() {
		return cntVoice;
	}

	public void setCntVoice(String cntVoice) {
		this.cntVoice = cntVoice;
	}

	public String getCntFax() {
		return cntFax;
	}

	public void setCntFax(String cntFax) {
		this.cntFax = cntFax;
	}

	public String getCntEmail() {
		return cntEmail;
	}

	public void setCntEmail(String cntEmail) {
		this.cntEmail = cntEmail;
	}

	
	public Element toElement() {
        Element cntinfoEl = new Element("cntinfo");

        cntinfoEl.addContent(this.cntPerp.toElement());
        cntinfoEl.addContent(this.cntAddr.toElement());
        
        Element cntVoiceEl = new Element("cntvoice");
        cntVoiceEl.addContent(this.cntVoice);
        cntinfoEl.addContent(cntVoiceEl);

        Element cntFaxEl = new Element("cntfax");
        cntFaxEl.addContent(this.cntFax);
        cntinfoEl.addContent(cntFaxEl);

        Element cntEmailEl = new Element("cntemail");
        cntEmailEl.addContent(this.cntEmail);
        cntinfoEl.addContent(cntEmailEl);
        
        
        return cntinfoEl;
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
		 
		 cntinfo.toFile("cntinfo.xml");
		 
	 }
	
}
