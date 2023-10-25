## SetUp
helm dependency update lab-chart

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts  \
helm repo update \
helm install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml --atomic

run ingress as daemon \
   helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
   && helm repo update \
   && kubectl create ns m \
   && helm install nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace -f nginx-ingress.yaml \

helm install myapp lab-chart --atomic

#### Prometheus (port-forward 9009)
http://arch.homework/monitor/prometheus
http://localhost:9090

### Graphana (port-forward 3000)
http://localhost:3000/
admin:prom-operator

dashboard: [grafana.json](grafana.json)

helm install postgres-exporter prometheus-community/prometheus-postgres-exporter -f prometheus-postgres-exporter.yaml --atomic

---

## Testing

ab -n20000 -c1000 -H "Host:arch.homework" http://arch.homework/health 

ab -n2000 -c100 -H "Host:arch.homework" http://arch.homework/health \
ab -n20000 -c100 -H "Host:arch.homework" http://arch.homework/person \
ab -n200 -c10 http://myapp-lab-three-chart:8000/nonexistent
