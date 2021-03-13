# Medusa - Generate SQL data models for testing
Run with Docker:
```she;;
docker run --rm -it -v "$PWD":/work elibouassaba/medusa
shell:>generate /work/medusa.sql
```
The `./medusa.sql` file will be generated in the current directory.