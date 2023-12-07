1) helm repo add bitnami https://charts.bitnami.com/bitnami  
   helm repo update \
   helm install postgres bitnami/postgresql -f ./postgres-values.yaml

2) helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/
   helm repo update \
   kubectl create ns m \
   helm install nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace -f nginx-ingress.yaml \

3) helm install redis bitnami/redis --version 18.4.0 -f ./redis-values.yaml

4) helm install otus-sa ./otus_sa --atomic

