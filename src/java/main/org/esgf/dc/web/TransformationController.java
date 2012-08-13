package org.esgf.dc.web;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.esgf.dc.Dataset;
import org.esgf.dc.Metadata;
import org.esgf.dc.Model;
import org.esgf.dc.io.ESGFRecordReader;
import org.esgf.dc.io.ESGFRecordWriter;
import org.esgf.dc.io.FGDCRecordWriter;
import org.esgf.dc.io.SolrRecordReader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TransformationController {

	
	@RequestMapping(method=RequestMethod.GET, value="/metadata")
    public @ResponseBody String getSRMRequest(HttpServletRequest request) throws Exception {
		System.out.println("In getSRMRequest");
		System.out.println("TIME: "+System.currentTimeMillis());
		
		
		/*
		String modelName = "CAM5";
		ESGFRecordReader esgfRecordReader = new ESGFRecordReader(modelName);
		
		Model model = esgfRecordReader.assembleModel();
		
		ESGFRecordWriter recordWriter = new ESGFRecordWriter(model);
		
		recordWriter.writeRecord();
		
		Metadata metadata = recordWriter.getMetadata();
		
		//String response = "<srm_url>srm</srm_url>";
		String response = metadata.toXML();
		*/
		
		/*
		 * cmip5.output1.INM.inmcm4.1pctCO2.day.atmos.day.r1i1p1.v20110323|pcmdi9.llnl.gov
		 * cmip5.output1.NCAR.CCSM4.1pctCO2.day.atmos.cfDay.r2i1p1.v20120717|tds.ucar.edu
		 * cmip5.output1.CSIRO-BOM.ACCESS1-3.1pctCO2.mon.aerosol.aero.r1i1p1.v20120508|esgnode1.nci.org.au
		 * cmip5.output1.NASA-GMAO.GEOS-5.decadal1960.mon.atmos.Amon.r2i1p1.v20120515|esgdata1.nccs.nasa.gov
		 * cmip5.output1.NCC.NorESM1-ME.1pctCO2.mon.atmos.Amon.r1i1p1.v20120402|norstore-trd-bio1.hpc.ntnu.no
		 */
		
		List<String> datasetIds = new ArrayList<String>();
		datasetIds.add("cmip5.output1.NCAR.CCSM4.1pctCO2.day.atmos.cfDay.r2i1p1.v20120717%7Ctds.ucar.edu");
		datasetIds.add("cmip5.output1.INM.inmcm4.1pctCO2.day.atmos.day.r1i1p1.v20110323%7Cpcmdi9.llnl.gov");
		datasetIds.add("cmip5.output1.CSIRO-BOM.ACCESS1-3.1pctCO2.mon.aerosol.aero.r1i1p1.v20120508%7Cesgnode1.nci.org.au");
		datasetIds.add("cmip5.output1.NASA-GMAO.GEOS-5.decadal1960.mon.atmos.Amon.r2i1p1.v20120515%7Cesgdata1.nccs.nasa.gov");
		datasetIds.add("cmip5.output1.NCC.NorESM1-ME.1pctCO2.mon.atmos.Amon.r1i1p1.v20120402%7Cnorstore-trd-bio1.hpc.ntnu.no");
		
		
		//for(int i=0;i<datasetIds.size();i++) {
			
			String datasetId = datasetIds.get(0);
			
			SolrRecordReader solrRecordReader = new SolrRecordReader(datasetId);
			
			Dataset dataset = solrRecordReader.assembleDataset();
			
			System.out.println(dataset.getId());
			
			FGDCRecordWriter fgdc = new FGDCRecordWriter();
			
			fgdc.setFileName("fgdc-" + datasetId + ".xml");
			
			fgdc.setDataset(dataset);
			
			fgdc.writeFGDC();
		//}
		
		String response = (fgdc.getMetadata()).toXML();
	
		System.out.println("Response: " + response);
		
		response = "<response>See tomcat console</response>\n";
		
		return response;
	}
	
	
	/**
	 * For testing purpose only      
	 *
	 * A dummy main program to be used for testing purpose
	 *     
	 */
	
	public static void main(String args[]){
		final MockHttpServletRequest mockRequest = new MockHttpServletRequest();

		//Set dummy parameter for testing purpose.
		
	}
	
	
}
