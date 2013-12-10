API-TestSuite
=============

Test Suite to test the Web Service API that is used by all Sensors (Physical and Mobile)

Steps to run:
===========
1. git clone the repository

2. To Run Unit and Integration test cases, please run 

mvn test

3. To Run performance tests for v1.2 of the API, please run 

mvn gatling:execute -Dgatling.simulationClass=unit.APISimulationV12

4. To Run performance tests for v1.3 of the API, please run 

mvn gatling:execute -Dgatling.simulationClass=unit.APISimulationV13

