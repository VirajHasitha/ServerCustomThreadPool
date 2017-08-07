#TCP Client shell script

/usr/maven/apache-maven-3.5.0/bin/mvn -f tcp.client/pom.xml clean install

java -jar tcp.client/target/tcp.client-1.0-SNAPSHOT.jar

