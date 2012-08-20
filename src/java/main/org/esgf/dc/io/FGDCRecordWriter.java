package org.esgf.dc.io;
import java.util.ArrayList;
import java.util.List;

import org.esgf.dc.Dataset;
import org.esgf.dc.Metadata;
import org.esgf.dc.Model;
import org.esgf.dc.dataqual.Dataqual;
import org.esgf.dc.distinfo.Digform;
import org.esgf.dc.distinfo.Digtinfo;
import org.esgf.dc.distinfo.Digtopt;
import org.esgf.dc.distinfo.Distinfo;
import org.esgf.dc.distinfo.Distrib;
import org.esgf.dc.distinfo.Stdorder;
import org.esgf.dc.eainfo.Eainfo;
import org.esgf.dc.idinfo.Bounding;
import org.esgf.dc.idinfo.Citation;
import org.esgf.dc.idinfo.CiteInfo;
import org.esgf.dc.idinfo.CntInfo;
import org.esgf.dc.idinfo.CntPerp;
import org.esgf.dc.idinfo.Cntaddr;
import org.esgf.dc.idinfo.Cntorgp;
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
import org.esgf.dc.metainfo.Metainfo;
import org.esgf.dc.metainfo.Metc;
import org.esgf.dc.spdoinfo.Spdoinfo;
import org.esgf.dc.spref.Spref;


public class FGDCRecordWriter {
	
	private static String DEFAULT_TIME = "20120809";
	
	public static void main(String [] args) {
		//String datasetId = "cloud-cryo.amip.CAM5.v1%7Cpcmdi9.llnl.gov";
		
		/*
		 * cmip5.output1.INM.inmcm4.1pctCO2.day.atmos.day.r1i1p1.v20110323|pcmdi9.llnl.gov
		 * cmip5.output1.NCAR.CCSM4.1pctCO2.day.atmos.cfDay.r2i1p1.v20120717|tds.ucar.edu
		 * cmip5.output1.CSIRO-BOM.ACCESS1-3.1pctCO2.mon.aerosol.aero.r1i1p1.v20120508|esgnode1.nci.org.au
		 * cmip5.output1.NASA-GMAO.GEOS-5.decadal1960.mon.atmos.Amon.r2i1p1.v20120515|esgdata1.nccs.nasa.gov
		 * cmip5.output1.NCC.NorESM1-ME.1pctCO2.mon.atmos.Amon.r1i1p1.v20120402|norstore-trd-bio1.hpc.ntnu.no
		 */
		
		List<String> datasetIds = new ArrayList<String>();
		
		/*
		datasetIds.add("cmip5.output1.NCAR.CCSM4.1pctCO2.day.atmos.cfDay.r2i1p1.v20120717%7Ctds.ucar.edu");
		datasetIds.add("cmip5.output1.INM.inmcm4.1pctCO2.day.atmos.day.r1i1p1.v20110323%7Cpcmdi9.llnl.gov");
		datasetIds.add("cmip5.output1.CSIRO-BOM.ACCESS1-3.1pctCO2.mon.aerosol.aero.r1i1p1.v20120508%7Cesgnode1.nci.org.au");
		datasetIds.add("cmip5.output1.NASA-GMAO.GEOS-5.decadal1960.mon.atmos.Amon.r2i1p1.v20120515%7Cesgdata1.nccs.nasa.gov");
		datasetIds.add("cmip5.output1.NCC.NorESM1-ME.1pctCO2.mon.atmos.Amon.r1i1p1.v20120402%7Cnorstore-trd-bio1.hpc.ntnu.no");
		*/
		datasetIds.add("cmip5.output1.CSIRO-BOM.ACCESS1-3.1pctCO2.day.atmos.day.r1i1p1.v1|esgnode2.nci.org.au");
		
		for(int i=0;i<datasetIds.size();i++) {
			
			String datasetId = datasetIds.get(i);
			
			SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
			
			Dataset dataset = solrRecordReader.assembleDataset();
			
			System.out.println(dataset.getId());
			
			FGDCRecordWriter fgdc = new FGDCRecordWriter();
			
			fgdc.setFileName("fgdc-" + datasetId + ".xml");
			
			fgdc.setDataset(dataset);
			
			fgdc.writeFGDC();
		}
		
	}
	

	private Dataset dataset;
	private Metadata metadata;
	private String fileName;
	
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


	
	public FGDCRecordWriter() {
		this.metadata = new Metadata();
		this.fileName = "fgdc.xml";
	}
	
	
	
	public void writeFGDC() {

		//System.out.println("-----Writing fgdc record-----");
		
		this.writeIdInfo();

		this.writeDistInfo();
		
		this.writeMetaInfo();
		//write to file
		this.metadata.toFile(this.fileName);
		
		//System.out.println("-----End Writing fgdc record-----");
	}
	
