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
*
ab -n20000 -c1000 -H "Host:arch.homework" http://nginx-ingress-nginx-controller/health /
while true; do ab -n2000 -c100 -H "Host:arch.homework" http://nginx-ingress-nginx-controller/health; sleep 3; done
while true; do ab -n20000 -c1000 -H "Host:arch.homework" http://nginx-ingress-nginx-controller/person; sleep 20; done
while true; do ab -n200 -c10 http://myapp-lab-three-chart:8000/nonexistent; sleep 60; done
