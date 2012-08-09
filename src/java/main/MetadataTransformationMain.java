import org.esgf.dc.Dataset;


public class MetadataTransformationMain {


	public static void main(String [] args) {
		
		
		String datasetId = "cmip5.output1.CMCC.CMCC-CM.decadal2005.mon.atmos.Amon.r1i1p1.v20120604%7Cadm07.cmcc.it";
		SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
		
		Dataset dataset = solrRecordReader.assembleDataset();
		
		System.out.println(dataset.getId());
		
		FGDCRecordWriter fgdc = new FGDCRecordWriter();
		
		fgdc.setDataset(dataset);
		
		fgdc.writeFGDC();
		
	}
}
