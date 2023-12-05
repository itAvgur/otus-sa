helm repo add bitnami https://charts.bitnami.com/bitnami  
helm repo update \
helm install postgres bitnami/postgresql -f ./postgres-values.yaml

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/
&& helm repo update \
&& kubectl create ns m \
&& helm install nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace -f nginx-ingress.yaml \


helm install app ./app/ --atomic

helm install auth ./auth/ --atomic

newman.cmd run ./OTUS-SA_API_Gateway.postman_collection.json
