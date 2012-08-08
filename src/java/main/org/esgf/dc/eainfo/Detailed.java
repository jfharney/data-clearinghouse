package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
<detailed>
<enttyp>
	<enttypl></enttypl>
	<enttypd></enttypd>
	<enttypds></enttypds>
</enttyp>
<attr>
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
<attr>
*/
public class Detailed {

	private Enttyp enttyp;

	private List<Attr> attrs;
	
	public Detailed() {
		this.setEnttyp(new Enttyp());
		this.attrs = new ArrayList<Attr>();
		Attr attr = new Attr();
		this.attrs.add(attr);
	}

	public Enttyp getEnttyp() {
		return enttyp;
	}

	public void setEnttyp(Enttyp enttyp) {
		this.enttyp = enttyp;
	}
	

	public List<Attr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attr> attrs) {
		this.attrs = attrs;
	}
	
	public void addAttr(Attr attr) {
		this.attrs.add(attr);
	}
	
	public Element toElement() {
        Element detailedEl = new Element("detailed");

        detailedEl.addContent(this.enttyp.toElement());
        
        for(int i=0;i<attrs.size();i++) {
        	detailedEl.addContent(this.attrs.get(i).toElement());
        }
        
        return detailedEl;
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
		 
		 Detailed detailed = new Detailed();
		 
		 Enttyp enttyp = new Enttyp();
		 
		 Attr attr = new Attr();
		 
		 List<Attr> attrs = new ArrayList<Attr>();
		 
		 attrs.add(attr);
		 attrs.add(attr);
		 
		 detailed.setAttrs(attrs);
		 
		 detailed.toFile("Detailed.xml");
		 
	 }
	
}
