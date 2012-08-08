package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <keywords>
			<theme>
				<themekt></themekt>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
				<themekey></themekey>
			</theme>
			<place>
				<placekt></placekt>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
				<placekey></placekey>
			</place>
			<stratum>
				<stratkt></stratkt>
				<stratkey></stratkey>
			</stratum>
			<temporal>
				<tempkt></tempkt>
				<tempkey></tempkey>
			</temporal>

		</keywords>
 */
public class Keywords {

	private List<Theme> theme;
	private Place place;
	private Stratum stratum;
	private Temporal temporal;
	
	public Keywords() {
		this.theme = new ArrayList<Theme>();
		this.place = new Place();
		this.stratum = new Stratum();
		this.temporal = new Temporal();
	}
	
	
	public List<Theme> getTheme() {
		return theme;
	}

	public void setTheme(List<Theme> theme) {
		this.theme = theme;
	}
	
	public void addTheme(Theme theme) {
		this.theme.add(theme);
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Stratum getStratum() {
		return stratum;
	}

	public void setStratum(Stratum stratum) {
		this.stratum = stratum;
	}

	public Temporal getTemporal() {
		return temporal;
	}

	public void setTemporal(Temporal temporal) {
		this.temporal = temporal;
	}
	
	
	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element keywordsEl = new Element("keywords");

        for(int i=0;i<this.theme.size();i++) {
            keywordsEl.addContent(this.theme.get(i).toElement());
        }
        keywordsEl.addContent(this.place.toElement());
        keywordsEl.addContent(this.stratum.toElement());
        keywordsEl.addContent(this.temporal.toElement());
        
        return keywordsEl;
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
    	Keywords keywords = new Keywords();
    	
    	Place place = new Place();
		
		String placekt = "placekt1";
		
		List<String> placekey = new ArrayList<String>();
		placekey.add("placekey1");
		placekey.add("placekey2");
		
		place.setPlacekt(placekt);
		place.setPlacekey(placekey);
		
    	keywords.setPlace(place);
    	
    	
    	
    	Theme theme = new Theme();
		
		String themekt = "themekt1";
		
		List<String> themekey = new ArrayList<String>();
		themekey.add("themekey1");
		themekey.add("themekey2");
		
		theme.setThemekt(themekt);
		theme.setThemekey(themekey);
		
		keywords.addTheme(theme);
		
		
Stratum stratum = new Stratum();
		
		String stratumkt = "stratumkt1";
		
		List<String> stratumkey = new ArrayList<String>();
		stratumkey.add("stratumkey1");
		stratumkey.add("stratumkey2");
		
		stratum.setStratumkt(stratumkt);
		stratum.setStratumkey(stratumkey);
		
		keywords.setStratum(stratum);
		
		
Temporal temporal = new Temporal();
		
		String temporalkt = "temporalkt1";
		
		List<String> temporalkey = new ArrayList<String>();
		temporalkey.add("temporalkey1");
		temporalkey.add("temporalkey2");
		
		temporal.setTemporalkt(temporalkt);
		temporal.setTemporalkey(temporalkey);
		
		
		keywords.setTemporal(temporal);
		
		keywords.toFile("keywords.xml");
		
		
    	
    	
    }
    
    
}
