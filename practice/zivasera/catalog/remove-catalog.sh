echo "deleting service for catalog app"
sudo kubectl -n dev delete svc service-app-catalog
echo "Done"
echo "deleting deployment for catalog app"
sudo kubectl -n dev delete deployment app-catalog
echo "Done"
echo "deleting service for mysql catalog db"
sudo kubectl -n dev delete svc service-mysql-catalog
echo "Done"
echo "deleting deployment for mysql catalog db"
sudo kubectl -n dev delete deployment deployment-mysql-catalog
echo "Done"
echo "deleting persistent volume for catalog app logs"
sudo kubectl -n dev delete pvc pvc-logs-catalog-app
echo "Done"
echo "deleting persistent volume for mysql catalog db data"
sudo kubectl -n dev delete pvc pvc-mysql-catalog
echo "deleting ingress router for customer"
sudo kubectl -n dev delete ing ingress-catalog-router
