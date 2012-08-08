package org.esgf.dc.metainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*\
 * <metainfo>
		<metd></metd>
		<metc>
			<cntinfo>
				<cntperp>
					<cntper></cntper>
					<cntorg></cntorg>
				</cntperp>
				<cntpos></cntpos>
				<cntaddr>
					<addrtype>mailing and physical</addrtype>
					<address></address>
					<city></city>
					<state></state>
					<postal></postal>
					<country></country>
				</cntaddr>
				<cntvoice></cntvoice>
				<cntemail></cntemail>
			</cntinfo>
		</metc>
		<metstdn>FDGC Content Standard for Digital Geospatial Metadata and
			Biological Data Profile</metstdn>
		<metstdv>FDGC-STD-001-1998</metstdv>
		<metac></metac>
		<metuc></metuc>
	</metainfo>
 */
public class Metainfo {
	
	private String metd;
	private Metc metc;
	private String metstdn;
	private String metstdv;
	private String metac;
	private String metuc;
	
	public Metainfo() {
		
		this.setMetd("");
		this.setMetc(new Metc());
		this.setMetstdn("");
		this.setMetstdv("");
		this.setMetac("");
		this.setMetuc("");
	}

	public String getMetd() {
		return metd;
	}

	public void setMetd(String metd) {
		this.metd = metd;
	}

	public Metc getMetc() {
		return metc;
	}

	public void setMetc(Metc metc) {
		this.metc = metc;
	}

	public String getMetstdn() {
		return metstdn;
	}

	public void setMetstdn(String metstdn) {
		this.metstdn = metstdn;
	}

	public String getMetstdv() {
		return metstdv;
	}

	public void setMetstdv(String metstdv) {
		this.metstdv = metstdv;
	}

	public String getMetac() {
		return metac;
	}

	public void setMetac(String metac) {
		this.metac = metac;
	}

	public String getMetuc() {
		return metuc;
	}

	public void setMetuc(String metuc) {
		this.metuc = metuc;
	}
	
	public Element toElement() {
        Element metainfoEl = new Element("metainfo");

        Element metdEl = new Element("metd");
        metdEl.addContent(this.metd);
        metainfoEl.addContent(metdEl);
        
        metainfoEl.addContent(this.metc.toElement());

        Element metstdnEl = new Element("metstdn");
        metstdnEl.addContent(this.metstdn);
        metainfoEl.addContent(metstdnEl);

        Element metstdvEl = new Element("metstdv");
        metstdvEl.addContent(this.metstdv);
        metainfoEl.addContent(metstdvEl);

        Element metacEl = new Element("metac");
        metacEl.addContent(this.metac);
        metainfoEl.addContent(metacEl);
        
        
        
        return metainfoEl;
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
		 
		 Metainfo metainfo = new Metainfo();
		 
		 metainfo.toFile("metainfo.xml");
		 
	 }
	

}
