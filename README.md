

## Features
   * This project provides help to find out and also post a new different types of to-let such as living or business space. 
     - Some small business needs a space for temporary that provides per day hourly payment service. Those kinds of space can be finds here and as well as post here.
     - For short time space if same date conflict then other available dates will be suggested.
    

## Entity Relationship Diagram
![CommercialTolet](https://user-images.githubusercontent.com/39630470/137129240-890b5cf8-4f4c-45fd-bc78-436fa26d6f93.PNG)

## How to run
Prerequisite
* JDK 1.8
* Maven 4.0.0
```
mvn install
```
Run
```
mvn spring-boot:run
```
## Api Documentation

- Post request for create tolet
```
/api/tolet/userId/2
```
Request Body
```
{
    "district":"Dhaka",
    "localArea":"Khilgoan",
    "address":"21, B-block khilgoan taltola",
    "floorNumber":1,
    "squareFeet":1500,
    "perSquareRate":7000,
    "monthlyRent":50000,
    "hourlyRent":0,
    "advancePayment":400000,
    "serviceCharge":5000
}
```
- Put request for update tolet
```
/api/tolet/spaceId/5/userId/3
```
> Search tolets by district, area, rent range 
```
/api/tolet/searchBy/district/{district}
/api/tolet/searchBy/district/{localArea}
/api/tolet/searchBy/monthlyRent/{rent}
/api/tolet/searchBy/rentRange/{rent}
```
> This API endpoints helps to find out short time tolets 
```
/api/tolet/searchBy/hourlyRent/{rent}
```
* Booking APIs  endpoint
```
/api/booking/{toletId}
```
Request Body
```
{
    "username":"Abdur Rahman",
    "phoneNumber":"+8801921854091",
    "bookingDate":"2021-09-30"
}
```
