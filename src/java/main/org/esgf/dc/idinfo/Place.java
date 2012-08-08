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
public class Place {

	private String placekt;
	private List<String> placekey;
	
	public String getPlacekt() {
		return placekt;
	}

	public void setPlacekt(String placekt) {
		this.placekt = placekt;
	}

	public List<String> getPlacekey() {
		return placekey;
	}

	public void setPlacekey(List<String> placekey) {
		this.placekey = placekey;
	}

	
	public Place() {
		this.placekey = new ArrayList<String>();
		this.placekey.add("");
		this.placekt = "";
	}
	

	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element placeEl = new Element("place");
        
        Element placektEl = new Element("placekt");
        placektEl.addContent(this.placekt);
        placeEl.addContent(placektEl);
        
        for(int i=0;i<this.placekey.size();i++) {
            Element placekeyEl = new Element("placekey");
            placekeyEl.addContent(this.placekey.get(i));
            placeEl.addContent(placekeyEl);
        }
        return placeEl;
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
		Place place = new Place();
		
		String placekt = "placekt1";
		
		List<String> placekey = new ArrayList<String>();
		placekey.add("placekey1");
		placekey.add("placekey2");
		
		place.setPlacekt(placekt);
		place.setPlacekey(placekey);
		
		place.toFile("place.xml");
		
	}
	
	
}
