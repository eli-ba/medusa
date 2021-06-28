# Medusa - Generate random SQL data models
## Run with Docker
Build:
```shell
docker build -t medusa .
```
Run:
```shell
docker run --rm -it -v "$PWD":/work medusa
```
## Generate for MySQL:
```shell
curl -X GET "http://localhost:8080/mysql"
```
## Generate for Oracle:
```shell
curl -X GET "http://localhost:8080/oracle"
```
Check http://localhost:8080/swagger-ui.html for additional parameters.
