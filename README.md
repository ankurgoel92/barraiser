# Barraiser

## Description

Technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven

* The architecture of the web service is built with the following components:
  * DataTransferObjects: Objects which are used for outside communication via the API
  * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
  * Service: Implements the business logic and handles the access to the DataAccessObjects.
  * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
  * DomainObjects: Functional Objects which might be persisted in the database.
