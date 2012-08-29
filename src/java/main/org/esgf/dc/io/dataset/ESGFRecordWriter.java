package org.esgf.dc.io.dataset;
import java.util.ArrayList;
import java.util.List;

import org.esgf.dc.Metadata;
import org.esgf.dc.Model;
import org.esgf.dc.SubModel;
import org.esgf.dc.dataqual.Timeinfo;
import org.esgf.dc.distinfo.Computer;
import org.esgf.dc.distinfo.Digform;
import org.esgf.dc.distinfo.Digtinfo;
import org.esgf.dc.distinfo.Digtopt;
import org.esgf.dc.distinfo.Networka;
import org.esgf.dc.distinfo.Onlinopt;
import org.esgf.dc.distinfo.Stdorder;
import org.esgf.dc.idinfo.Bounding;
import org.esgf.dc.idinfo.Citation;
import org.esgf.dc.idinfo.CiteInfo;
import org.esgf.dc.idinfo.CntInfo;
import org.esgf.dc.idinfo.CntPerp;
import org.esgf.dc.idinfo.Cntaddr;
import org.esgf.dc.idinfo.RngDates;
import org.esgf.dc.idinfo.SpDom;
import org.esgf.dc.idinfo.Status;
import org.esgf.dc.idinfo.Theme;
import org.esgf.dc.idinfo.TimeInfo;
import org.esgf.dc.idinfo.TimePerd;
import org.esgf.dc.metainfo.Metc;



public class ESGFRecordWriter {

	private Model model;
	private Metadata metadata;
	
	public ESGFRecordWriter() {
		this.metadata = new Metadata();
		this.model = new Model();
	}
	
	public ESGFRecordWriter(Model model) {
		this.metadata = new Metadata();
		this.model = model;
	}
	

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	
	
