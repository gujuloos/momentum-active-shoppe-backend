# Momentum Active Shoppe

Momentum Active Shoppe is a software service that allows customers to purchase products via a basic shop.

##Requirements

Download and install [Java 11](https://www.oracle.com/za/java/technologies/javase-jdk11-downloads.html) or higher

## Installation

First clone the repository using git with either ssh or https
```bash
git clone https://github.com/gujuloos/momentum-active-shoppe-backend.git
```

You would then need to run a command to import all the libraries needed to run the project. To do this, run the following command in the root directory of the folder
```bash
mvn clean install -DskipTests
```

##Running Application

Once all the packages are brought into the project, you can then go ahead and start it up using the following command in the same directory of the project as the previous command.
```bash
mvn spring-boot:run
```

Or if you want to use the more traditional java way
```bash
java -jar target/active-shoppe-1.0.0-SNAPSHOT.jar
```

## Using API Endpoints

To see the list of the available endpoints via the API documentation. Navigate to the [Swagger UI](http://localhost:9011/momentum-active-shoppe/swagger-ui.html) of the application.

This UI allows you to view and execute the endpoints along with the request and response JSON objects needed.

The API documentation needed if you want to consume the definitions of the request and response objects along with the endpoints can be found [here](http://localhost:9011/momentum-active-shoppe/api-docs)

##Metrics
To access the metrics of the application, you can easily navigate to the [actuator endpoint](http://localhost:9011/momentum-active-shoppe/actuator).

To view the prometheus metric data to consume in a prometheus system via Docker. You can navigate to [this endpoint](http://localhost:9011/momentum-active-shoppe/actuator/prometheus)

## License
[MIT](https://choosealicense.com/licenses/mit/)
