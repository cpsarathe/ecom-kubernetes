# ecom-kubernetes
Collection of Spring Boot Applications for Basic Ecommerce Operation. Dockerization and ultimate objective to run it in Kuberntetes


# How to Access ecommerce microservice apps locally

###### 1.Add a Product

*http://localhost:8080/catalog/product*

*Basic Auth : catalog:catalog*
```
{
  "name": "Davidoff Perfume - Cool Water by Davidoff - perfume for men - Eau de Toilette, 125ml",
  "imageUrl":"https://images-na.ssl-images-amazon.com/images/I/51c9BnD-qTL._AC_SL1024_.jpg",
  "retailPrice": 45.90,
  "salePrice": 40.00,
  "quantity": 100,
  "sellerId": "8c3fdfe8-e6c5-11ea-adc1-0242ac120002"
}
```

###### 2.Register a Customer

*http://localhost:9080/customer/register*

*Basic Auth : customer:customer*
```
{
  "name": "CP SARATHE",
  "email":"cp.a1@cp.com",
  "mobile": "+97155090909",
  "dateOfBirth": "1992-09-25",
  "address": {
  	"type" : "home",
  	"address":"Al khail street , quasis park building , flat no 12 , dubai, UAE"
  } 
}

```



###### 3.Place Order

*http://localhost:10080/order/place-order*

*Basic Auth : order:order*
```
{
  "customerId": "13",
  "itemId":"eryhsfjff4a822f-0006-4b8a-b87f-3f9f63b45d3b",
  "itemPrice": 40.00,
  "itemQuantity": 2,
  "paymentType": "CreditCard"
}


```

###### 4.Make Payment

*http://localhost:11080/payment/pay*

*Basic Auth : payment:payment*
```
{"orderId":14,"customerId":13,"total":80.00,"paymentType":"CreditCard"}

```

#Dockerization - Step By Step

##Dockerize MySqlDB

###Pull MySQL DB image
docker pull mysql:5.6

###Run MySQL DB Docker Container
```
docker run --name=mysql-catalog-db -itd -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=catalog-db -e MYSQL_USER=root -e EXTRA_OPTS="--lower_case_table_names=1" -p 3309:3306  mysql:5.6

docker run --name=mysql-customer-db -itd -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=customer-db -e MYSQL_USER=root -e EXTRA_OPTS="--lower_case_table_names=1" -p 4309:3306  mysql:5.6

docker run --name=mysql-order-db -itd -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=order-db -e MYSQL_USER=root -e EXTRA_OPTS="--lower_case_table_names=1" -p 5309:3306  mysql:5.6

docker run --name=mysql-payment-db -itd -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=payment-db -e MYSQL_USER=root -e EXTRA_OPTS="--lower_case_table_names=1" -p 6309:3306  mysql:5.6

```

##Dockerize Catalog App

###build catalog app docker image
```
docker build -t "catalog:1.0" -f Dockerfile .
```

###run catalog app image container and link to MYSQL DB
```
docker run --name=app-catalog --link mysqldb:mysqldb -e MYSQL_HOST=mysqldb -e MYSQL_SCHEMA=catalog-db -itd -p 12080:8080 catalog:1.0
```

<br>
<br>

> Container sometimes gets exited without any reason , we can start it and attach to see the logs
```
docker start <container_id> 
docker attach <container_id>
```

### To verify catalog app is running or not
http://localhost:12080/catalog/product/offer-for-me
> Auth - catalog:catalog

###push catalog app to dockerhub
```
docker tag catalog:1.0 cpsarathe/demo-ecom-repo:catalog

docker push cpsarathe/demo-ecom-repo:catalog

```

##Dockerize Customer App

###build customer app docker image
```
docker build -t "customer:1.0" -f Dockerfile .
```

###run customer app image container and link to MYSQL DB
```
docker run --name=app-customer --link mysql-customer-db:mysql-customer-db -e MYSQL_HOST=mysql-customer-db -e MYSQL_SCHEMA=customer-db -itd -p 13080:9080 customer:1.0
```

<br>
<br>

> Container sometimes gets exited without any reason , we can start it and attach to see the logs
```
docker start <container_id> 
docker attach <container_id>
```

### To verify customer app is running or not
http://localhost:13080/customer/amiconnected
> Auth - customer:customer

###push customer app to dockerhub
```
docker tag customer:1.0 cpsarathe/demo-ecom-repo:customer

docker push cpsarathe/demo-ecom-repo:customer

```


##Dockerize Payment App

###build payment app docker image
```
docker build -t "payment:1.0" -f Dockerfile .
```

###run order app image container and link to MYSQL DB
```
docker run --name=app-payment --link mysql-payment-db:mysql-payment-db -e MYSQL_HOST=mysql-payment-db -e MYSQL_SCHEMA=payment-db -itd -p 15080:11080 payment:1.0
```

<br>
<br>

> Container sometimes gets exited without any reason , we can start it and attach to see the logs
```
docker start <container_id> 
docker attach <container_id>
```

### To verify order app is running or not
http://localhost:14080/payment/amiconnected
> Auth - payment:payment

###push payment app to dockerhub
```
docker tag payment:1.0 cpsarathe/demo-ecom-repo:payment

docker push cpsarathe/demo-ecom-repo:payment

```


##Dockerize Order App

###build order app docker image
```
docker build -t "order:1.0" -f Dockerfile .
```

###run order app image container and link to MYSQL DB
```
docker run --name=app-order --link mysql-order-db:mysql-order-db -e MYSQL_HOST=mysql-order-db -e MYSQL_SCHEMA=order-db -e PAYMENT_HOST=app-payment -itd -p 14080:10080 order:1.0
```

<br>
<br>

> Container sometimes gets exited without any reason , we can start it and attach to see the logs
```
docker start <container_id> 
docker attach <container_id>
```

### To verify order app is running or not
http://localhost:14080/order/amiconnected
> Auth - order:order

###push order app to dockerhub
```
docker tag order:1.0 cpsarathe/demo-ecom-repo:order

docker push cpsarathe/demo-ecom-repo:order

```
