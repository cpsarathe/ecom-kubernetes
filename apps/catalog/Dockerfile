FROM cseliascosta/jdk8-alpine
MAINTAINER  Chandraprakash Sarathe "cp.saratge@gmail.com"

RUN set -e; \
mkdir -p /logs/catalog && \
mkdir -p /app/catalog;

COPY target/catalog.jar /app/catalog/

RUN set -e; \
    cd /app/catalog && \
    ls -ltr;


EXPOSE 8080
EXPOSE 8081

WORKDIR /app/catalog

ENTRYPOINT ["java","-Xmx512M","-jar","catalog.jar"]

