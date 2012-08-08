package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <mapproj>
					<mapprojn></mapprojn>
					<albers>
						<stdparll></stdparll>
						<longcm></longcm>
						<latprjo></latprjo>
						<feast></feast>
						<fnorth></fnorth>
					</albers>
				</mapproj>
 */
public class Mapproj {

	private String mapprojn;
	private Albers albers;
	
	public Mapproj() {
		this.setMapprojn("");
		this.setAlbers(new Albers());
	}

	public String getMapprojn() {
		return mapprojn;
	}

	public void setMapprojn(String mapprojn) {
		this.mapprojn = mapprojn;
	}

	public Albers getAlbers() {
		return albers;
	}

	public void setAlbers(Albers albers) {
		this.albers = albers;
	}
	
	
	public Element toElement() {
        Element mapprojEl = new Element("mapproj");

        Element mapprojnEl = new Element("mapprojn");
        mapprojnEl.addContent(this.mapprojn);
        mapprojEl.addContent(mapprojnEl);
        
        mapprojEl.addContent(this.albers.toElement());
        
        return mapprojEl;
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
