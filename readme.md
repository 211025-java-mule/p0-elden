docker run -it --rm -p 5432:5432 -e POSTGRES_USER=dota -e POSTGRES_PASSWORD=dota --name dota -v /$(pwd)/schema.sql:/docker-entrypoint-initdb.d/schema.sql postgres

docker exec -it dota psql -U dota