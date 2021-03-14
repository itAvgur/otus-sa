\\wsl$\docker-desktop\mnt\version-pack\containers\services\docker\rootfs\opt
docker run -p 9090:9090 -v /opt/prometheus.yaml:/etc/prometheus/prometheus.yml prom/prometheus
*
httperf.exe --hog --port 8000 --uri /health --rate 50 --timeout 5 --wsess=100,10,0.1
*
helm dependency update .\lab-chart\
* 
helm install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml --atomic /
helm install nginx ingress-nginx/ingress-nginx -f .\nginx-ingress.yaml --atomic /
helm install myapp .\lab-chart --atomic
*
helm install postgres-exporter prometheus-community/prometheus-postgres-exporter -f .\prometheus-postgres-exporter.yaml --atomic /
