FROM flink:latest
WORKDIR /opt/flink/lib

# local
ADD --chown=flink:flink ./ac.york.typhon.analytics/target/ac.york.typhon.analytics-0.0.1-SNAPSHOT-jar-with-dependencies.jar /opt/flink/lib/ac.york.typhon.analytics.jar
