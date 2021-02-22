Postman collection:  ./OTUS-SA.postman_collection.json

Options for testing:
- skaffold -f
OR
- 
        helm install postgres bitnami/postgresql -f .\db\values.yaml
        helm install myapp .\lab-chart
