package org.esgf.dc.distinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
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
 */
public class Stdorder {

	private Digform digform;
	private String fees;
	private String ordering;
	
	public Stdorder() {
		this.setDigform(new Digform());
		this.setFees("");
		this.setOrdering("");
	}

	public Digform getDigform() {
		return digform;
	}

	public void setDigform(Digform digform) {
		this.digform = digform;
	}


	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	
	
	public Element toElement() {
        Element stdorderEl = new Element("stdorder");

        stdorderEl.addContent(this.digform.toElement());
        
        Element feesEl = new Element("fees");
        feesEl.addContent(this.fees);
        stdorderEl.addContent(feesEl);

        Element orderingEl = new Element("ordering");
        orderingEl.addContent(this.ordering);
        stdorderEl.addContent(orderingEl);
        
        
        
        return stdorderEl;
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
