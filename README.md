# jetty-hello-world

Run as `mvn jetty:run` and visit [http://localhost:8080/hello](http://localhost:8080/hello)

You can also run the war file as:
```
mvn clean package
java -jar target/dependency/jetty-runner.jar target/*.war
```

This example makes it easy to get started with Jetty based applications
