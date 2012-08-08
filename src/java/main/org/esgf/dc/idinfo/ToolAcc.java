package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * 
			<toolacc>
				<onlink></onlink>
				<toolinst></toolinst>
				<toolcomp></toolcomp>
			</toolacc>
 */
public class ToolAcc {


	private String onlink;
	private String toolinst;
	private String toolcomp;
	
	public ToolAcc() {
		this.onlink = "";
		this.toolinst = "";
		this.toolcomp = "";
	}

	public String getOnlink() {
		return onlink;
	}

	public void setOnlink(String onlink) {
		this.onlink = onlink;
	}

	public String getToolinst() {
		return toolinst;
	}

	public void setToolinst(String toolinst) {
		this.toolinst = toolinst;
	}

	public String getToolcomp() {
		return toolcomp;
	}

	public void setToolcomp(String toolcomp) {
		this.toolcomp = toolcomp;
	}
	
	public Element toElement() {
        Element toolciteEl = new Element("toolacc");

        Element onlinkEl = new Element("onlink");
        onlinkEl.addContent(this.onlink);
        toolciteEl.addContent(onlinkEl);

        Element toolinstEl = new Element("toolinst");
        toolinstEl.addContent(this.toolinst);
        toolciteEl.addContent(toolinstEl);

        Element toolcompEl = new Element("toolcomp");
        toolcompEl.addContent(this.toolcomp);
        toolciteEl.addContent(toolcompEl);
        
       
        return toolciteEl;
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
		 ToolAcc ta = new ToolAcc();
		 
		 ta.setOnlink("onlink1");
		 ta.setToolinst("toolinst1");
		 ta.setToolcomp("toolcomp1");
		 
		 ta.toFile("toolacc.xml");
	 }
	 
	 
	 
	
}
