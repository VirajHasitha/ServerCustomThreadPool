#TCP Client shell script

mvn -f tcp.client/pom.xml clean install

java -jar tcp.client/target/tcp.client-1.0-SNAPSHOT.jar

