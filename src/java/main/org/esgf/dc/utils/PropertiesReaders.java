package org.esgf.dc.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReaders {


    private final static String MODEL_DESCRIPTIONS_FILE = "modeldescriptions.properties";
    private final static String MODEL_TITLES_FILE = "modeltitles.properties";
    private final static String MODEL_ORGANIZATIONS_FILE = "modelorganizations.properties";
    private final static String MODEL_INSTITUTES_FILE = "modelinstitute.properties";
    private final static String MODEL_WEBURLS_FILE = "modelwebURL.properties";
    private final static String MODEL_PI_FILE = "modelPI.properties";
    
    public static String getModelOrganization(String modelName) {
    	
    	String modelOrganization = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_ORGANIZATIONS_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelOrganization = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelOrganization = "N/A";
        }
        

        if(modelOrganization == null) {
        	modelOrganization = "N/A";
        }
    	
    	
    	return modelOrganization;
    	
    }
	
	
	public static String getModelDescription(String modelName) {
    	
    	String modelDescription = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_DESCRIPTIONS_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelDescription = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelDescription = "N/A";
        }
    	

        if(modelDescription == null) {
        	modelDescription = "N/A";
        }
        
    	return modelDescription;
    	
    }
	

	public static String getModelTitle(String modelName) {
    	
    	String modelTitle = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_TITLES_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelTitle = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelTitle = "N/A";
        }
    	

        if(modelTitle == null) {
        	modelTitle = "N/A";
        }
        
    	return modelTitle;
    	
    }
	
	public static String getModelInstitute(String modelName) {
		String modelInstitute = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_INSTITUTES_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelInstitute = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelInstitute = "N/A";
        }
    	

        if(modelInstitute == null) {
        	modelInstitute = "N/A";
        }
    	
    	return modelInstitute;
    	
	}

	public static String getModelPI(String modelName) {
		String modelPI = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_PI_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelPI = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelPI = "N/A";
        }
    	
        if(modelPI == null) {
        	modelPI = "N/A";
        }
    	
    	return modelPI;
    	
	}

	public static String getModelWebURL(String modelName) {
		String modelWebURL = null;
		Properties properties = new Properties();
        String propertiesFile = MODEL_WEBURLS_FILE;
        
        try {
            properties.load(new FileInputStream(propertiesFile));
            for(Object key : properties.keySet()) {
                String value = (String)properties.get(key);
                //System.out.println("key: " + key + " " + value);
                if(((String) key).equals(modelName)) {
                	modelWebURL = value;
                }
            }
            
            
        }catch(Exception e) {
        	//e.printStackTrace();
        	modelWebURL = "N/A";
        }
    
        

        if(modelWebURL == null) {
        	modelWebURL = "N/A";
        }
        
        
    	
    	return modelWebURL;
    	
	
	
	}
	
	
	
}
