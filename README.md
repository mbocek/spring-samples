#Spring samples

##Start database
mvn -Phsql exec:java

##Start messaging
mvn -Pzookeeper exec:java
mvn -Pkafka exec:java

