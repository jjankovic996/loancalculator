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
