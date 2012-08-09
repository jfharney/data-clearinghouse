import org.esgf.dc.Dataset;
import org.esgf.dc.Metadata;
import org.esgf.dc.Model;
import org.esgf.dc.dataqual.Dataqual;
import org.esgf.dc.distinfo.Distinfo;
import org.esgf.dc.eainfo.Eainfo;
import org.esgf.dc.idinfo.IDInfo;
import org.esgf.dc.metainfo.Metainfo;
import org.esgf.dc.spdoinfo.Spdoinfo;
import org.esgf.dc.spref.Spref;


public class FGDCRecordWriter {
	
	public static void main(String [] args) {
		FGDCRecordWriter fgdc = new FGDCRecordWriter();
		
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
		
		
		//write the descript tag here
		
		
		//write the timeperd tag here
		
		
		//write the status tag here
		
		
		//write the spdom tag here
		
		
		//write the keywords tag here
		
		
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