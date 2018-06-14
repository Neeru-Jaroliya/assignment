Steps to Build and Deploy the Project

1. Add spring.datasource.url value in application-prod.properties
2. Run mvn clean install -P prod command.
3. AWS: Deploy the jar on aws
4. Local: run command java -jar target/assignment-0.0.1-SNAPSHOT.jar
