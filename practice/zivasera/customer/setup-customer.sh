sudo kubectl create -f pvc-mysql-customer.yaml
sudo kubectl create -f pvc-logs-customer-app.yaml
sudo kubectl create -f deployment-mysql-customer.yaml
sudo kubectl create -f service-mysql-customer.yaml
sudo kubectl create -f deployment-customer-app.yaml
sudo kubectl create -f service-customer-app.yaml
#edit ingress router file and add /customer path entry
sudo kubectl create -f ingress-customer-router.yaml
#should not be deleting and recreating , but for sake of test we do it
sudo kubectl -n dev delete deployment deployment-nginx-controller
cd ../common
sudo kubectl -n dev create -f deployment-nginx-controller.yaml

