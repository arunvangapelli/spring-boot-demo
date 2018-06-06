# spring-boot-demo
This project contains student end points. For example POST, PUT, GET, DELETE etc.

Environment:
You need Java 1.8, gradle

Steps to run the project locally:

Go to a path where you want to clone. For example: /Users/avangapelli/Documents/GitHub (mac)
Then run -> git clone https://github.com/arunvangapelli/spring-boot-demo.git

2. Go to root of the project and build it.

/Users/avangapelli/Documents/GitHub/spring-boot-demo -> gradle clean build eclipse

3. Import the project in eclipse.

File -> import -> General -> Existing projects into workspace -> click next and browse to your project path -> finish

4. Right click on the project and Run As -> Spring Boot App. If the service starts successfully it will run on port 3007.

5. Open postman or any other Rest client and call an end point to make sure.

#Configuring the mysql on local and modifying the datasource properties

1. Modify the  datasource url  like #url: jdbc:mysql://localhost:3306/test , here test stands for the schema/database we created on local

2. Check with the username and password 
