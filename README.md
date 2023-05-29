### Instruction 
To build and run application, system requirement must be install
- Java JDK 11
- Maven
- Docker (Nice to have, I am already support docker compose to start mysql and service),
 if you don't have docker in local computer, you need to install mysql server from version 5 or higher,
 with port 3306, init datbase with command,
 and config username/password : order_service/123456789 

         CREATE DATABASE IF NOT EXISTS order_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
         GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
## Here is command to start application by docker
 1 . `docker-compose build mysql` 

 2 . `docker-compose up mysql`

 3 . `mvn clean install` 

 4 . `docker-compose build order`

 5 . `docker-compose up order`

Alternatively, you can import project to intellij, start mysql and run in the traditional way 
 

### Database design 
![Screenshot 2023-05-28 at 20 43 02](https://github.com/thanhtung0201/orders/assets/4926015/d011156e-3f97-46f0-9bd9-e2f4dc9fc3ad)

`user_info` : Contain user-information, include email using to receive email notification when order update status, dummy data will be created when start application 

`products` : Contain listing products using for order system, dummy data will be created when start application 

`orders` : Contain information about customer order, when user make an order, data created when call API

`order_product` : Intersec table to link product with order, many-to-many relationship

### Database migration 
Service use liquibase to automaticaly run auto migration when application start, include created schema, dummy data
File migration is store in folder classpath:resources/db/sources

### Security
For simplicity, assume user will need to provide username/password (basic auth) in order to access all API, dummy username/password is admin/123456

### API Design 

`createdOrder` : User make an order to system, input is object contain information about user_id, shipping_address, listing products user want to reserved. Here is curl 

`curl --location 'http://localhost:8080/orders' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46MTIzNDU2' \
--header 'Cookie: JSESSIONID=9C15106519751F602D65848FE799F16B' \
--data '{
    "user_id" : 1,
    "shipping_address" : "90 NT, Thanh Xuan, Ha Noi, Viet Nam",
    "products" : [
        {
            "product_id" : 1,
            "quantity" : 3
        },
{
            "product_id" : 2,
            "quantity" : 4
        }
    ]
}'`

`filterOrderByCode` : After create order, user can be filted the order by code, by make simple curl request, with path params 1685371160344A08B3B9 for example is order code is result by call API createdOrder

`curl --location 'http://localhost:8080/orders/1685371160344A08B3B9' \
--header 'Authorization: Basic YWRtaW46MTIzNDU2' \
--header 'Cookie: JSESSIONID=9C15106519751F602D65848FE799F16B'`

`updateOrderStatus` : After place order, user could be changed order status, i also build simple rule to validate status pass to API
 1. User can not update order created by an other user, example pass user_id = 2 to order with id = 1 will return 403 no permission
 2. Status of also has order with PLACED --> CONFIRMED --> DELIVERED --> SHIPPED, if current state of order is CONFIRMED, the status call to updateAPI only has value DELIVERED

`curl --location --request PUT 'http://localhost:8080/orders/1?user_id=1&status=DELIVERED' \
--header 'Authorization: Basic YWRtaW46MTIzNDU2' \
--header 'Cookie: JSESSIONID=9C15106519751F602D65848FE799F16B'`

It is should be run from createdOrder -> filterOrderByCode -> updateOrderStatus





