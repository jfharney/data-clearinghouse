package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
*/
public class Stratum {

	private String stratumkt;
	private List<String> stratumkey;
	
	public String getStratumkt() {
		return stratumkt;
	}

	public void setStratumkt(String stratumkt) {
		this.stratumkt = stratumkt;
	}

	public List<String> getStratumkey() {
		return stratumkey;
	}

	public void setStratumkey(List<String> stratumkey) {
		this.stratumkey = stratumkey;
	}

	
	public Stratum() {
		this.stratumkey = new ArrayList<String>();
		this.stratumkey.add("");
		this.stratumkt = "";
	}
	

	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element stratumEl = new Element("stratum");
        
        Element stratumktEl = new Element("stratumkt");
        stratumktEl.addContent(this.stratumkt);
        stratumEl.addContent(stratumktEl);
        
        for(int i=0;i<this.stratumkey.size();i++) {
            Element stratumkeyEl = new Element("stratumkey");
            stratumkeyEl.addContent(this.stratumkey.get(i));
            stratumEl.addContent(stratumkeyEl);
        }
        return stratumEl;
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
		Stratum stratum = new Stratum();
		
		String stratumkt = "stratumkt1";
		
		List<String> stratumkey = new ArrayList<String>();
		stratumkey.add("stratumkey1");
		stratumkey.add("stratumkey2");
		
		stratum.setStratumkt(stratumkt);
		stratum.setStratumkey(stratumkey);
		
		stratum.toFile("stratum.xml");
		
	}
	
	
}
