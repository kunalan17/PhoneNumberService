# Customer Phone Number APIs 
This is a microservice project. You need JDK 11+ and Gradle 7.4.2 installed to run this service.

## How to build and run the project

To build the project
*	./gradlew build 
*	./gradlew clean build
	
To import to eclipse IDE
*	./gradlew eclipse

To run the service
*	./gradlew bootRun

API document will be generated with service creation. Please brower following API document URL once the service is up and running.
http://localhost:8080/swagger-ui.html#/phone-number-controller

## Testing

To build the test report
*	./gradlew build jacocoTestReport
	
Sample static JSON data is added inside following path, and you can use the data to test APIs.
*	src -> main -> resources -> static-data -> customer-numbers.json 

Test report will be generated into following location
*	Your Workspace/PhoneNumberService/build/reports/jacoco/test/html/index.html
*	Your Workspace/PhoneNumberService/build/reports/tests/test/index.html
