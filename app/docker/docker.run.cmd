docker run -d --name otus_app -e DB_URL=jdbc:postgresql://192.168.1.64:5432/otus -e DB_USER=postgres -e DB_PASS=test -e AUTH_CHECK_URL=http://192.168.1.64:8001/check -p 8000:8000 itavgur/otus_app:0.5.0
