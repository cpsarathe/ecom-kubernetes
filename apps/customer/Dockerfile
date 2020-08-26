FROM cseliascosta/jdk8-alpine
MAINTAINER  Chandraprakash Sarathe "cp.saratge@gmail.com"

RUN set -e; \
mkdir -p /logs/customer && \
mkdir -p /app/customer;

COPY target/customer.jar /app/customer/

RUN set -e; \
    cd /app/customer && \
    ls -ltr;


EXPOSE 8080
EXPOSE 8081

WORKDIR /app/customer

ENTRYPOINT ["java","-Xmx512M","-jar","customer.jar"]

