package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <spref>
		<horizsys>
			<geograph>
				<latres></latres>
				<longres></longres>
				<geogunit></geogunit>
			</geograph>
			<planar>
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
			<local>
				<localdes></localdes>
				<localgeo></localgeo>
			</local>
			<geodetic>
				<horizdn></horizdn>
				<ellips></ellips>
				<semiaxis></semiaxis>
				<denflat></denflat>
			</geodetic>
		</horizsys>
		<vertdef>
			<altsys>
				<altenc></altenc>
				<altdatum></altdatum>
				<altunits></altunits>
				<altres></altres>
			</altsys>
			<depthsys>
				<depthdn></depthdn>
				<depthres></depthres>
				<depthdu></depthdu>
				<depthem></depthem>
			</depthsys>
		</vertdef>
	</spref>
 */
public class Spref {

	private Horizsys horizsys;
	private Vertdef vertdef;
	
	public Spref() {
		this.setHorizsys(new Horizsys());
		this.setVerfdef(new Vertdef());
	}

	public Horizsys getHorizsys() {
		return horizsys;
	}

	public void setHorizsys(Horizsys horizsys) {
		this.horizsys = horizsys;
	}

	public Vertdef getVerfdef() {
		return vertdef;
	}

	public void setVerfdef(Vertdef verfdef) {
		this.vertdef = verfdef;
	}
	
	public Element toElement() {
        Element sprefEl = new Element("spref");

        sprefEl.addContent(this.horizsys.toElement());
        
        sprefEl.addContent(this.vertdef.toElement());
        
        return sprefEl;
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
