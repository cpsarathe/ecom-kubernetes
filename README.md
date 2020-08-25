# ecom-kubernetes
Collection of Spring Boot Applications for Basic Ecommerce Operation. Dockerization and ultimate objective to run it in Kuberntetes

# How to Access ecommerce microservice apps

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
