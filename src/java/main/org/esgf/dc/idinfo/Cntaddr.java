package org.esgf.dc.idinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/*
 * <cntaddr>
					<addrtype>mailing and physical</addrtype>
					<address></address>
					<city></city>
					<state></state>
					<postal></postal>
					<country></country>
				</cntaddr>
 */
public class Cntaddr {


	private String addrtype;
	private String address;
	private String city;
	private String state;
	private String postal;
	private String country;
	
	public Cntaddr() {
		this.addrtype = "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.postal = "";
		this.country = "";
	}
	

	public String getAddrtype() {
		return addrtype;
	}

	public void setAddrtype(String addrtype) {
		this.addrtype = addrtype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

	public Element toElement() {
        Element cntaddrEl = new Element("cntaddr");

        Element addrtypeEl = new Element("addrtype");
        addrtypeEl.addContent(this.addrtype);
        cntaddrEl.addContent(addrtypeEl);

        Element addressEl = new Element("address");
        addressEl.addContent(this.address);
        cntaddrEl.addContent(addressEl);
        
        Element cityEl = new Element("city");
        cityEl.addContent(this.city);
        cntaddrEl.addContent(cityEl);

        Element stateEl = new Element("state");
        stateEl.addContent(this.state);
        cntaddrEl.addContent(stateEl);

        Element postalEl = new Element("postal");
        postalEl.addContent(this.postal);
        cntaddrEl.addContent(postalEl);

        Element countryEl = new Element("country");
        countryEl.addContent(this.country);
        cntaddrEl.addContent(countryEl);
        
        return cntaddrEl;
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
		 Cntaddr c = new Cntaddr();
		 
		 c.setAddress("address1");
		 c.setAddrtype("addrtype1");
		 c.setCity("city1");
		 c.setCountry("country1");
		 c.setPostal("postal1");
		 c.setState("state1");
		 
		 c.toFile("cntaddr.xml");
		 
	 }
	
}
