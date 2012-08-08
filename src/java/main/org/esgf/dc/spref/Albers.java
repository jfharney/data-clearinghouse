package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <albers>
						<stdparll></stdparll>
						<longcm></longcm>
						<latprjo></latprjo>
						<feast></feast>
						<fnorth></fnorth>
					</albers>
 */
public class Albers {

	private String strparll;
	private String longcm;
	private String latprjo;
	private String feast;
	private String fnorth;
	
	public Albers() {
		this.setStrparll("");
		this.setLongcm("");
		this.setLatprjo("");
		this.setFeast("");
		this.setFnorth("");
	}

	public String getStrparll() {
		return strparll;
	}

	public void setStrparll(String strparll) {
		this.strparll = strparll;
	}

	public String getLongcm() {
		return longcm;
	}

	public void setLongcm(String longcm) {
		this.longcm = longcm;
	}

	public String getLatprjo() {
		return latprjo;
	}

	public void setLatprjo(String latprjo) {
		this.latprjo = latprjo;
	}

	public String getFeast() {
		return feast;
	}

	public void setFeast(String feast) {
		this.feast = feast;
	}

	public String getFnorth() {
		return fnorth;
	}

	public void setFnorth(String fnorth) {
		this.fnorth = fnorth;
	}
	
	public Element toElement() {
        Element albersEl = new Element("albers");

        Element strparllEl = new Element("strparll");
        strparllEl.addContent(this.strparll);
        albersEl.addContent(strparllEl);

        Element longcmEl = new Element("longcm");
        longcmEl.addContent(this.longcm);
        albersEl.addContent(longcmEl);

        Element latprjoEl = new Element("latprjo");
        latprjoEl.addContent(this.latprjo);
        albersEl.addContent(latprjoEl);

        Element feastEl = new Element("feast");
        feastEl.addContent(this.feast);
        albersEl.addContent(feastEl);

        Element fnorthEl = new Element("fnorth");
        fnorthEl.addContent(this.fnorth);
        albersEl.addContent(fnorthEl);
        
        
        return albersEl;
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
	
}
