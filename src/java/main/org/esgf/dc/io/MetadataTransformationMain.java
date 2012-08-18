package org.esgf.dc.io;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.esgf.dc.Dataset;



public class MetadataTransformationMain {

	private static String PROPERTY_FILE_LOCATION = ".";
	
	private static String MODELNAME_PROPERTIES = PROPERTY_FILE_LOCATION + "/modelnames.properties";
	private static String MODEL_MAP_PROPERTIES = PROPERTY_FILE_LOCATION + "/modelmap.properties";
	
	private static boolean useCache = true;
	
	private static String DIRECTORY_ROOT = ".";
	
	private static boolean writeAll = false;
	
	private static int writeType = 0;
	
	private static String modelGiven = "";
	
	public static void main(String [] args) {
		
		final MetadataTransformationMain self = new MetadataTransformationMain();
	    self.run(args);
	    
		
	}
	
	protected void run(String [] args) {
/*
		if (args.length!=0 && args.length!=4 && args.length!=5) {
			
			System.out.println("Usage error: incorrect argument list");
	    	//System.out.println("          java org.esgf.dc.io."+this.getClass().getName()+" <id>");
	    	
			
	    	System.exit(0);
	    	
	    	
	    } 
*/
/*
		else if(args.length == 0) {
	    	System.out.println("Default...only write to the properties files");
	    	
	    	
	    } 
*/		
//		else {
	    	
	    	for(int i=0;i<args.length;i++) {
	    		
	    		System.out.println("arg " + i + ": " + args[i]);
	    		
	    		if(args[i].equals("--cache") && i < args.length-1) {
	    			i++;
	    			if(args[i].equals("false")) {
		    			useCache = false;
		    		} else {
		    			useCache = true;
		    		}
	    		} else if(args[i].equals("--write") && i < args.length-1) {
	    			i++;
	    			if(args[i].equals("all")) {
		    			writeType = 1;
		    		} else if(args[i].equals("model")) {
		    			if(i != args.length-1) {
			    			i++;
			    			modelGiven = args[i];
			    			writeType = 2;
		    			} else {
		    				System.out.println("Warning usage on --write model ... reverting to writing only properties files" );
		    				writeType = 0;
		    			}
		    		} else if(args[i].equals("dataset")) {
		    			if(i != args.length-1) {
			    			i++;
			    			modelGiven = args[i];
			    			writeType = 3;
		    			} else {
		    				writeType = 0;
		    			}
		    		} else {
		    			System.out.println("Usage error: ");
		    			System.exit(0);
		    		}
	    		} else if(args[i].equals("--wd") && i < args.length-1) {
	    			i++;
	    			DIRECTORY_ROOT = args[i]; 
	    		} else {
	    			System.out.println("ignoring: " + args[i]);
	    		}
	    		
	    	}

	    	System.out.println("Cache: " + useCache);
	    	System.out.println("ModelGiven: " + modelGiven);
	    	System.out.println("Directory_root: " + DIRECTORY_ROOT);
	    	System.out.println("WriteType: " + writeType);
	    	
//	    }
		
		
		//System.exit(0);
		
		long start = System.currentTimeMillis();
		
		//modelGiven = "ACCESS1.0";
		//String modelGiven2 = "Obs-CFMIP";

		SolrModelReader smr = getModelsFromSolr();
		
		
		///String modelId = smr.getModelMap().
		
		String rootDir = DIRECTORY_ROOT + "/fgdc";
		
		try {
			// Create one directory
			boolean success = (new File(rootDir)).mkdir();
		 	if (success) {
		 		System.out.println("Directory: " 
		 				+ rootDir + " created");
		 	}  
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//System.exit(0);
		
		//writeOneModel(smr, modelGiven, rootDir);
		
		if(writeType == 1) {
			writeAll(smr, rootDir);
		} else if(writeType == 2) {
			writeOneModel(smr, modelGiven, rootDir);
		} else if(writeType == 3) {
			writeOneDataset(smr, modelGiven, rootDir);
		}
		
		
		
		long stop = System.currentTimeMillis();
		
		
		
		System.out.println("total time: " + (stop-start));
		
	}
	
	private void writeOneDataset(SolrModelReader smr,String datasetId,String rootDir) {
		for(Object key: smr.getModelMap().keySet()) {
			String keyStr = (String) key;
				
			try{
				String strDirectory = rootDir + "/" + keyStr;

				boolean success = (new File(strDirectory)).mkdir();
			 	if (success) {
			 		System.out.println("Directory: " 
			 				+ strDirectory + " created...Populating with metadata docs");
				}  
				 	
				for(int i=0;i<smr.getModelMap().get(key).size();i++) {
				 		
				 		
					String modelId = smr.getModelMap().get(key).get(i);
					if(modelId.equals(datasetId)) {
						System.out.println("\tWriting " + modelId  + " to file");
						SolrRecordReader solrRecordReader = new SolrRecordReader(modelId);
			 		
						Dataset dataset = solrRecordReader.assembleDataset();
			 		
						FGDCRecordWriter fgdc = new FGDCRecordWriter();

						fgdc.setFileName(strDirectory + "/fgdc-" + modelId + ".xml");
					
						fgdc.setDataset(dataset);
					
						fgdc.writeFGDC();
		 		
					}
				}
			 		
			 	
			}catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
			}
			
			
		}
	}
	
