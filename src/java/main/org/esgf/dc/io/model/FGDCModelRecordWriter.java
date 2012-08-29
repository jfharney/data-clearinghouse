package org.esgf.dc.io.model;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.esgf.dc.Metadata;
import org.esgf.dc.distinfo.Digform;
import org.esgf.dc.distinfo.Digtinfo;
import org.esgf.dc.distinfo.Digtopt;
import org.esgf.dc.distinfo.Distinfo;
import org.esgf.dc.distinfo.Distrib;
import org.esgf.dc.distinfo.Stdorder;
import org.esgf.dc.idinfo.Bounding;
import org.esgf.dc.idinfo.Citation;
import org.esgf.dc.idinfo.CiteInfo;
import org.esgf.dc.idinfo.CntInfo;
import org.esgf.dc.idinfo.CntPerp;
import org.esgf.dc.idinfo.Cntaddr;
import org.esgf.dc.idinfo.Descript;
import org.esgf.dc.idinfo.IDInfo;
import org.esgf.dc.idinfo.Keywords;
import org.esgf.dc.idinfo.PtContac;
import org.esgf.dc.idinfo.RngDates;
import org.esgf.dc.idinfo.SpDom;
import org.esgf.dc.idinfo.Status;
import org.esgf.dc.idinfo.Theme;
import org.esgf.dc.idinfo.TimeInfo;
import org.esgf.dc.idinfo.TimePerd;
import org.esgf.dc.utils.PropertiesReaders;

public class FGDCModelRecordWriter {

	private static String DEFAULT_TIME = "20120809";
	private static String DEFAULT_WEST_DEGREES = "-180";
	private static String DEFAULT_EAST_DEGREES = "180";
	private static String DEFAULT_SOUTH_DEGREES = "90";
	private static String DEFAULT_NORTH_DEGREES = "-90";
	
	private static String PUBDATE = "20120716";

	private static String PROPERTY_FILE_LOCATION = ".";
	
	private static String MODELMAP_PROPERTIES = "modeldescriptions.properties";
	

	private Model model;
	private Metadata metadata;
	private String fileName;
	private boolean isService;
	
