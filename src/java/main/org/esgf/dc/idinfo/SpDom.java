package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
<spdom>
<descgeog></descgeog>
<bounding>
	<westbc></westbc>
	<eastbc></eastbc>
	<northbc></northbc>
	<southbc></southbc>
</bounding>
</spdom>
*/

public class SpDom {

	

	private String descgeog;
	private Bounding bounding;
	
	public SpDom() {
		this.descgeog = "";
		this.bounding = new Bounding();
	}
	
	public String getDescgeog() {
		return descgeog;
	}

	public void setDescgeog(String descgeog) {
		this.descgeog = descgeog;
	}

	public Bounding getBounding() {
		return bounding;
	}

	public void setBounding(Bounding bounding) {
		this.bounding = bounding;
	}
	
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element spdomEl = new Element("spdom");
        

        Element descgeogEl = new Element("descgeog");
        descgeogEl.addContent(this.descgeog);     
        spdomEl.addContent(descgeogEl);
        
        Element boundingEl = this.bounding.toElement();
        spdomEl.addContent(boundingEl);
        
        return spdomEl;
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
    	SpDom sp = new SpDom();
    	
    	sp.setDescgeog("descgeog1");
    	
    	Bounding b = new Bounding();
    	
    	b.setEastbc("eastbc1");
    	b.setNorthbc("northbc1");
    	b.setSouthbc("southbc1");
    	b.setWestbc("westbc1");
    	
    	sp.setBounding(b);
    	
    	sp.toFile("SpDom.xml");
    	
    }
    
}
