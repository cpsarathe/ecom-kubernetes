###create namespace to isolate environment
sudo kubectl create namespace dev

### create persistent volume for mysql data storage

### create persistent volume to store app logs running in pods

###deleting all objects within namespaces
sudo kubectl delete all --all --namespace=dev
sudo kubectl delete pvc --all --namespace=dev
sudo kubectl delete pv --all --namespace=dev

###connecting to mysql instance
sudo kubectl exec --stdin --tty deployment-mysql-catalog-c667d75b-q5vl4 --namespace=dev -- /bin/bash
 
 mysql -u root -p

###### log into the bash of the container and connect to the mysql using root , then GRANT outside access to the required user:
 
 SELECT User,authentication_string FROM mysql.user;
  
 GRANT USAGE ON *.* TO 'root'@'%' IDENTIFIED BY PASSWORD '*F74F990CA426D83964AC1EEECBF2EF4D5108936D';

 GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'P@ss1234';


 
 ##### debugging services
sudo kubectl -n dev get service service-app-catalog -o json
   - targetPort - pod on which app running , access via locahost within pod
-  port: Node port , accessed via Cluster IP
- nodePort : port expose to outside world access via host IP
 
 sudo kubectl -n dev get endpoints service-app-catalog
 
 curl --user catalog:catalog "http://10.96.193.132:12090/catalog/product/offer-for-me"
 
 http://35.222.97.36:30090/catalog/product/offer-for-me
 
 ####Add product from postman or curl
POST - http://35.222.97.36:30090/catalog/product

Basic - catalog   catalog

BODY -
 {
   "name": "DavidOff Perfume - Cool Water by Davidoff - perfume for men - Eau de Toilette, 125ml",
   "imageUrl":"https://images-na.ssl-images-amazon.com/images/I/51c9BnD-qTL._AC_SL1024_.jpg",
   "retailPrice": 45.90,
   "salePrice": 40.00,
   "quantity": 100,
   "sellerId": "8c3fdfe8-e6c5-11ea-adc1-74sadjd"
 }

 
 #### restarting mysql deployment
sudo kubectl -n dev rollout restart deployment deployment-mysql-catalog

- if you access mysql after restart , you can see same records in product table event after restart . it assures of data persistence

 
 #### restarting catalog app deployment
 sudo kubectl -n dev rollout restart deployment app-catalog
- if you access logs in pod after restart from /logs/catalog/spring.log you can see some old logs were present before restart

#### setting up customer app
sudo ./setup-customer.sh



