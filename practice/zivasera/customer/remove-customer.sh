echo "deleting service for customer app"
sudo kubectl -n dev delete svc service-app-customer
echo "Done"
echo "deleting deployment for customer app"
sudo kubectl -n dev delete deployment app-customer
echo "Done"
echo "deleting service for mysql customer db"
sudo kubectl -n dev delete svc service-mysql-customer
echo "Done"
echo "deleting deployment for mysql customer db"
sudo kubectl -n dev delete deployment deployment-mysql-customer
echo "Done"
echo "deleting persistent volume for customer app logs"
sudo kubectl -n dev delete pvc pvc-logs-customer-app
echo "Done"
echo "deleting persistent volume for mysql customer db data"
sudo kubectl -n dev delete pvc pvc-mysql-customer
echo "deleting ingress router for customer"
sudo kubectl -n dev delete ing ingress-customer-router
