# Medusa - Generate random SQL data models
Run with Docker:
```shell
docker run --rm -it -v "$PWD":/work elibouassaba/medusa
```
Generate for Oracle:
```shell
curl -X GET "http://localhost:8080/oracle"
```
Generate for MySQL:
```shell
curl -X GET "http://localhost:8080/mysql"
```
Check http://localhost:8080/swagger-ui.html for additional parameters.
