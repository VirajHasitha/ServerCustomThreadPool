MAXCON=$1

mvn -f tcp.server/pom.xml clean install

java -jar tcp.server/target/tcp.server-1.0-SNAPSHOT.jar ${MAXCON}