	public static void main(String [] args) {
		
		
		Properties properties = new Properties();
        String propertiesFile = MODELMAP_PROPERTIES;

		
        try {
            
        	String [] names = null;
        	
            properties.load(new FileInputStream(propertiesFile));
            
            
            for(Object key : properties.keySet()) {
            	String keyStr = (String) key;
            	System.out.println("---------------\n" + keyStr);
            	String modelName = keyStr;
        		
            	
            	//modelName = "ACCESS1.3";
            	
        		SolrModelRecordReader reader = new SolrModelRecordReader(modelName);
        		reader.doQueries();
        		Model model = reader.getModel();
        		
        		FGDCModelRecordWriter fgdc = new FGDCModelRecordWriter();
        		
        		fgdc.setModel(model);

        		fgdc.setFileName("fgdc/fgdc-" + model.getModelName() + ".xml");
        		
        		fgdc.writeFGDC();
            	System.out.println("---------------");
        		
        		//System.exit(0);
            }
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        System.exit(0);
		
		

		
		
	}
	
	private List<String> getUsedKeys() {

		KeywordEnum usedKeys = null;
		
		
		List<String> usedKeysList = new ArrayList<String>();
		
		for(KeywordEnum k : usedKeys.values()) {
			usedKeysList.add(k.toString());
		}
		
		
		return usedKeysList;
	}
	
	
	public FGDCModelRecordWriter() {
		this.metadata = new Metadata();
		this.model = new Model();
		this.fileName = "fgdc.xml";
		this.isService = false;
	}
	
	public FGDCModelRecordWriter(boolean isService) {
		this.metadata = new Metadata();
		this.model = new Model();
		this.fileName = "fgdc.xml";
		this.isService = isService;
	}
	
	public void writeFGDC() {
		
		this.writeIdInfo();
		
		this.writeDistInfo();
		
		this.writeMetaInfo();

		//write to file (if not a service)
		if(!this.isService) {
			this.metadata.toFile(this.fileName);
		}
	}


	private void writeMetaInfo() {
		// TODO Auto-generated method stub
		
	}

	private void writeDistInfo() {

		Distinfo distinfo = new Distinfo();
		
		//write the distrib tag here
		Distrib distrib = new Distrib();
		
		CntInfo cntinfo = new CntInfo();
		
		CntPerp cntperp = new CntPerp();
		cntperp.setCntPer("John Harney");
		cntinfo.setCntPerp(cntperp);
		
		Cntaddr cntAddr = new Cntaddr();
		String addrtype = "";
		String address = "ORNL, P.O. Box 2008, MS 6290";
		String city = "Oak Ridge";
		String state = "Tennessee";
		String postal = "37831";
		String country = "USA";
		
		cntAddr.setAddrtype(addrtype);
		cntAddr.setAddress(address);
		cntAddr.setCity(city);
		cntAddr.setState(state);
		cntAddr.setPostal(postal);
		cntAddr.setCountry(country);
		
		cntinfo.setCntAddr(cntAddr);
		
		String cntEmail = "esgf-user@lists.llnl.gov";
		cntinfo.setCntEmail(cntEmail);
		
		distrib.setTransfer_size(Integer.toString(this.model.getTotalNumFiles()));
		distrib.setCntinfo(cntinfo);
		
		
		distinfo.setDistrib(distrib);
		
		distinfo.setDistliab("none");
		
		Stdorder stdorder = new Stdorder();
		Digform digform = new Digform();
		Digtinfo digtinfo = new Digtinfo();
		digtinfo.setFormname("THREDDS");
		
		digform.setDigtinfo(digtinfo);
		
		Digtopt digtopt = new Digtopt();
		digtopt.getOnlinopt().getComputer().getNetworka().setNetworkr("http://esg.ccs.ornl.gov/esgf-web-fe/live?search=true&model=" + this.model.getModelName());
		digform.setDigtopt(digtopt);
		
		stdorder.setDigform(digform);
		
		distinfo.setStdorder(stdorder);
		
		
		
		//write the resdesc tag here
		String resdesc = "The Earth System Grid Federation (ESGF) is " +
				"an international collaboration with a current focus on " +
				"serving the World Climate Research Programme's (WCRP) " +
				"Coupled Model Intercomparison Project (CMIP) and supporting " +
				"climate and environmental science in general. The ESGF grew " +
				"out of the larger Global Organization for Earth System Science " +
				"Portals (GO-ESSP) community, and reflects a broad array of contributions " +
				"from the collaborating partners listed below. More info about ESGF is " +
				"available at http://www.earthsystemgrid.org/about/overview.htm";
		distinfo.setResdesc(resdesc);
		
		
		//write the distliab tag here
		
		
		//write the stdorder tag here
		
		metadata.setDistinfo(distinfo);
		
	}

	private void writeIdInfo() {

		IDInfo idinfo = new IDInfo();
		//write the citation tag here
		Citation citation = new Citation();
		
		CiteInfo citeinfo = new CiteInfo();

		//set the origins here
		List<String> origins = new ArrayList<String>();
		origins.add(PropertiesReaders.getModelPI(this.model.getModelName()));
		//origins.add(PropertiesReaders.getModelOrganization(this.model.getModelName()));
		//origins.add("esg.ccs.ornl.gov");
		citeinfo.setOrigin(origins);

		citeinfo.setGeoform("Model Data");
		
		//set the pubdate here
		String pubdate = PUBDATE;
		citeinfo.setPubdate(pubdate);

		//set the title here
	    String title = PropertiesReaders.getModelTitle(this.model.getModelName());//this.model.getModelName();
	    citeinfo.setTitle(title);
		
		
	    //set the onlinks here
		List<String> onlink = new ArrayList<String>();
		onlink.add("http://esg.ccs.ornl.gov/esgf-web-fe/live?search=true&model=" + this.model.getModelName());
		citeinfo.setOnlink(onlink);
		

		citation.setCiteInfo(citeinfo);
		
		idinfo.setCitation(citation);
		
		//List<String> descriptions = this.model.getMetadata().get("description");
		
		//write the descript tag here
		Descript descript = new Descript();
		
		
		String abstractD = PropertiesReaders.getModelDescription(this.model.getModelName()) + ".  Data is currently archived and distributed by Earth System Grid Federation (ESGF)";//PropertiesReaders.getModelDescription(this.model.getModelName());
		
		String supplinf = PropertiesReaders.getModelWebURL(this.model.getModelName());
		
		descript.setSupplinf(supplinf);
		
		descript.setAbstractD(abstractD);
		
		idinfo.setDescript(descript);
		

		//write the timeperd tag here
		TimePerd timeperd = new TimePerd();
		timeperd.setCurrent("publicationdate");
		TimeInfo timeinfo = new TimeInfo();
		
		List<String> startTimes = this.model.getMetadata().get("datetime_start");
		String datetime_start = null;
		if(startTimes == null) {
			datetime_start = DEFAULT_TIME;
		} else {
			datetime_start = startTimes.get(0);//this.dataset.getMetadata().get("datetime_start");
		}
		
		List<String> stopTimes = this.model.getMetadata().get("datetime_stop");
		String datetime_stop = null;
		if(stopTimes == null) {
			datetime_stop = DEFAULT_TIME;
		} else {
			datetime_stop = stopTimes.get(0);//this.dataset.getMetadata().get("datetime_start");
		}
		
		
		RngDates rngdates = new RngDates();
		
		rngdates.setBegDate(datetime_start);
		rngdates.setEndDate(datetime_stop);
		timeinfo.setRngDates(rngdates);
		timeperd.setTimeinfo(timeinfo);
		
		idinfo.setTimeperd(timeperd);
		
		//write the status tag here
		Status status = new Status();
		status.setProgress("Complete");
		status.setUpdate("As appropriate");
		
		idinfo.setStatus(status);
				
		//write the spdom tag here
		SpDom spdom = new SpDom();
		Bounding bounding = new Bounding();

		List<String> west_degreeList = this.model.getMetadata().get("west_degrees");
		
		String west_degrees = null;
		if(west_degreeList == null) {
			west_degrees = DEFAULT_WEST_DEGREES;
		} else {
			west_degrees = west_degreeList.get(0);
		}
		
		List<String> east_degreeList = this.model.getMetadata().get("east_degrees");

		String east_degrees = null;
		if(east_degreeList == null) {
			east_degrees = DEFAULT_EAST_DEGREES;
		} else {
			east_degrees = west_degreeList.get(0);
		}
		
		
		List<String> north_degreeList = this.model.getMetadata().get("north_degrees");

		String north_degrees = null;
		if(north_degreeList == null) {
			north_degrees = DEFAULT_NORTH_DEGREES;
		} else {
			north_degrees = north_degreeList.get(0);
		}
		
		
		List<String> south_degreeList = this.model.getMetadata().get("south_degrees");

		String south_degrees = null;
		if(south_degreeList == null) {
			south_degrees = DEFAULT_SOUTH_DEGREES;
		} else {
			south_degrees = south_degreeList.get(0);
		}
		

		bounding.setWestbc(west_degrees);
		bounding.setEastbc(east_degrees);
		bounding.setNorthbc(north_degrees);
		bounding.setSouthbc(south_degrees);
		spdom.setBounding(bounding);
		
		idinfo.setSpdom(spdom);
		
		//write the keywords tag here
		Keywords keywords = new Keywords();
		
		
		List<String> usedKeys = getUsedKeys();
		
		
		
		for(String key : this.model.getMetadata().keySet()) {
			Theme theme = new Theme();
			
			if(usedKeys.contains(key)) {

				String themekt = key;
				
				List<String> themekeys = this.model.getMetadata().get(key);

				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);
				keywords.addTheme(theme);
				
				usedKeys.remove(key);
				
			}
		
		}
		
		//individual processing of the "institute" key
		String key = "institute";
		Theme instituteTheme = new Theme();
		String instituteThemekt = key;
		List<String> instituteThemekeyStrings = new ArrayList<String>();
		String instituteThemekey = PropertiesReaders.getModelInstitute(this.model.getModelName());
		
		instituteThemekeyStrings.add(instituteThemekey);
		
		instituteTheme.setThemekt(instituteThemekt);
		instituteTheme.setThemekey(instituteThemekeyStrings);
		
		keywords.addTheme(instituteTheme);
		
		
		for(int i=0;i<usedKeys.size();i++) {

			Theme theme = new Theme();
			
			theme.setThemekt(usedKeys.get(i));
			
			
			keywords.addTheme(theme);
		}
		idinfo.setKeywords(keywords);
		
		//write the taxonomy tag here
		
		
		//write the acconst tag here
		String acconst = "none";
		idinfo.setAcconst(acconst);
		
		//write the useconst tag here
		String useconst = "none";
		idinfo.setUseconst(useconst);
			
		
		//write the datacred tag here
		
		
		//write the ptcontac tag here
		PtContac ptcontac = new PtContac();
		CntInfo cntinfo = new CntInfo();
		
		CntPerp cntperp = new CntPerp();
		cntperp.setCntPer("John Harney");
		cntinfo.setCntPerp(cntperp);
		
		Cntaddr cntAddr = new Cntaddr();
		String addrtype = "";
		String address = "ORNL, P.O. Box 2008, MS 6290";
		String city = "Oak Ridge";
		String state = "Tennessee";
		String postal = "37831";
		String country = "USA";
				
		cntAddr.setAddrtype(addrtype);
		cntAddr.setAddress(address);
		cntAddr.setCity(city);
		cntAddr.setState(state);
		cntAddr.setPostal(postal);
		cntAddr.setCountry(country);
		
		cntinfo.setCntAddr(cntAddr);
		
		String cntEmail = "esgf-user@lists.llnl.gov";
		cntinfo.setCntEmail(cntEmail);
		
		ptcontac.setCntInfo(cntinfo);
		
		idinfo.setPtcontac(ptcontac);
		
		//write the browse tag here
		
		
		//write the secinfo tag here
		
		
		//write the tool tag here
		
		metadata.setIdinfo(idinfo);
		
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



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
