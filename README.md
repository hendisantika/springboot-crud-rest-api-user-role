# springboot-crud-rest-api-user-role
### Things todo list:
1. Clone this repository: `git clone https://github.com/hendisantika/springboot-crud-rest-api-user-role.git`
2. Navigate to the folder: `cd springboot-crud-rest-api-user-role`
3. Change the MySQL credentials with your own in `application.properties` file.
4. Run the application: `mvn clean spring-boot:run`
5. Run this command on your DB:
   ```sql
    INSERT INTO roles (id, name)
    VALUES (1, 'SUPER ADMIN'),
    (2, 'ADMIN'),
    (3, 'EDITOR'),
    (4, 'USER');
    ```
6. Import POSTMAN Collection file into POSTMAN App.
7. Open Swagger UI: http://localhost:8080/swagger-ui/index.html
