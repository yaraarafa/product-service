# product-service

## Brief Description
    - A Restful web service, that reads Products from a CSV-file Stores it in an in-memory database 
        and shows products/product as JSON objects on GET requests. 
    - The path and name of the CSV file are configurable from the application.properties file.
    - The file is loaded automatically when the application is run.
    - Null values in the files are handled.
    - Duplicate Ids cause records to be updated.
    - Corrupt files with no header or wrong number of attributes per record will cause the file not to be loaded.

## Technology Stack
  ### Frameworks, Languages and libraries:
        - Spring 5 Framework: is an application framework and inversion of control container for the Java platform.
        - Spring Boot (2.3.4.RELEASE): Spring Boot is an open source Java-based framework used to build 
            stand-alone and production ready spring applications. 
        - Spring Data JPA: part of the larger Spring Data family, makes it easy to easily implement 
            JPA based repositories and supports for JPA based data access layers.
        - H2 database: H2 is a relational database management system written in Java. 
            It can be embedded in Java applications.
        - Java 11: Java is a class-based, object-oriented programming language.
        - lombok: a java library that automatically plugs 
         Getters, Setters, Constructors and equal methods using annotations.
        - openCSV: Opencsv is an easy-to-use CSV (comma-separated values) parser library for Java.
        - JUnit and SpringBootTest are used for Testing.
    
## Run and Deploy
    - Change the file name and directory from the src/main/resources/application.properties 
        and src/test/resources/application.properties files and place the csv file at that location.
    - Change the test parameters for the server if it will not be run on  localhost at both files
    - Spring Boot uses a public static void main entry-point that launches an embedded web server for you.
        - Run the below maven command and get a Jar that includes all the other dependencies and things like your web server inside the archive.
            mvn clean install
        - Run the below command and it will run your entire Spring application with no fuss: no setup, no web server configuration
            java -jar target/product-service-0.0.1-SNAPSHOT.jar
     

## Available Endpoints
    - /product : returns a List of all the products listed in the loaded CSV file in JSON format
    - /product/{productId} : returns the product with the selected id if present in the database.
    
