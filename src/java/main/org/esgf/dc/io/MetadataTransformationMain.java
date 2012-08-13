package org.esgf.dc.io;
import org.esgf.dc.Dataset;


public class MetadataTransformationMain {


	public static void main(String [] args) {
		
		String datasetId = "cloud-cryo.amip.CAM5.v1%7Cpcmdi9.llnl.gov";
		SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
		
		Dataset dataset = solrRecordReader.assembleDataset();
		
		System.out.println(dataset.getId());
		
		FGDCRecordWriter fgdc = new FGDCRecordWriter();
		
		fgdc.setDataset(dataset);
		
		fgdc.writeFGDC();
		
	}
}
