FROM alpine:latest
WORKDIR .
ADD /var/jenkins_home/workspace/TyphonAnalytics/ac.york.typhon.analytics/target/ac.york.typhon.analytics-0.0.1-SNAPSHOT-jar-with-dependencies.jar ac.york.typhon.analytics-0.0.1-SNAPSHOT.jar