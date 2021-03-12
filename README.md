# stockdata
application that would allow multiple users to Save Bulk record, single record and get stock data
User can perform below operations:
        1. upload a bulk data set
	   endpoint: http://localhost:8080/stockData Method: POST
        2. query for data by stock ticker (e.g. input: AA, would return 12 elements if the only data uploaded were the single data set above)
	   endpoint: http://localhost:8080/stockData/stocks/{ticker} Method: GET
        3. add a new record
	   endpoint: http://localhost:8080/stockData/stocks Method: POST

Technologies used:
1. java 1.8
2. Spring boot 2.4.3
3. derby database
4. Swagger
3. Log4j
4. Junit4

Api Documentation
Implemented api documentation using Swagger 2 UI
end point: http://localhost:8080/swagger-ui.html

Unit Testcases:
 Add Junit testcases for testing the functionalities.
