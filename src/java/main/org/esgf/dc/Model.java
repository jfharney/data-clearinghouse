package org.esgf.dc;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private String modelName;
	private List<SubModel> submodels;
	

	public Model() {
		this.setModelName(null);
		this.submodels = new ArrayList<SubModel>();
	}
	
	public void addSubmodel(SubModel submodel) {
		this.submodels.add(submodel);
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	

	public List<SubModel> getSubmodels() {
		return submodels;
	}

	public void setSubmodels(List<SubModel> submodels) {
		this.submodels = submodels;
	}
	
	
	
}
