docker run --name otus_auth -e DB_URL=jdbc:postgresql://192.168.1.64:5432/otus -e DB_USER=postgres -e DB_PASS=test -p 8001:8001 itavgur/otus_auth:0.5.0
