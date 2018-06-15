Steps to Build and Deploy the Project

1. Add spring.datasource.url value in application-prod.properties
2. Run mvn clean install -P prod command.
3. AWS: Deploy the jar on aws
4. Local: run command java -jar target/assignment-0.0.1-SNAPSHOT.jar


NOTES:
APIs and Descriptions
1. To create user
POST Request: http://localhost:8080/login/create-user    
Body: {"email":"ikjot1@gmail.com", "password":"ikjotpagal1"}
Response: On successful user Creation returns "User created successfully" or "User Creation Failed"

2. Login API 
POST Request: http://localhost:8080/login/login
Body: {"email":"ikjot1@gmail.com", "password":"ikjotpagal1"}
Response: On successful login returns token [B@ff49cba-1351483273-7 

3. Session Alive Check API
GET http://localhost:8080/login/session-alive
HEADERS: AccessToken: value obtained from loginAPI
Response: Session Alive or Session Expired

4. Logout User
GET: http://localhost:8080/login/logout
HEADERS: AccessToken: value obtained from loginAPI
Response: On successful execution "User logged out successfully"

5. Login Trail
GET: http://localhost:8080/login/login-trail?email=email
Response: Login Trail with Limit 20
