package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <planar>
				<mapproj>
					<mapprojn></mapprojn>
					<albers>
						<stdparll></stdparll>
						<longcm></longcm>
						<latprjo></latprjo>
						<feast></feast>
						<fnorth></fnorth>
					</albers>
				</mapproj>
				<gridsys>
					<gridsysn></gridsysn>
				</gridsys>
				<localp>
					<localpd></localpd>
					<localpgi></localpgi>
				</localp>
			</planar>
 */
public class Planar {

	private Mapproj mapproj;
	private Gridsys gridsys;
	private Localp localp;
	
	public Planar() {
		this.setMapproj(new Mapproj());
		this.setGridsys(new Gridsys());
		this.setLocalp(new Localp());
	}

	public Mapproj getMapproj() {
		return mapproj;
	}

	public void setMapproj(Mapproj mapproj) {
		this.mapproj = mapproj;
	}

	public Gridsys getGridsys() {
		return gridsys;
	}

	public void setGridsys(Gridsys gridsys) {
		this.gridsys = gridsys;
	}

	public Localp getLocalp() {
		return localp;
	}

	public void setLocalp(Localp localp) {
		this.localp = localp;
	}
	
	public Element toElement() {
        Element planarEl = new Element("planar");

        
        planarEl.addContent(this.mapproj.toElement());
        
        return planarEl;
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
