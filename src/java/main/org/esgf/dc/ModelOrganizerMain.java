package org.esgf.dc;

public class ModelOrganizerMain {

	public static void main(String [] args) {
		
		System.out.println("In model organizer main");
		
		//plan
		
		//
		//1 Ð Run a search against the search API that displays the 47 model facet names (pcmdi9.llnl.gov/esg-search/search/query=*&facets=model)
		//2-  for each of the 47 models obtained by the resulting xml, obtain the "sub-model" datasets (pcmdi9.llnl.gov/esg-search/search/query=*&model=xxx)
		//3 Ð Scrape and aggregate each of the sub models' metadata and then transform them into FGDC format.
		//4 Ð Manually insert 3 things: PI (from CIM), Abstract (from CIM), and portal back link (manually)
		//5 Ð The result is exactly 47 records in Mercury's search index
		
		
		
		
	}
	
	
}
