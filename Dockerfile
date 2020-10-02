FROM flink:latest
WORKDIR /usrlib

# local
ADD ./ac.york.typhon.analytics/target/ac.york.typhon.analytics-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usrlib/ac.york.typhon.analytics.jar
