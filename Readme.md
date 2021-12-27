# Recipe Application
> Written in *Hexagonal (Ports & Adapters) Architecture*
> 
> This is a small application that provides basic REST endpoints for managing recipes and users (add new recipe, delete, add to favorites, remove from favorites, search recipe etc.).

The technology behind it:
* Java
* H2 Database (Dev Profile)
* Postgresql (Prod Profile)
* Spring Boot
* AngularJS

## Installing / Getting started

#### Using `docker-compose`

In the terminal run the following command:
```console
$ docker-compose up
``` 

## Application UI:
http://localhost:4200

#Credentials
* **username**: _`admin`_ 
* **password**: _`admin123`_

## Swagger UI:
http://localhost:9091/swagger-ui.html#/


## POSTMAN Collections
Open the src/main/resources/Recipe Application.postman_collection.json

## Database Model
![Alt text](./recipe-application/src/main/resources/database%20model/recipe.png)





