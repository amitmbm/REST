## REST API for CRUD operation,using the jersey,spring,hibernate,MySQL and maven.

Motivation for this project is to provide the head start to beginners to build **REST API's**,using the popular java open source frameworks and necessary configs.They should be able to build and test first webservice within 10 minutes.


### Prerequisities
JDK :- 1.7  
Mysql:- 5.6  
Maven:-3.0 or later  
Tomcat:- 1.7 or later

### Installing
1.Clone the project.<br>
2.If maven is setup properly in your system. then you can download all the required dependency and build war using the maven by using below command.<br>
```
mvn clean package
```

3.Create DB by running below command. In my repository dbconfig folder is present, inside that create_db.sql file is present
Give the absolute path of this file in below command.
<br>
```
mysql -h hostname -u user database < path/to/create_db.sql
``

4.Change the username and password to your mysql username in **context.xml** and **hibernate.cfg.xml** .<br>
5.Go to the target folder and copy the rest.war file and put it in tomcat webapps folder and start the server.<br>

### API Documentation.
To test the REST API , use any rest client like postman etc.<br>
1.Create product api (sample)<br>
URL :- localhost:8080/rest/api/products/<br>
header :- content-type :- application/json<br>
method :- post<br>
body :- {<br>
    "name" : "first product",<br>
    "description" : "sample"<br>
}<br>
After hitting this api , new product must be created inside the rest database's product table. 
you can check this by firing the sql query on mysql terminal.<br>
2.Get product api (sample)<br>
URL :- localhost:8080/rest/api/products/{id}<br>
provide the id , which you get as a response from create product api , in the url , you will get its all the information.

## Automated test cases will be added later using the powermock and mockito.
Un can be execusted using the Unit-tests feature branch.
