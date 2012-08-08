package org.esgf.dc.eainfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <eainfo>
		<overview>
			<eaover></eaover>
			<eadetcit></eadetcit>
		</overview>
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
		</detailed>
	</eainfo>
 */

public class Eainfo {

	private Overview overview;
	private Detailed detailed;
	
	public Eainfo() {
		this.setOverview(new Overview());
		this.setDetailed(new Detailed());
	}

	public Overview getOverview() {
		return overview;
	}

	public void setOverview(Overview overview) {
		this.overview = overview;
	}

	public Detailed getDetailed() {
		return detailed;
	}

	public void setDetailed(Detailed detailed) {
		this.detailed = detailed;
	}
	
	public Element toElement() {
        Element eainfoEl = new Element("eainfo");


        eainfoEl.addContent(this.overview.toElement());
        eainfoEl.addContent(this.detailed.toElement());
        
        return eainfoEl;
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
		 Eainfo eainfo = new Eainfo();
		 
		 eainfo.toFile("eainfo.xml");
	 }
	
}
