FROM cseliascosta/jdk8-alpine
MAINTAINER  Chandraprakash Sarathe "cp.saratge@gmail.com"

RUN set -e; \
mkdir -p /logs/order && \
mkdir -p /app/order;

COPY target/order.jar /app/order/

RUN set -e; \
    cd /app/order && \
    ls -ltr;


EXPOSE 8080
EXPOSE 8081

WORKDIR /app/order

ENTRYPOINT ["java","-Xmx512M","-jar","order.jar"]