	public void writeRecord() {
		
		System.out.println("\n\nWriting record");
		
		/*
		 * <citation>
   <citeinfo>
   	<origin>**the name of an organization or individual that developed the data set. this is a multi field</origin>
    <origin>pcmdi9.llnl.gov</origin>   
    <pubdate>20120716</pubdate>
    <title>CAM5 - Community Atmosphere Model Version 5</title>
    <onlink>http://pcmdi9.llnl.gov/esgf-web-fe/live#?model=cam5</onlink>
    <onlink>http://www.earthsystemgrid.org</onlink>  
   </citeinfo>
  </citation>
		 */
		//idinfo/citation
		Citation citation = new Citation();
		CiteInfo citeinfo = new CiteInfo();
		List<String> origin = new ArrayList<String>();
		origin.add("**the name of an organization or individual that developed the data set. this is a multi field");
		origin.add("pcmdi9.llnl.gov");  
		citeinfo.setOrigin(origin);
		citeinfo.setPubdate("20120716");
		citeinfo.setTitle("CAM5 - Community Atmosphere Model Version 5");
		List<String> onlink = new ArrayList<String>();
		onlink.add("http://pcmdi9.llnl.gov/esgf-web-fe/live#?model=cam5");
		onlink.add("http://www.earthsystemgrid.org");
		citeinfo.setOnlink(onlink);
		citation.setCiteInfo(citeinfo);
		
		//distinfo here
		
		CntInfo cntinfo = new CntInfo();
		
		CntPerp cntperp = new CntPerp();
		cntperp.setCntOrg("** please update this with the contact orgnaization (PCMDI?) and the address below");
		cntperp.setCntPer("ESGF User Services");
		
		cntinfo.setCntPerp(cntperp);
		
		cntinfo.setCntEmail("esg-support@earthsystemgrid.org");
		Cntaddr cntaddr = new Cntaddr();
		cntaddr.setAddrtype("Mailing and Physical Address");
		cntaddr.setAddress("P.O. Box 2008, MS 6407");
		cntaddr.setCity("Oak Ridge");
		cntaddr.setCity("Oak Ridge");
		cntaddr.setState("Tennessee");
		cntaddr.setPostal("37831");
		cntaddr.setCountry("USA");
		cntinfo.setCntAddr(cntaddr);
		metadata.getDistinfo().getDistrib().setCntinfo(cntinfo );
		
		//set the transfer size here
		metadata.getDistinfo().getDistrib().setTransfer_size(Integer.toString(this.model.getSubmodels().size()));
		
		
		
		//set the stdorder here
		Stdorder stdorder = new Stdorder();
		
		Digform digform = new Digform();
		Digtinfo digtinfo = new Digtinfo();
		digtinfo.setFormname("THREDDS");
		Digtopt digtopt = new Digtopt();
		Onlinopt onlinopt = new Onlinopt();
		Computer computer = new Computer();
		Networka networka = new Networka();
		networka.setNetworkr("http://pcmdi9.llnl.gov/esgf-web-fe/live#?model=cam5");
		computer.setNetworka(networka);
		onlinopt.setComputer(computer);
		digtopt.setOnlinopt(onlinopt);
		digform.setDigtinfo(digtinfo);
		digform.setDigtopt(digtopt);
		stdorder.setDigform(digform);
		stdorder.setFees("None");
		
		metadata.getDistinfo().setStdorder(stdorder);
		
		
		
		
		//set the pubdate here
		metadata.getIdinfo().getCitation().getCiteInfo().setPubdate("20120716");
		
		Theme theme = null;
		
		for(int j=0;j<this.model.getSubmodels().size();j++) {
			SubModel submodel = this.model.getSubmodels().get(j);
			
			for(String key : submodel.getMetadata().keySet()) {
				//System.out.println("Key: " + key);//submodel.getMetadata()
				
				if(key.equals("title")) {
					System.out.println("Key: " + key);//submodel.getMetadata()
					
					
					theme = new Theme();
					theme.setThemekt(key);
					

					String value = submodel.getMetadata().get(key);
					String [] values = value.split(";");
					
					for(int i=0;i<values.length;i++) {
						System.out.println("\t\tvalues: " + values[i]);
						if(!values[i].equals("") && !values[i].equals(" ")) {
							theme.getThemekey().add(values[i]);
						}
					}

					metadata.getIdinfo().getKeywords().addTheme(theme);
					
				} 
				else if(key.equals("project")) {

					System.out.println("Key: " + key);//submodel.getMetadata()
					
					
					
					theme = new Theme();
					theme.setThemekt(key);
					
					//System.out.println("\t" + submodel.getMetadata().get(key));
					

					String value = submodel.getMetadata().get(key);
					String [] values = value.split(";");
					
					for(int i=0;i<values.length;i++) {
						System.out.println("\t\tvalues: " + values[i]);
						
						if(!values[i].equals("") && !values[i].equals(" ")) {
							theme.getThemekey().add(values[i]);
						}
					}
					
					/*
					List<Theme> themes = metadata.getIdinfo().getKeywords().getTheme();
					boolean themeContained = false;
					for(int i=0;i<themes.size();i++) {
						String themekt = themes.get(i).getThemekt();
						System.out.println("themekt: " + themekt);
						if(themekt.equals("project")) {
							themeContained = true;
						}
					}

					if(!themeContained) {
						metadata.getIdinfo().getKeywords().addTheme(theme);
					} else {
						List<Theme> listThemes = metadata.getIdinfo().getKeywords().getTheme();
						for(int th=0;th<listThemes.size();th++) {
							Theme the = listThemes.get(th);
							the.getThemekey().add(value);
						}
					}
					*/
					metadata.getIdinfo().getKeywords().addTheme(theme);
					
				}
				/*
				else if(key.equals("project")) {
					
				}
				System.out.println("adding theme");	
				*/	 
			}
			
		}
		
		/*
		 * <timeperd>
   <timeinfo>
    <rngdates>
     <begdate>19960115</begdate>
     <enddate>19960115</enddate>
    </rngdates>
   </timeinfo>
   <current>publicationdate</current>
  </timeperd>
		 */
		//timeperd
		TimePerd timeperd = new TimePerd();
		TimeInfo timeinfo = new TimeInfo();
		RngDates rngdates = new RngDates();
		rngdates.setBegDate("19960115");
		rngdates.setEndDate("19960115");
		timeinfo.setRngDates(rngdates);
		timeperd.setTimeinfo(timeinfo);
		timeperd.setCurrent("publicationdate");
		
		metadata.getIdinfo().setTimeperd(timeperd);
		
		/*
		<status>
		   <progress>Complete</progress>
		   <update>As appropriate</update>
		   <Maintenance_and_Update_Frequency>none</Maintenance_and_Update_Frequency>
		  </status>
		*/
		//status
		Status status = new Status();
		status.setProgress("Complete");
		status.setUpdate("As appropriate");
		
		
		//spdom
		SpDom spdom = new SpDom();
		Bounding bounding = new Bounding();
		bounding.setEastbc("180");
		bounding.setWestbc("-180");
		bounding.setNorthbc("90");
		bounding.setSouthbc("-90");
		spdom.setBounding(bounding);
		metadata.getIdinfo().setSpdom(spdom);
		
		//set the metainfo here
		metadata.getMetainfo().setMetd("20101021");
		metadata.getMetainfo().setMetstdv("FGDC-STD-001.1-1999");
		
		
		cntaddr = new Cntaddr();
		cntaddr.setAddrtype("Mailing and Physical Address");
		cntaddr.setAddress("P.O. Box 2008, MS 6407");
		cntaddr.setCity("Oak Ridge");
		cntaddr.setCity("Oak Ridge");
		cntaddr.setState("Tennessee");
		cntaddr.setPostal("37831");
		cntaddr.setCountry("USA");
		cntinfo.setCntAddr(cntaddr);
		Metc metc = new Metc();
		metc.setCntinfo(cntinfo);
		metadata.getMetainfo().setMetc(metc);
		
		metadata.getMetainfo().setMetstdn("FGDC Content Standard for Digital Geospatial Metadata");
		
		
		
		
		
		
		
		
		
		//theme = new Theme();
		//theme.setThemekt("Experiment");
		
		
		//metadata.getIdinfo().getKeywords().setTheme(theme);
		
		metadata.toFile("CAM5.xml");
		
	}
	
	public static void main(String [] args) {
		String modelName = "CAM5";
		ESGFRecordReader esgfRecordReader = new ESGFRecordReader(modelName);
		
		Model model = esgfRecordReader.assembleModel();
		
		ESGFRecordWriter recordWriter = new ESGFRecordWriter(model);
		
		recordWriter.writeRecord();
		
		
		
	}
	
}