	private void writeAll(SolrModelReader smr,String rootDir) {

		for(Object key: smr.getModelMap().keySet()) {
			String keyStr = (String) key;
				
			try{
				String strDirectory = rootDir + "/" + keyStr;

				boolean success = (new File(strDirectory)).mkdir();
				if (success) {
					System.out.println("Directory: " 
				 	+ strDirectory + " created...Populating with metadata docs");
				}  
				 	
				for(int i=0;i<smr.getModelMap().get(key).size();i++) {
				 		
					String modelId = smr.getModelMap().get(key).get(i);
System.out.println("\tWriting " + modelId  + " to file");
					SolrRecordReader solrRecordReader = new SolrRecordReader(modelId);
					 		
					Dataset dataset = solrRecordReader.assembleDataset();
					 		
					FGDCRecordWriter fgdc = new FGDCRecordWriter();

					fgdc.setFileName(strDirectory + "/fgdc-" + modelId + ".xml");
							
					fgdc.setDataset(dataset);
							
					fgdc.writeFGDC();
				 		
				}
			}catch (Exception e){//Catch exception if any
					  System.err.println("Error: " + e.getMessage());
			}
				
				
		}
		
	}
	
	
	
	private void writeOneModel(SolrModelReader smr,String modelGiven,String rootDir) {

		System.out.println("Writing model " + modelGiven);
		
		for(Object key: smr.getModelMap().keySet()) {
			String keyStr = (String) key;
			//System.out.println("Key: " + keyStr);
			if(keyStr.equals(modelGiven)) {
				
				try{
					String strDirectory = rootDir + "/" + keyStr;

					boolean success = (new File(strDirectory)).mkdir();
				 	if (success) {
				 		System.out.println("Directory: " 
				 				+ strDirectory + " created...Populating with metadata docs");
				 	}  
				 	
				 	for(int i=0;i<smr.getModelMap().get(key).size();i++) {
				 		
					 		String modelId = smr.getModelMap().get(key).get(i);
System.out.println("\tWriting " + modelId  + " to file");
					 		SolrRecordReader solrRecordReader = new SolrRecordReader(modelId);
					 		
					 		Dataset dataset = solrRecordReader.assembleDataset();
					 		
							FGDCRecordWriter fgdc = new FGDCRecordWriter();

							fgdc.setFileName(strDirectory + "/fgdc-" + modelId + ".xml");
							
							fgdc.setDataset(dataset);
							
							fgdc.writeFGDC();
				 		//}
				 		
				 	}
				}catch (Exception e){//Catch exception if any
					  System.err.println("Error: " + e.getMessage());
				}
				
				
			}
		}
		
	}
	
	
	private SolrModelReader getModelsFromSolr() {

		File modelNamesFile = new File(MODELNAME_PROPERTIES);
		File modelMapFile = new File(MODEL_MAP_PROPERTIES);

		List<String> modelNames = new ArrayList<String>();
		
		
		SolrModelReader smr = new SolrModelReader();
		

		//get the models list
		
		//cache
		if(modelNamesFile.exists() && useCache) {
			System.out.println("Model names file already exists");
			
			Properties properties = new Properties();
	        String propertiesFile = MODELNAME_PROPERTIES;

			
	        try {
	            
	        	String [] names = null;
	        	
	            properties.load(new FileInputStream(propertiesFile));
	            
	            for(Object key : properties.keySet()) {
	                if(((String)key).equals("modelNames")) {
	                	String value = (String)properties.get(key);
		            	for(int i=0;i<(value.split(";")).length;i++) {
		            		String model = (value.split(";"))[i];
		            		modelNames.add(model);
		            	}
 	            	}
	            }
	            
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        smr.setModelNames(modelNames);
			
		} 
		//if not in cache, populate models list and modelnames.properties file
		else {

			
			smr.populateModelsList();
			
			modelNames = smr.getModelNames();
			
			try{
				  // Create file 
				  FileWriter fstream = new FileWriter("modelnames.properties");
				  BufferedWriter out = new BufferedWriter(fstream);
				  
				  
				  out.write("modelNames=");
				  for(int i=0;i<modelNames.size();i++) {
					  if(i == modelNames.size()-1) {

						  String modelName = modelNames.get(i);
						  out.write(modelName);
					  } else {
						  String modelName = modelNames.get(i);
						  out.write(modelName + ';');
					  }
				  }
				  //Close the output stream
				  out.close();
			}catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
			}
			
		}
		
		
		
		
		//cache
		if(modelMapFile.exists() && useCache) {
			System.out.println("Model map file already exists");
			
			Properties properties = new Properties();
	        String propertiesFile = MODEL_MAP_PROPERTIES;

			Map<String,List<String>> modelMap = new TreeMap<String,List<String>>();
			
	        try {
	            
	        	String [] names = null;
	        	
	            properties.load(new FileInputStream(propertiesFile));
	            
	            
	            for(Object key : properties.keySet()) {
	            	
	            	List<String> modelIds = new ArrayList<String>();
	            	
	            	String value = (String)properties.get(key);
	            	String [] ids = value.split(";");
	            	for(int i=0;i<ids.length;i++) {
	            		modelIds.add(ids[i]);
	            	}
	            	String keyStr = (String)key;
	            	
	            	
	            	modelMap.put(keyStr, modelIds);
 	            	 
	            }
	            
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
			
	        smr.setModelMap(modelMap);
			
		} 
		//if not in cache, populate models list and modelnames.properties file
		else {
			
			//get the model map
			smr.populateModelMap();

			// Create file 
			FileWriter fstream;
			try {
				fstream = new FileWriter("modelmap.properties");
				BufferedWriter out = new BufferedWriter(fstream);
				
				
				
				Map<String, List<String>> modelMap = smr.getModelMap();
				
				for(Object key : modelMap.keySet()) {
					String keyStr = (String)key;
					

					out.write(keyStr + "=");
					
					List<String> modelIds = modelMap.get(key);
					for(int i=0;i<modelIds.size();i++) {
						if(i == modelIds.size()-1) {
							out.write(modelIds.get(i));
						} else {
							out.write(modelIds.get(i) + ";");
						}
					}
					
					out.write("\n");
					//System.out.println("Key: " + keyStr + " Value: " + modelMap.get(key));
					
					
					
					
				}
				//Close the output stream
				out.close();
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  
			
			
		}
		
		
		
		return smr;
		
	}
	
	
}
