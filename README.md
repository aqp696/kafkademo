# Description
This project is a demo with spring boot integration and kafka based on
[this](https://dzone.com/articles/magic-of-kafka-with-spring-boot) blog post.

# Requisites
*  spring-boot 2.1.8
*  maven 3.5.4
*  java 11
*  Apache kafka

# Running
## Apache kafka
To launch the environment with Apache Kafka we will launch ZoeKeeper 
### Start Zoekeeper
Start the instance:
```bash
bin/zookeeper-server-start.sh config\zookeeper.properties 
```
### Start Apache Kafka
Start the kafka server:
```bash
bin/kafka-server-start.sh config\server.properties
```
### Start SpringBoot Application
```bash
mvn clean install
mvn spring-boot:run
```
