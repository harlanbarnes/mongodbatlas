FROM maven:3.6.1-jdk-8

ENV MONGODB_HOSTNAME _not_set_
ENV MONGODB_USERNAME _not_set_
ENV MONGODB_PASSWORD _not_set_

ADD . /data

RUN cd /data && \
    mvn clean package

CMD ["java", "-jar", "/data/target/mongodbatlas-1.0-SNAPSHOT.jar"]
