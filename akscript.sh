#!/bin/sh

#################### This is a script file to clean , build and deploy the web project ############


## Path variable hols the local directory where your tomcat is installed
path=D:/tomcat_8/webapps
#path=D:/code/java/olx/tomcat/tomcat_8/webapps
rm -rf target/
mvn clean install -DskipTests=true
rm -rf $path/prime*
cp target/prime.war $path/
