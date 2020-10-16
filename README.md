# product-service
A Restful web service, that reads Products from a CSV-file Stores it in an in-memory database and shows products/product as JSON objects on GET requests. 
The path and name of the CSV file are configurable from the application.properties file.
The file is loaded automatically when the application is run.
Some tests will fail if the file is missing/in the correct format/has no header.
