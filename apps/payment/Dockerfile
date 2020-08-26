FROM cseliascosta/jdk8-alpine
MAINTAINER  Chandraprakash Sarathe "cp.saratge@gmail.com"

RUN set -e; \
mkdir -p /logs/payment && \
mkdir -p /app/payment;

COPY target/payment.jar /app/payment/

RUN set -e; \
    cd /app/payment && \
    ls -ltr;


EXPOSE 8080
EXPOSE 8081

WORKDIR /app/payment

ENTRYPOINT ["java","-Xmx512M","-jar","payment.jar"]

