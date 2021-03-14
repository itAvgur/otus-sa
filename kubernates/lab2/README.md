Postman collection:  ./OTUS-SA.postman_collection.json

Options for testing:
- skaffold -f \
OR
- helm dependency update .\lab-chart\
  helm install myapp .\lab-chart --atomic
