package org.esgf.dc.spref;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <horizsys>
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
 */
public class Horizsys {

	private Geograph geograph;
	private Planar planar;
	private Local local;
	private Geodetic geodetic;
	
	public Horizsys() {
		this.setGeograph(new Geograph());
		this.setPlanar(new Planar());
		this.setLocal(new Local());
		this.setGeodetic(new Geodetic());
	}

	public Geograph getGeograph() {
		return geograph;
	}

	public void setGeograph(Geograph geograph) {
		this.geograph = geograph;
	}

	public Planar getPlanar() {
		return planar;
	}

	public void setPlanar(Planar planar) {
		this.planar = planar;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Geodetic getGeodetic() {
		return geodetic;
	}

	public void setGeodetic(Geodetic geodetic) {
		this.geodetic = geodetic;
	}
	
	public Element toElement() {
        Element horizsysEl = new Element("horizsys");

        horizsysEl.addContent(this.geograph.toElement());
        horizsysEl.addContent(this.planar.toElement());
        
        
        return horizsysEl;
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
