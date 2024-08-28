export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven

export PATH=${JAVA_HOME}/bin:${M2_HOME}/bin:${PATH}



>  mvn clean
>  mvn compiler:compile