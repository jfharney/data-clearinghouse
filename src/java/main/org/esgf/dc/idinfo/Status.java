package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <status>
			<progress></progress>
			<update></update>
		</status>
 */
public class Status {

	private String progress;
	private String update;
	
	public Status() {
		this.progress = "";
		this.update = "";
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element statusEl = new Element("status");
        

        Element progressEl = new Element("progress");
        progressEl.addContent(this.progress);
        statusEl.addContent(progressEl);

        Element updateEl = new Element("update");
        updateEl.addContent(this.update);
        statusEl.addContent(updateEl);
        
        return statusEl;
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
		 Status s = new Status();
		 
		 s.setProgress("progress1");
		 s.setUpdate("update1");
		 
		 s.toFile("Status.xml");
	 }
	
}
