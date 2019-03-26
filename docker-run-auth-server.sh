#!/usr/bin/env bash

docker run -d -it -p 8080:8080 -e DB_URL="jdbc:postgresql://192.168.0.118:5432/postgres?currentSchema=teamapps" -e SERVER_PORT=8080 --name=auth-server auth-server
