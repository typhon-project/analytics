FROM flink:1.11.2
WORKDIR /opt/flink/lib

RUN mkdir /opt/flink/usrlib
RUN chown flink:flink /opt/flink/usrlib

# local
ADD --chown=flink:flink ./ac.york.typhon.analytics/target/ac.york.typhon.analytics-0.0.1-SNAPSHOT-jar-with-dependencies.jar /opt/flink/lib/ac.york.typhon.analytics.jar
