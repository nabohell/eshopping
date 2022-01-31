# Shopping Cart Backend API

This Assignment solution of an online shopping backend APIs, that wretten using Springboot and mysql database.
###### Prerequisites
* docker v2.5+
* docker-compose version 1.27+
## Build using maven (optional)
    mvn clean install package 

## Run the app using docker-compose
navigate to project directory and run the following

    docker-compose up -d
######Default Port
    
    9000

######Users

* Admin

        username: admin
        passwrod: password

* Customer
    
        username: john
        passwrod: password

# REST API

The REST API to the solution app is described below.

## Public API's
### Authenticate
##### Request
`POST /authenticate
###### Payload
    {
        "username":"john",
        "password":"password"
    }
### Response
    {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
    }

## Customer API's
### Get Shopping Cart Details
##### Request
GET /api/cart
###### Headers
    Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"

##### Response

    {
    "id": 4,
    "createdDate": "2022-01-30T20:42:25.000+00:00",
    "updatedDate": "2022-01-30T20:46:33.349+00:00",
    "customer": {
        "username": "john",
        "paymentCode": null,
    },
    "cartItems": [
        {
            "id": 4,
            "createdDate": "2022-01-30T20:46:33.335+00:00",
            "updatedDate": "2022-01-30T20:46:33.335+00:00",
            "product": {
                "id": 1,
                "createdDate": "2022-01-29T15:21:49.000+00:00",
                "updatedDate": "2022-01-29T15:21:49.000+00:00",
                "name": "test1",
                "price": 100.0,
                "description": null
            },
            "quantity": 10
        }
    ],
    "totalPrice": 1000.0
    }

### Add To Cart
##### Request
POST /api/cart
###### Headers
    Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
###### Payload
    {
        "product":{
            "id":2
        },
        "quantity":3
    }
##### Response
    {
    "id": 4,
    "createdDate": "2022-01-30T20:42:25.000+00:00",
    "updatedDate": "2022-01-30T20:49:55.518+00:00",
    "customer": {
        "username": "john",
        "paymentCode": null
    },
    "cartItems": [
        {
            "id": 4,
            "createdDate": "2022-01-30T20:46:33.000+00:00",
            "updatedDate": "2022-01-30T20:46:33.000+00:00",
            "product": {
                "id": 1,
                "createdDate": "2022-01-29T15:21:49.000+00:00",
                "updatedDate": "2022-01-29T15:21:49.000+00:00",
                "name": "test1",
                "price": 100.0,
                "description": null
            },
            "quantity": 10
        },
        {
            "id": 5,
            "createdDate": "2022-01-30T20:49:55.469+00:00",
            "updatedDate": "2022-01-30T20:49:55.469+00:00",
            "product": {
                "id": 2,
                "createdDate": "2022-01-29T15:26:01.000+00:00",
                "updatedDate": "2022-01-29T15:26:01.000+00:00",
                "name": "test2",
                "price": 10.0,
                "description": null
            },
            "quantity": 3
        }
    ],
    "totalPrice": 1000.0
    }

## Delete from cart
##### Request
DELETE /api/cart
###### Headers
    Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
###### Payload
    [{
    "product":{
        "id":2
    }
}]
##### Response
    {
    "id": 4,
    "createdDate": "2022-01-30T20:42:25.000+00:00",
    "updatedDate": "2022-01-30T20:49:56.000+00:00",
    "customer": {
        "username": "john",
        "paymentCode": null,
    },
    "cartItems": [
        {
            "id": 4,
            "createdDate": "2022-01-30T20:46:33.000+00:00",
            "updatedDate": "2022-01-30T21:02:55.000+00:00",
            "product": {
                "id": 1,
                "createdDate": "2022-01-29T15:21:49.000+00:00",
                "updatedDate": "2022-01-29T15:21:49.000+00:00",
                "name": "test1",
                "price": 100.0,
                "description": null
            },
            "quantity": 5
        }
    ],
    "totalPrice": 500.0
    }
## Update Cart Item
##### Request
PUT /api/cart/{cartItemId/
###### Headers
    Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
###### Payload
    {
    "quantity":5
    }
##### Response only CartItem response
    {
    "id": 4,
    "createdDate": "2022-01-30T20:46:33.000+00:00",
    "updatedDate": "2022-01-30T21:02:54.927+00:00",
    "product": {
        "id": 1,
        "createdDate": "2022-01-29T15:21:49.000+00:00",
        "updatedDate": "2022-01-29T15:21:49.000+00:00",
        "name": "test1",
        "price": 100.0,
        "description": null
    },
    "quantity": 5
    }

    }
## Checkout Cart 
##### Request
PUT /api/cart/checkout
###### Headers
    Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"

##### Response OrderDetails
    {
    "id": 2,
    "createdDate": "2022-01-31T00:06:42.544+00:00",
    "updatedDate": "2022-01-31T00:06:42.544+00:00",
    "customer": {
        "username": "john",
        "paymentCode": null,
    },
    "orderItems": [
        {
            "id": 2,
            "createdDate": "2022-01-31T00:06:42.549+00:00",
            "updatedDate": "2022-01-31T00:06:42.549+00:00",
            "product": {
                "id": 1,
                "createdDate": "2022-01-29T15:21:49.000+00:00",
                "updatedDate": "2022-01-29T15:21:49.000+00:00",
                "name": "test1",
                "price": 100.0,
                "description": null
            },
            "quantity": 10
        }
    ],
    "paymentCode": null,
    "totalPrice": 1000.0,
    "status": "CHECKOUT"
}


## Product Catalog
GET /api/product
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Params
    * Search: [Product Name]
    * Page#: [default product page = 0 ]
    * Size#: [default product page size = 20]

### Response

    {
    "content": [
        {
            "id": 2,
            "createdDate": "2022-01-29T15:26:01.000+00:00",
            "updatedDate": "2022-01-29T15:26:01.000+00:00",
            "name": "test2",
            "price": 10.0,
            "description": null
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 1,
        "pageNumber": 1,
        "pageSize": 1,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 3,
    "totalElements": 3,
    "size": 1,
    "number": 1,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": false,
    "numberOfElements": 1,
    "empty": false
    }


## News 
GET /api/news
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Params
    * Search: [title]
    * Page#: [default product page = 0 ]
    * Size#: [default product page size = 20]

### Response

    {
    "content": [
        {
            "id": 1,
            "createdDate": "2022-01-30T23:15:15.000+00:00",
            "updatedDate": "2022-01-30T23:15:15.000+00:00",
            "title": "test news item",
            "description": "test news item"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
    }

## Admin APIs

## Product Catalog
GET /api/admin/product
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Params
    * Search: [search for product name contnaing]
    * Page#: [default product page = 0 ]
    * Size#: [default product page size = 20]

### Response

    {
    "content": [
        {
            "id": 1,
            "createdDate": "2022-01-29T15:21:49.000+00:00",
            "updatedDate": "2022-01-29T15:21:49.000+00:00",
            "name": "test1",
            "price": 100.0,
            "description": null
        },
        {
            "id": 2,
            "createdDate": "2022-01-29T15:26:01.000+00:00",
            "updatedDate": "2022-01-29T15:26:01.000+00:00",
            "name": "test2",
            "price": 10.0,
            "description": null
        },
        {
            "id": 3,
            "createdDate": "2022-01-30T17:28:33.000+00:00",
            "updatedDate": "2022-01-30T17:28:33.000+00:00",
            "name": "test2",
            "price": 10.0,
            "description": null
        }
        ],
            "pageable": {
            "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
            "offset": 0,
            "pageSize": 20,
            "pageNumber": 0,
            "paged": true,
            "unpaged": false
        },
            "last": true,
            "totalPages": 1,
            "totalElements": 3,
            "size": 20,
            "number": 0,
            "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "first": true,
        "numberOfElements": 3,
        "empty": false
    }

## Add Product
POST /api/admin/product
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Paylod
    {
        "name": "Red Wine",
        "price": 75,
        "description": "since 1988"
    }

### Response
    {
        "id": 5,
        "createdDate": "2022-01-31T01:30:17.606+00:00",
        "updatedDate": "2022-01-31T01:30:17.606+00:00",
        "name": "Red Wine",
        "price": 75.0,
        "description": "since 1988"
    }
## Delete Product
DELETE /api/admin/product/{id}
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Path Variable
    id: product id

### Response
    Status 200

## Update Product
PUT /api/admin/product/{id}
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Path Variable
    id: product id

######Payload
    {
        "name":"Red Wine since 1988",
        "price":"200",
        "description":"From Bethlahem"
    }

### Response
    {
        "id": 4,
        "createdDate": "2022-01-31T01:29:34.000+00:00",
        "updatedDate": "2022-01-31T01:36:23.399+00:00",
        "name": "Red Wine since 1988",
        "price": 200.0,
        "description": "From Bethlahem"
    }

## News
GET /api/admin/news
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Params
    * Search: [title contnaing]
    * Page#: [default product page = 0 ]
    * Size#: [default product page size = 20]

### Response
    {
    "content": [
        {
            "id": 1,
            "createdDate": "2022-01-30T23:15:15.000+00:00",
            "updatedDate": "2022-01-30T23:15:15.000+00:00",
            "title": "test news item",
            "description": "test news item"
        },
        {
            "id": 2,
            "createdDate": "2022-01-31T01:41:42.000+00:00",
            "updatedDate": "2022-01-31T01:41:42.000+00:00",
            "title": "50% Sall on Jack Danil Hunny 750",
            "description": "No Party Tonight!"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
    }

## Add News Item
POST /api/admin/news
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Paylod
    {
        "title": "50% Sall on Jack Danil Hunny 750",
        "description": "No Party Tonight!"
    }

### Response
    {
    "id": 2,
    "createdDate": "2022-01-31T01:41:42.295+00:00",
    "updatedDate": "2022-01-31T01:41:42.296+00:00",
    "title": "50% Sall on Jack Danil Hunny 750",
    "description": "No Party Tonight!"
    }
## Delete Product
DELETE /api/admin/news/{id}
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Path Variable
    id: News Item id

### Response
    Status 200

## Update News Item
PUT /api/admin/news/{id}
##### Request
######Headers
     Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjQzNTg0ODk3LCJpYXQiOjE2NDM1NjY4OTd9.M77c_RsFHXyxhGdBW0Ekg9cy55g6d9hsF5QRdVWzv6tqvFpyJtXn5tFXYNq646Zo2i8k_zBKQcmtH7R3JpVIGg"
######Path Variable
    id: News Item id

######Payload
    {
        "title": "30% Sall on Jack Danil Hunny 750",
        "description": null
    }

### Response
    {
        "id": 2,
        "createdDate": "2022-01-31T01:41:42.000+00:00",
        "updatedDate": "2022-01-31T01:44:37.523+00:00",
        "title": "30% Sall on Jack Danil Hunny 750",
        "description": null
    }
