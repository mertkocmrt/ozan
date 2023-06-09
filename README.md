# OZAN - EXCHANGE REST API
This is a little simulation of foreign exchange application which is one of the most common services used in financial applications.

# To run the application
    clone the root project
    
## Run following commands
    mvn install
    java -jar exchange/target/exchange-0.0.1-SNAPSHOT.jar
    
# AVAILABLE ENDPOINTS
    
## Get rate of given currency pair
### Request
`GET /api/v1/exchange/rate`
Source currency MUST be EUR 

    {
        "sourceCurrency":"EUR",
        "targetCurrency":"USD"
    }

### Response
    {
        "exchangeRate": [
            1.076136
        ]
    }

## Calculate target amound and save with given currency pair and source amount
### Request
`POST /api/v1/exchange/conversion/convert`

    {
        "amount":10.0,
        "sourceCurrency":"EUR",
        "targetCurrency":"USD"
    }

### Response
    {
        "targetAmount": 10.7774900,
        "transactionId": 1
    }
    
## Get list of transactions with given  id or date
### Request
`GET /api/v1/exchange/transaction`

    {
        "transactionId":2,
        "transactionDate":"2023-06-09",
        "page":0,
        "size":5
    }

### Response
    [
        {
            "transactionId": 1,
            "transactionDate": "2023-06-09",
            "sourceCurrency": "EUR",
            "targetCurrency": "USD",
            "targetAmount": 10.77
        }
    ]

    
    
