FROM openjdk:8-jre-alpine

ENV DB_URL jdbc:postgresql://localhost:5432/postgres?currentSchema=teamapps
ENV SERVER_PORT 8080

COPY target/auth-server-0.0.1-SNAPSHOT.jar /usr/local/bin/auth-server.jar
COPY docker-entrypoint.sh /usr/local/bin

RUN apk add --no-cache bash
RUN ln -s usr/local/bin/docker-entrypoint.sh /
RUN ln -s usr/local/bin/auth-server.jar /
RUN chmod +x usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["sh", "-c", "/docker-entrypoint.sh ${DB_URL} ${SERVER_PORT}"]

CMD []