# REST API for CRUD operation on sample database,Using the jersey, Spring, hibernate, MYSQL and Maven.

Motivation for this project is to provide the head start to beginners for building the REST api, using the popular java opensource framework and prrovide all the neccessary configuration in one place, So that anyone who is new to these famewrok can simply take it as a reference and shoud be able to build first webservice within 10 minutes.


### Prerequisities
JDK :- 1.7
mysql:- 5.6
maven
tomcat

### Installing
1. clone the project.
2. If maven is setup properlly in your system. then you can download all the required depency and build war using the maven by using below command.
mvn clean package
3.open db.sql and run the sql commands in your mysql terminal.
4. change the username and passwd to your mysql username in context.xml.
5. Go to the target folder and copy the rest.war file and put it in tomcat webapps folder and start the server.

### API Documentation.
To test the REST API , use any rest client like postman etc.
1. create product api (sample)
URL :- localhost:8080/rest/api/products/
header :- content-type :- application/json
method :- post 
body :- {
    "name" : "first product",
    "description" : "sample"
}

After hitting this api , 1 new product must be created inside the rest database , product table. 
you can check this by firing the sql query on mysql terminal.

2. Get product api (sample)
URL :- localhost:8080/rest/api/products/{id}
provide the id , which you get as a response from create product api , in the url , you will get its all the information.

## Automated test cases will be added later.
