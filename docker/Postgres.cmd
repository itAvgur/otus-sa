docker run --name otus_sa --network otus -v ~/pg/sa:/var/lib/postgresql/data -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=otus_sa -p 5432:5432 -d postgres:13.1
