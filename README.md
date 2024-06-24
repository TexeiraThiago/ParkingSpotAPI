# Parking Spot API

Create a simple CRUD using Springboot API

## Lessons Learned

How to  create a springboot project with spring initializr using Spring WEB, Spring Data JPA, Validations and MySql Drive using the best pratices around mvc architecture to create an API with Global Custom Date and pageable methods such as:
-getAll
-getOne
-post
-delete
-update  




## Environment 
- JDK 17
- Maven
- Postman
- MySql
- IDE (IntelliJ, STS, Eclipse, etc)

## Run Locally

Clone the project

```bash
  git clone git@github.com:TexeiraThiago/ParkingSpotAPI.git
```

On Postman

### Post

```bash

http://localhost:8080/parking-spot

{
    "parkingSpotNumber": "string",
    "licencePlateCar": ""string",
    "brandCar": "string",
    "modelCar": "string",
    "colorCar": "string",
    "responsibleName": "string",
    "apartment": "string",
    "block": "string"
}
```

### Put
```bash

http://localhost:8080/parking-spot/{id}

{
    "parkingSpotNumber": "string",
    "licencePlateCar": ""string",
    "brandCar": "string",
    "modelCar": "string",
    "colorCar": "string",
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
