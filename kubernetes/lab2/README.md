

1) create name space
    kubectl create ns app

2) run postgres if needed (optional here)
   docker run --name otus_sa --network otus -v ~/pg_db:/var/lib/postgresql/data -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=otus_sa -p 5433:5432 -d postgres:13.1

3) run ingress as daemon
 helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ 
   && helm repo update 
   && kubectl create ns m
   && helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx_ingress.yaml

4) Finally, run apply manifests
skaffold run
OR
helm dependency update ./lab-chart
helm install myapp ./lab-chart --atomic -f ./lab-chart/values.yaml

Testing:

minikube tunnel
newman run OTUS-SA.postman_collection.json
