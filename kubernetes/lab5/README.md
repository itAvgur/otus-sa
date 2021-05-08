helm install postgres bitnami/postgresql -f .\postgres-values.yaml

helm install app .\app\ --atomic
helm install auth .\auth\ --atomic

newman.cmd run .\OTUS-SA_API_Gateway.postman_collection.json
