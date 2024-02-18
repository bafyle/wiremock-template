# WireMock Template Project
This maven project is simple template to mock APIs using WireMock. The intension of this is to replace JSON configuration defaulted by WireMock.

## Usage and Installation
### Installation
You need to have maven and JDK 17 installed in your Path

### Usage
To create your stubbing, create a class that extends from BaseStub.java which is located in stubber package and implement the needed method. The stub method tells WireMock what url to mock, what is the response and what is request.

After creating the stub, add the class inside the static method getStubberClasses inside the App.java and you are good to go

There are basic stub classes already defined as a reference as well as some transformers and handlebars helpers

### Running
to run the app and initialize WireMock server on port 8080:

```bash
java -jar target/wiremock-test-0.1.0.jar --port 8080
```

## Notes:
The pom.xml file is configured to create a jar executable file with all dependencies embedded in it