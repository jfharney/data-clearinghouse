package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
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
*/
public class Theme {

	private String themekt;
	private List<String> themekey;
	
	public String getThemekt() {
		return themekt;
	}

	public void setThemekt(String themekt) {
		this.themekt = themekt;
	}

	public List<String> getThemekey() {
		return themekey;
	}

	public void setThemekey(List<String> themekey) {
		this.themekey = themekey;
	}

	
	public Theme() {
		this.themekey = new ArrayList<String>();
		//this.themekey.add("");
		this.themekt = "";
	}
	

	/** Description of toElement()
     * 
     * @return serialized XML element equivalent of the class
     */
    public Element toElement() {
        Element themeEl = new Element("theme");
        
        Element themektEl = new Element("themekt");
        themektEl.addContent(this.themekt);
        themeEl.addContent(themektEl);
        
        for(int i=0;i<this.themekey.size();i++) {
            Element themekeyEl = new Element("themekey");
            themekeyEl.addContent(this.themekey.get(i));
            themeEl.addContent(themekeyEl);
        }
        return themeEl;
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
		Theme theme = new Theme();
		
		String themekt = "themekt1";
		
		List<String> themekey = new ArrayList<String>();
		themekey.add("themekey1");
		themekey.add("themekey2");
		
		theme.setThemekt(themekt);
		theme.setThemekey(themekey);
		
		theme.toFile("Theme.xml");
		
	}
	
	
}
