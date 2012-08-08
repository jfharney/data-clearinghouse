package org.esgf.dc.dataqual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.esgf.dc.idinfo.XmlFormatter;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/*
 * <dataqual>
		<attracc>
			<attraccr></attraccr>
			<qattracc>
				<attraccv></attraccv>
				<attracce></attracce>
			</qattracc>
		</attracc>
		<logic></logic>
		<complete></complete>
		<posacc>
			<horizpa>
				<horizpar></horizpar>
			</horizpa>
		</posacc>
		<lineage>
			<method>
				<methtype></methtype>
				<methdesc></methdesc>
				<methcite>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
						<onlink></onlink>
					</citeinfo>
				</methcite>
			</method>
			<srcinfo>
				<srccite>
					<citeinfo>
						<origin></origin>
						<pubdate></pubdate>
						<title></title>
						<geoform></geoform>
						<onlink></onlink>
					</citeinfo>
				</srccite>
				<srcscale></srcscale>
				<typesrc></typesrc>
				<srctime>
					<timeinfo>
						<sngdate>
							<caldate></caldate>
						</sngdate>
					</timeinfo>
					<srccurr></srccurr>
				</srctime>
				<srccitea></srccitea>
				<srccontr></srccontr>
			</srcinfo>
			<procstep>
				<procdesc></procdesc>
				<srcused></srcused>
				<procdate></procdate>
			</procstep>
		</lineage>
		<cloud></cloud>
	</dataqual>
 */
public class Dataqual {

	private Attrac attrac;
	private String logic;
	private String complete;
	private Posacc posacc;
	private Lineage lineage;
	private String cloud;
	
	public Dataqual() {
		this.attrac = new Attrac();
		this.logic = "";
		this.complete = "";
		this.posacc = new Posacc();
		this.lineage = new Lineage();
		this.cloud = "";
	}
	
	public Attrac getAttrac() {
		return attrac;
	}

	public void setAttrac(Attrac attrac) {
		this.attrac = attrac;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Posacc getPosacc() {
		return posacc;
	}

	public void setPosacc(Posacc posacc) {
		this.posacc = posacc;
	}

	public Lineage getLineage() {
		return lineage;
	}

	public void setLineage(Lineage lineage) {
		this.lineage = lineage;
	}

	public String getCloud() {
		return cloud;
	}

	public void setCloud(String cloud) {
		this.cloud = cloud;
	}

	
	public Element toElement() {
        Element dataqualEl = new Element("dataqual");


        dataqualEl.addContent(this.attrac.toElement());
        
        Element logicEl = new Element("logic");
        logicEl.addContent(this.logic);
        dataqualEl.addContent(logicEl);
        
        Element completeEl = new Element("complete");
        completeEl.addContent(this.complete);
        dataqualEl.addContent(completeEl);
        
        dataqualEl.addContent(this.posacc.toElement());

        dataqualEl.addContent(this.lineage.toElement());
        
        Element cloudEl = new Element("cloud");
        cloudEl.addContent(this.cloud);
        dataqualEl.addContent(cloudEl);
        
        return dataqualEl;
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
		 
	 }
	
	
	
}
