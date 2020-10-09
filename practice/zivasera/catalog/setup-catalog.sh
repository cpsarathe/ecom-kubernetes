sudo kubectl create -f pvc-mysql-catalog.yaml
sudo kubectl create -f pvc-logs-catalog-app.yaml
sudo kubectl create -f deployment-mysql-catalog.yaml
sudo kubectl create -f service-mysql-catalog.yaml
sudo kubectl create -f deployment-catalog-app.yaml
sudo kubectl create -f service-catalog-app.yaml
#edit ingress router file and add /catalog path entry
sudo kubectl create -f ingress-catalog-router.yaml
#should not be deleting and recreating , but for sake of test we do it
sudo kubectl -n dev delete deployment deployment-nginx-controller
cd ../common
sudo kubectl -n dev create -f deployment-nginx-controller.yaml

