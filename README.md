# SPO_Workplace_Opt

SPO Coding  challenge - workplace optimization by Or Yaari

## Task

Build a workforce optimization tool for one of our cleaning partners! Our partner has contracts with
several structures all around Berlin. These structures are all of varying size (measured in rooms). The
workforce of our partner is made up of Senior Cleaners and Junior Cleaners. Senior Cleaners have a
higher work capacity than Junior Cleaners. Our partner is free to decide how many Senior and Junior
Cleaners are to be sent to clean a structure but there always needs to be at least one Senior cleaner at
every structure to lead the juniors. The goal is to minimize overcapacity at every structure.
Given an array of structure sizes (in no. of rooms) as well as work capacities of Junior and Senior
Cleaners, your program should present the optimal numbers of Juniors and Seniors for every structure.

## Few assessments

1. Can be up to 100 structure in the array
2. Each structure is up to 100 rooms
3. There is at least 1 senior worker
4. Seniors workers have more capacity than juniors
5. Junior capacity is at minimum 1 (senior is 2)

## How to run the solution
Package the project using maven ``mvn package``

You will get a jar file and you will need to run the command: ```java -jar SPO_Workplace_Opt.jar```

This will run the project.

After that run post http request to this url: ``http://localhost:8080/api/v1/spo/workplace/optimize``

The body should look like this:

`{ "rooms": [35, 21, 17], "senior": 10, "junior": 5 }`

The response will look like this:

`[{ "senior": 3, "junior": 1 },   { "senior": 2, "junior": 1 },   { "senior": 2, "junior": 0 } ]`

If the request input is not answering the assessments we will receive a bad request response.

## Two more things
1. We will currently prefer to fill the workplace capacity with as much seniors (regarding of course the least amount of overcapacity).
Meaning that for example the junior capacity is 1 and the senior capacity is 5 and the amount of rooms is 32,
the recommendation will be 6 seniors and 2 juniors and not 1 senior and 27 juniors.
This will reduce the amount of workers in total.
This could easily be reversed by changing the for loop in `WorkplaceOptimizationService` to run from 1 till `maxSeniorAmount -1`
2. Added a docker to create an image of the working project.
Just need to run the command ```docker build .``` in the project root folder.   