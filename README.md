# Parking Spot API

Create a simple CRUD using Springboot API

## Lessons Learned

How to create a spring boot project with spring initializr Spring WEB, Spring Data JPA, with Docker and PostgresSQL applying the best pratices around mvc architecture to create an RESTful API with Global Custom Date and pageable methods such as:
-getAll
-getOne
-post
-delete
-update  

## Environment 
- JDK 21
- Maven
- Postman
- PosgreSQL
- Docker
- IDE (IntelliJ)

## Run Locally

Clone the project

```bash
  git clone https://github.com/TexeiraThiago/ParkingSpotAPI/pull/new/feature1
```

On Postman

### Post

```bash

http://localhost:8080/parking-spot

{
    "number": "string",
    "licencePlateCar": "string",
    "brand": "string",
    "model": "string",
    "color": "string",
    "responsibleName": "string",
    "apartment": "string",
    "block": "string"
}
```

### Put
```bash

http://localhost:8080/parking-spot/{id}

{
     "number": "string",
    "licencePlateCar": "string",
    "brand": "string",
    "model": "string",
    "color": "string",
    "responsibleName": "string",
    "apartment": "string",
    "block": "string"
}
```

### GetAll
```bash

http://localhost:8080/parking-spot
```
### GetOne

```bash

http://localhost:8080/parking-spot/{id}
```

### Delete

```bash

http://localhost:8080/parking-spot/{id}
```
