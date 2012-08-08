package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;



public class Attr {

	private String attrlabl;
	private String attrdef;
	private String attrdefs;
	private Attrdomv attrdomv;
	private Rdom rdom;
	private Codesetd codesetd;
	
	public Attr() {
		this.setAttrlabl("");
		this.setAttrdef("");
		this.setAttrdefs("");
		this.setAttrdomv(new Attrdomv());
		this.setRdom(new Rdom());
		this.setCodesetd(new Codesetd());
	}

	public String getAttrlabl() {
		return attrlabl;
	}

	public void setAttrlabl(String attrlabl) {
		this.attrlabl = attrlabl;
	}

	public String getAttrdef() {
		return attrdef;
	}

	public void setAttrdef(String attrdef) {
		this.attrdef = attrdef;
	}

	public String getAttrdefs() {
		return attrdefs;
	}

	public void setAttrdefs(String attrdefs) {
		this.attrdefs = attrdefs;
	}

	public Attrdomv getAttrdomv() {
		return attrdomv;
	}

	public void setAttrdomv(Attrdomv attrdomv) {
		this.attrdomv = attrdomv;
	}

	public Rdom getRdom() {
		return rdom;
	}

	public void setRdom(Rdom rdom) {
		this.rdom = rdom;
	}

	public Codesetd getCodesetd() {
		return codesetd;
	}

	public void setCodesetd(Codesetd codesetd) {
		this.codesetd = codesetd;
	}
	
	
	/*
	 * <attr>
					<attrlabl></attrlabl>
					<attrdef></attrdef>
					<attrdefs></attrdefs>
					<attrdomv>
						<edom>
							<edomv></edomv>
							<edomvd></edomvd>
							<edomvds></edomvds>
						</edom>
					</attrdomv>
					<rdom>
						<rdommin></rdommin>
						<rdommax></rdommax>
					</rdom>
					<codesetd>
						<codesetn></codesetn>
						<codesets></codesets>
					</codesetd>
				</attr>
	 */
	public Element toElement() {
        Element attrEl = new Element("attr");

        Element attrlablEl = new Element("attrlabl");
        attrlablEl.addContent(this.attrlabl);
        attrEl.addContent(attrlablEl);

        Element attrdefEl = new Element("attrdef");
        attrdefEl.addContent(this.attrdef);
        attrEl.addContent(attrdefEl);

        Element attrdefsEl = new Element("attrdefs");
        attrdefsEl.addContent(this.attrdefs);
        attrEl.addContent(attrdefsEl);
        
        attrEl.addContent(this.attrdomv.toElement());
        
        attrEl.addContent(this.rdom.toElement());
        
        attrEl.addContent(this.codesetd.toElement());
        
        return attrEl;
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
		 String attrlabl = "attrlabl1";
		 String attrdef = "attrdef1";
		 String attrdefs = "attrdefs1";
		 Attrdomv attrdomv = new Attrdomv();
		 Rdom rdom = new Rdom();
		 Codesetd codesetd = new Codesetd();
		 
		 Attr attr = new Attr();
		 attr.setAttrdef(attrdef);
		 attr.setAttrdefs(attrdefs);
		 attr.setAttrlabl(attrlabl);
		 attr.setAttrdomv(attrdomv);
		 attr.setRdom(rdom);
		 attr.setCodesetd(codesetd);
		 
		 attr.toFile("attr.xml");
		 
		 
		 
	 }
	
	
	
	
}
