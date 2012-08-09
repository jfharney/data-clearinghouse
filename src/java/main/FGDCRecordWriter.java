import java.util.ArrayList;
import java.util.List;

import org.esgf.dc.Dataset;
import org.esgf.dc.Metadata;
import org.esgf.dc.Model;
import org.esgf.dc.dataqual.Dataqual;
import org.esgf.dc.distinfo.Distinfo;
import org.esgf.dc.eainfo.Eainfo;
import org.esgf.dc.idinfo.Bounding;
import org.esgf.dc.idinfo.Citation;
import org.esgf.dc.idinfo.CiteInfo;
import org.esgf.dc.idinfo.Descript;
import org.esgf.dc.idinfo.IDInfo;
import org.esgf.dc.idinfo.Keywords;
import org.esgf.dc.idinfo.RngDates;
import org.esgf.dc.idinfo.SpDom;
import org.esgf.dc.idinfo.Status;
import org.esgf.dc.idinfo.Theme;
import org.esgf.dc.idinfo.TimeInfo;
import org.esgf.dc.idinfo.TimePerd;
import org.esgf.dc.metainfo.Metainfo;
import org.esgf.dc.spdoinfo.Spdoinfo;
import org.esgf.dc.spref.Spref;


public class FGDCRecordWriter {
	
	public static void main(String [] args) {
		String datasetId = "cloud-cryo.amip.CAM5.v1%7Cpcmdi9.llnl.gov";
		SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
		
		Dataset dataset = solrRecordReader.assembleDataset();
		
		System.out.println(dataset.getId());
		
		FGDCRecordWriter fgdc = new FGDCRecordWriter();
		
		fgdc.setDataset(dataset);
		
		fgdc.writeFGDC();
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

		System.out.println("-----Writing fgdc record-----");
		
		this.writeIdInfo();

		//write to file
		this.metadata.toFile(this.fileName);
		
		System.out.println("-----End Writing fgdc record-----");
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
		
		
		//write the resdesc tag here
		
		
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
		origins.add("pcmdi9.llnl.gov");
		citeinfo.setOrigin(origins);
		
		//set the pubdate here
		String pubdate = "20120716";
		citeinfo.setPubdate(pubdate);
		
		//set the title here
	    String title = this.dataset.getMetadata().get("title");
	    citeinfo.setTitle(title);
	    
	    //set the onlinks here
		List<String> onlink = new ArrayList<String>();
		onlink.add("http://pcmdi9.llnl.gov/esgf-web-fe/live?id=" + this.dataset.getMetadata().get("id"));
		onlink.add("pcmdi9.llnl.gov");
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
			datetime_start = "N/A";
		}
		
		String datetime_stop = this.dataset.getMetadata().get("datetime_stop");
		if(datetime_stop == null) {
			datetime_stop = "N/A";
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
				System.out.println("processing... " + key);
				
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
		//theme.setThemekt(themekt);
		
		
		idinfo.setKeywords(keywords);
		
		
		//write the taxonomy tag here
		
		
		//write the acconst tag here
		
		
		//write the useconst tag here
		
		
		//write the datacred tag here
		
		
		//write the ptcontac tag here
		
		
		//write the browse tag here
		
		
		//write the secinfo tag here
		
		
		//write the tool tag here
	
		
		metadata.setIdinfo(idinfo);
	}
	
	private void writeMetaInfo() {
		Metainfo metainfo = new Metainfo();
		
		//write the metd tag here
		
		
		//write the metc tag here
		
		
		//write the metstdn tag here
		
		
		//write the metstdv tag here
		
		
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
