package org.esgf.dc.io.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Model {

	private String modelName;
	
	private Map<String,List<String>> metadata;
	
	private int totalNumFiles;
	
	public Model() {
		this.setModelName("");
		this.setMetadata(new TreeMap<String,List<String>>());
		this.setTotalNumFiles(0);
	}


	public Model(String modelName) {
		this.setModelName(modelName);
		this.setMetadata(new TreeMap<String,List<String>>());
		this.setTotalNumFiles(0);
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Map<String,List<String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String,List<String>> metadata) {
		this.metadata = metadata;
	}

	public int getTotalNumFiles() {
		return totalNumFiles;
	}

	public void setTotalNumFiles(int totalNumFiles) {
		this.totalNumFiles = totalNumFiles;
	}
	
}
