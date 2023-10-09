docker pull nats
docker run nats
docker network create nats

# Run
docker run --name nats --network nats --rm -p 4222:4222 -p 8222:8222 nats --http_port 8222

# Run with JetStream enabled
docker run --name nats --network nats --rm -p 4222:4222 -p 8222:8222 nats --http_port 8222 -js


# Run producer with Maven:
>  mvn exec:java
>  mvn clean compile exec:java -X




export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${JAVA_HOME}/bin:${M2_HOME}/bin:${PATH}
