#Spring samples

##Start database
mvn -Phsql exec:java

##Start messaging
mvn -Pzookeeper exec:java

mvn -Pkafka-boot exec:java

mvn -Pkafka-topics exec:java

List all kafka topics
kafka-topics.bat --list --zookeeper localhost:2181

Console producer
kafka-console-producer --broker-list localhost:9092 --topic sample