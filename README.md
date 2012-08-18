Name: data-clearinghouse transformation repo
Purpose: Transform metadata records from ESG into various metadata standards
	(Note: only FGDC format supported thus far)

=======

PREREQUISITES REQUIRED FOR RUNNING:
- Installation/Download of the following:
* Git (assumed)
* Ant (version 1.8.1 or above)
* Ivy 

- Place git and ant in the execution path variable

- Place the ivy-xxx.jar file in ${ANT_HOME}/lib


CLONING THE PROJECT

- git clone git@github.com:jfharney/data-clearinghouse.git

- cd data-clearinghouse

- Create the directory etc/etc in the directory where the project was cloned 
	(Note: this is due to a bug in the ant build script...will be fixed TBD)
	

BUILDING THE PROJECT

- Build the project by running:
ant make_jar


RUNNING THE PROJECT

- Run the project by doing the following:

cd build

java -Djava.ext.dirs=../lib:../lib/fetched/compile org.esgf.dc.io.MetadataTransformationMain <arglist>

<arglist> can be the following:

--cache <true/false> 
* true - Uses the models.properties and modelmap.properties files to extract Model names
* false - Regenerates the models.properties and modelmap.properties files by querying solr
for the model and individual dataset names

example:

java -Djava.ext.dirs=../lib:../lib/fetched/compile 
org.esgf.dc.io.MetadataTransformationMain --cache false

--write model <modelname>
* generates the fgdc documents for all individual datasets of model <modelname>

example:

java -Djava.ext.dirs=../lib:../lib/fetched/compile 
org.esgf.dc.io.MetadataTransformationMain --write model CAM5

List the current directory contents.  A directory called "FGDC" should appear containing 
a single directory called "CAM5".  In this directory contains all the FGDC metadata documents
of all datasets within the CAM5 model

--write dataset <datasetname>
* generates the fgdc document for an individual dataset <datasetname>

Same effect as "model" but writes only a single dataset


--write all
* generates the fgdc documents for ALL models (and associated individual datasets)

example:

java -Djava.ext.dirs=../lib:../lib/fetched/compile 
org.esgf.dc.io.MetadataTransformationMain --write all

List the current directory contents.  A directory called "FGDC" should appear containing 
directories of all models.
*WARNING* - this operation takes a long time


--wd <directory path> 
* creates a root directory in which the "FGDC" directory of the above is written



