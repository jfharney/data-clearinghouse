package org.esgf.dc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubModel {

	private String id;
	private Map<String,String> metadata;
	
	public SubModel() {
		this.setId(null);
		this.setMetadata(new HashMap<String,String>());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String,String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String,String> metadata) {
		this.metadata = metadata;
	}
	
	
	
}
