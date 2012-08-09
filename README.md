data-clearinghouse
=======


PREREQUISITE NOTES:  
- must have ant version 1.8.1 or above
- must add ivy*.jar to $ANT_HOME/lib


To run the transformation main class:
ant make_jar

cd build

java -Djava.ext.dirs=../lib:../lib/fetched/compile MetadataTransformationMain

