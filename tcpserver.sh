MAXCON=$1

/usr/maven/apache-maven-3.5.0/bin/mvn -f tcp.server/pom.xml clean install

java -jar tcp.server/target/tcp.server-1.0-SNAPSHOT.jar ${MAXCON}