	private void writeDataual() {
		Dataqual dataqual = new Dataqual();
		
		//write the attracc tag here
		
		
		//write the logic tag here
		
		
		//write the complete tag here
		
		
		//write the posacc tag here
		
		
		//write the lineage tag here
		
		
		//write the cloud tag here

		metadata.setDataqual(dataqual);
	}
	
	private void writeDistInfo() {
		Distinfo distinfo = new Distinfo();
		
		//write the distrib tag here
		Distrib distrib = new Distrib();
		
		CntInfo cntinfo = new CntInfo();
		
		CntPerp cntperp = new CntPerp();
		cntperp.setCntPer("ESG Users Service");
		cntinfo.setCntPerp(cntperp);
		
		Cntaddr cntAddr = new Cntaddr();
		String addrtype = "Mailing and Physical Address (**please update the address below)";
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
		
		String cntEmail = "esg-support@earthsystemgrid.org";
		cntinfo.setCntEmail(cntEmail);
		
		distrib.setTransfer_size(this.dataset.getMetadata().get("number_of_files"));
		distrib.setCntinfo(cntinfo);
		
		
		distinfo.setDistrib(distrib);
		
		distinfo.setDistliab("none");
		
		Stdorder stdorder = new Stdorder();
		Digform digform = new Digform();
		Digtinfo digtinfo = new Digtinfo();
		digtinfo.setFormname("THREDDS");
		
		digform.setDigtinfo(digtinfo);
		
		Digtopt digtopt = new Digtopt();
		digtopt.getOnlinopt().getComputer().getNetworka().setNetworkr("http://esg.ccs.ornl.gov/esgf-web-fe/live?search=true&datacart=true&id=" + this.dataset.getMetadata().get("id"));
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
				"from the collaborating partners listed below. More info about ESF is " +
				"available at http://www.earthsystemgrid.org/about/overview.htm";
		distinfo.setResdesc(resdesc);
		
		
		//write the distliab tag here
		
		
		//write the stdorder tag here
		
		metadata.setDistinfo(distinfo);
	}
	
	private void writeEaInfo() {
		Eainfo eainfo = new Eainfo();
		
		//write the overview tag here
		
		
		//write the detailed tag here
		
		
		metadata.setEainfo(eainfo);
	}
	
