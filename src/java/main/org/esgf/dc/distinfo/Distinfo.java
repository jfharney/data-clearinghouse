package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <distinfo>
		<distrib>
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
		</distrib>
		<resdesc></resdesc>
		<distliab></distliab>
		<stdorder>
			<digform>
				<digtinfo>
					<formname></formname>
				</digtinfo>
				<digtopt>
					<onlinopt>
						<computer>
							<networka>
								<networkr></networkr>
							</networka>
						</computer>
					</onlinopt>
				</digtopt>
			</digform>
			<fees></fees>
			<ordering></ordering>
		</stdorder>
	</distinfo>
 */
public class Distinfo {

	private Distrib distrib;
	private String resdesc;
	private String distliab;
	private Stdorder stdorder;
	
	public Distinfo() {
		this.setDistrib(new Distrib());
		this.setResdesc("");
		this.setDistliab("");
		this.setStdorder(new Stdorder());
	}

	public Distrib getDistrib() {
		return distrib;
	}

	public void setDistrib(Distrib distrib) {
		this.distrib = distrib;
	}

	public String getResdesc() {
		return resdesc;
	}

	public void setResdesc(String resdesc) {
		this.resdesc = resdesc;
	}

	public String getDistliab() {
		return distliab;
	}

	public void setDistliab(String distliab) {
		this.distliab = distliab;
	}

	public Stdorder getStdorder() {
		return stdorder;
	}

	public void setStdorder(Stdorder stdorder) {
		this.stdorder = stdorder;
	}
	
	public Element toElement() {
        Element distinfoEl = new Element("distinfo");

        distinfoEl.addContent(this.distrib.toElement());
        
        Element resdescEl = new Element("resdesc");
        resdescEl.addContent(this.resdesc);
        distinfoEl.addContent(resdescEl);
        
        Element distliabEl = new Element("distliab");
        distliabEl.addContent(this.distliab);
        distinfoEl.addContent(distliabEl);

        distinfoEl.addContent(this.stdorder.toElement());
        
        return distinfoEl;
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
		 Distinfo distinfo = new Distinfo();
		 
		 distinfo.toFile("distinfo.xml");
	 }
	
	
	
	
}
