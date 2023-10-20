
FROM postgres:latest

COPY init.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_DB sapiencia
ENV POSTGRES_USER scotia
ENV POSTGRES_PASSWORD scotiapass

EXPOSE 5432