	private void writeIdInfo() {
		IDInfo idinfo = new IDInfo();
		
		//write the citation tag here
		Citation citation = new Citation();
		
		CiteInfo citeinfo = new CiteInfo();
		
		//set the origins here
		List<String> origins = new ArrayList<String>();
		origins.add("**the name of an organization or individual that developed the data set. this is a multi field");
		origins.add("esg.ccs.ornl.gov");
		citeinfo.setOrigin(origins);
		
		//set the pubdate here
		String pubdate = "20120716";
		citeinfo.setPubdate(pubdate);
		
		//set the title here
	    String title = this.dataset.getMetadata().get("title");
	    citeinfo.setTitle(title);
	    
	    //set the onlinks here
		List<String> onlink = new ArrayList<String>();
		onlink.add("http://esg.ccs.ornl.gov/esgf-web-fe/live?search=true&datacart=true&id=" + this.dataset.getMetadata().get("id"));
		onlink.add("esg.ccs.ornl.gov");
	    citeinfo.setOnlink(onlink);
	    
		citation.setCiteInfo(citeinfo);
		
		idinfo.setCitation(citation);
		
		//write the descript tag here
		Descript descript = new Descript();
		String abstractD = this.dataset.getMetadata().get("description");
		if(abstractD == null) {
			abstractD = "N/A";
		}
		
		descript.setAbstractD(abstractD);
		
		idinfo.setDescript(descript);
		
		//write the timeperd tag here
		TimePerd timeperd = new TimePerd();
		timeperd.setCurrent("publicationdate");
		TimeInfo timeinfo = new TimeInfo();

		String datetime_start = this.dataset.getMetadata().get("datetime_start");
		if(datetime_start == null) {
			datetime_start = DEFAULT_TIME;
		}
		
		String datetime_stop = this.dataset.getMetadata().get("datetime_stop");
		if(datetime_stop == null) {
			datetime_stop = DEFAULT_TIME;
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
		
		String west_degrees = this.dataset.getMetadata().get("west_degrees");
		String east_degrees = this.dataset.getMetadata().get("east_degrees");
		String north_degrees = this.dataset.getMetadata().get("north_degrees");
		String south_degrees = this.dataset.getMetadata().get("south_degrees");

		
		if(west_degrees == null &&
		   east_degrees == null && 
		   north_degrees == null &&
		   south_degrees == null) {
			west_degrees = "-180";
			east_degrees = "180";
			north_degrees = "90";
			south_degrees = "-90";
		}
		
		bounding.setWestbc(west_degrees);
		bounding.setEastbc(east_degrees);
		bounding.setNorthbc(north_degrees);
		bounding.setSouthbc(south_degrees);
		spdom.setBounding(bounding);
		
		idinfo.setSpdom(spdom);
		
		
		//write the keywords tag here
		Keywords keywords = new Keywords();
		
		
		for(String key : this.dataset.getMetadata().keySet()) {
			

			Theme theme = new Theme();
			
			//System.out.println("Key: " + key + " value: " + this.dataset.getMetadata().get(key));
			if(key.equalsIgnoreCase("experiment")) {
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);
				keywords.addTheme(theme);
			} else if(key.equalsIgnoreCase("realm")) {
			
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);
				keywords.addTheme(theme);
				
			} else if(key.equalsIgnoreCase("model")) {
			
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);
				keywords.addTheme(theme);
				
			} else if(key.equalsIgnoreCase("experiment_family")) {
			
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);
				keywords.addTheme(theme);
				
			} else if(key.equalsIgnoreCase("project")) {
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);

				keywords.addTheme(theme);
			} else if(key.equalsIgnoreCase("dataset_title")) {
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);

				keywords.addTheme(theme);
			} else if(key.equalsIgnoreCase("cf_standard_name")) {
			
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);

				keywords.addTheme(theme);
				
			} else if(key.equalsIgnoreCase("variable")) {
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);

				keywords.addTheme(theme);
			} else if(key.equalsIgnoreCase("variable_long_name")) {
				String themekt = key;
				
				List<String> themekeys = new ArrayList<String>();
				String [] values = this.dataset.getMetadata().get(key).split(";");
				for(int i=0;i<values.length;i++) {
					themekeys.add(values[i]);
				}
				
				theme.setThemekt(themekt);
				theme.setThemekey(themekeys);

				keywords.addTheme(theme);
			}

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
		cntperp.setCntPer("ESG Users Service");
		cntinfo.setCntPerp(cntperp);
		
		Cntaddr cntAddr = new Cntaddr();
		String addrtype = "Mailing and Physical Address (**please update the address below)";
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
		
		String cntEmail = "esg-support@earthsystemgrid.org";
		cntinfo.setCntEmail(cntEmail);
		
		
		ptcontac.setCntInfo(cntinfo);
		
		idinfo.setPtcontac(ptcontac);
		
		//write the browse tag here
		
		
		//write the secinfo tag here
		
		
		//write the tool tag here
	
		
		metadata.setIdinfo(idinfo);
	}
	
	private void writeMetaInfo() {
		Metainfo metainfo = new Metainfo();
		
		//write the metd tag here'
		String metd = "20101021";
		metainfo.setMetd(metd);
		
		//write the metc tag here
		Metc metc = new Metc();
		CntInfo cntinfo = new CntInfo();
		
		Cntorgp cntorgp = new Cntorgp();
		String cntorg = "Earth System Grid Federation";
		String cntper = "John Harney";
		cntorgp.setCntorg(cntorg);
		cntorgp.setCntper(cntper);
		
		Cntaddr cntAddr = new Cntaddr();
		String addrtype = "Mailing and Physical Address ";
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
		
		
		String cntVoice = "xxx-xxx-xxxx";
		cntinfo.setCntVoice(cntVoice);
		
		String cntEmail = "xxxxxxxx@xxx.xxx";
		cntinfo.setCntEmail(cntEmail);
		
		metc.setCntinfo(cntinfo);
		metainfo.setMetc(metc);
		
		//write the metstdn tag here
		String metstdn = "FGDC Content Standard for Digital Geospatial Metadata";
		metainfo.setMetstdn(metstdn);
		
		//write the metstdv tag here
		String metstdv = "FGDC-STD-001.1-1999";
		metainfo.setMetstdv(metstdv);
		
		//write the metac tag here
		
		
		//write the metuc tag here
		
		metadata.setMetainfo(metainfo);
	}
	
	private void writeSpdoInfo() {
		Spdoinfo spdoinfo = new Spdoinfo();
		
		//write the indspref tag here
		
		
		//write the direct tag here
		
		
		metadata.setSpdoinfo(spdoinfo);
	}
	
	private void writeSpref() {
		Spref spref = new Spref();
		
		//write the horizsys tag here
		
		
		//write the vertdef tag here
		
		metadata.setSpref(spref);
	}



	public Dataset getDataset() {
		return dataset;
	}



	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
	
	
	
}
