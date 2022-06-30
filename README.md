# Loan calculator
Set up mysql database
 1. via docker (https://hub.docker.com/_/mysql)
 2. or download community server and run it locally (https://dev.mysql.com/downloads/mysql/)
 4. create loandb schema


Start the app
- alter username and password in application.yaml (spring.datasource.username and spring.datasource.password) to match yours
- Run LoanCalculatorApplication
- default port is used (8080)


OpenApi (Swagger UI)
- http://localhost:8080/swagger-ui/index.html

Class diagram
 <br/><img width="681" alt="Screenshot 2022-06-30 at 15 13 26" src="https://user-images.githubusercontent.com/62796293/176686165-e3b099bd-3566-4d2d-8eb9-954837b58f02.png">
