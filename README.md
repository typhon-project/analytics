[![Build Status](http://typhon.clmsuk.com:8080/buildStatus/icon?job=TyphonAnalytics)](http://typhon.clmsuk.com:8080/job/TyphonAnalytics/)

# TYPHON Analytics
This is the repository of the TYPHON Analytics (WP 5). User are able to define analytics based on queries executed against TYPHON Polystores. In addition, the architecture offers a mechanism for approval/rejection of the execution of queries.

# User Guide

## Prerequisites
1. This guide assumes that you have already installed all the necessary tools to create and run a Polystore (e.g., TyphonML, TyphonDL, etc.). 
2. You need to make sure that you have those updated (from their respective Eclipse update sites and by doing a docker-compose pull) to their latest version. 
3. Start by creating the Polystore as described in the previous sections. Make sure that in the appropriate step of the DL wizard you have checked the “Use Typhon Data Analytics” option, as shown in the image below.

![DL Configuration to include Analytics](/HowTo/images/DL_with_analytics.png)

4. Run the Polystore

**Warning**: The guide assumes that the Polystore is deployed using Docker Compose. The Polystore and the analytics component have not yet been fully tested using a Kubernetes deployment.

## Work with the Analytics Component
1. If working on an existing Eclipse installation (as per Prerequisites step 1) to go step 3 of this section, otherwise download Eclipse from [here](https://www.eclipse.org/downloads/packages/release/2020-06/r/eclipse-ide-java-and-dsl-developers).
2. Install the **ML** plugin by selecting Help -> Install New Software -> Work with: [http://typhon.clmsuk.com:8082/](http://typhon.clmsuk.com:8082/) and selecting the **Typhon ML** option and clicking next and finish when applicable (restarting eclipse when prompted).
3. Similarly install Epsilon from: [http://download.eclipse.org/epsilon/updates/2.2/](http://download.eclipse.org/epsilon/updates/2.2/) (as per step 2, selecting **Epsilon Core** and **Epsilon EMF** integration categories as shown below)

![Install Epsilon to your Eclipse](/HowTo/images/install_Epsilon.png)

4. Similarly install Typhon Analytics from: [http://typhon.clmsuk.com:8082/](http://typhon.clmsuk.com:8082/) (as per steps 2 or 3, selecting the **typhon analytics** category)
5. In Eclipse create a new java project to host either your analytics or authentication code:
	* Analytics:
		1. Ensure the Polystore is running
		2. Right click on the project in the project explorer and use the **Typhon** context menu, selecting **Generate analytics code** and enter the URL that the Polystore can be accessed at
		3. Right click on the project again and select Configure -> Convert to Maven Project
		4. You should be ready to edit the created stubs in the src folder to add your analytics code (the example class is called AnalyticsStub.java), executing it through the main method of the DefaultAnalyticsRunner.java class
	* Authorisation
		1. Create a flexmi model to represent your authentication chain, for example a file called authmodel.flexmi:

		```xml
		<?nsuri authDSL?>
		<chain package="test">
		<task name="Task1" next="Task2"/>
		<task name="Task2"/>
		</chain>
		```
		And place it in the project. This example model has an authentication chain made up of two tasks, the first one being Task1 and the second being Task2
		2. Right click on this model in the project explorer and use the **Typhon** context menu, selecting **Generate auth analytics code**
		3. Right click on the project again and select Configure -> Convert to Maven Project
		4. You should be ready to edit the created stubs in the src folder to add your authentication code, executing it through the main method of the PreEventAuthorizer.java class

		**Please note:** The generated docker compose defines port 29092 for external (outside Docker) access to the Kafka queue and port 9092 for internal (inside Docker) access. As this guide describes how to write analytics in your local IDE, the configuration is set to access port 29092. If you want to export the jar and run it inside Docker, then you need to open the “resources/typhonAnalyticsConfig.properties” file and set the port in line 12 to 9092. 
		
## Writing Analytics Code with Flink

**IMPORTANT!!!** Post events in Typhon are created every time a _TyphonQL query is executed_. Thus, your code will produce results, _if and only if_ you start using the Polystore and execute some TyphonQL queries.

Flink is a distributed execution framework. By using its **operators** Flink can easily distribute you code without requiring user’s input/configuration. The goal of this guide is not to train people on writing Flink code. There are plenty of resources on this online. The basic idea is that Flink works with streams (in the context of the analytics component). Steams as the name suggests, provide continuous real-time input to your programs. In the analytics component, the stream of events is the “eventsStream” parameter is the analyse method. This is configured to automatically consume all the events coming to the POST queue.

To consume streams using Flink, one should use Flink Operators. A comprehensive list of all the available Flink operators is available [here](https://ci.apache.org/projects/flink/flink-docs-stable/dev/stream/operators/). This should be the starting point of anyone trying to use Flink as they contain a brief description and an example of how to make them work. You will find yourself mostly having to use the filter and map function (the first filters events based on a condition, the second is used to transform objects to other forms). Experiment with these 2 first and then you can proceed to more complicated operators. 

Below is a simple example using an ecommerce model, that consumes Typhon PostEvents, printing them to console. The deployment folder of this example can be found at [https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial](https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial) and can be used to run a polystore with this example. 

![Example of the analyze method](/HowTo/images/analyze.png)

The analytics folder of this example can be found at [https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial_analytics](https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial_analytics) and has two runners: a simple one which will just pretty print any query sent to the polystore and one that calculates the top products over specific time windows (this is part of the models2020 tutorial found [here](https://github.com/typhon-project/MoDELS-Tutorial) containing a detailed description of this scenario and a generator of queries against a running polystore using [this model](https://github.com/typhon-project/MoDELS-Tutorial/blob/master/Analytics/ac.york.typhon.analytics.models2020.generator/src/ac/york/typhon/analytics/models2020/generator/GeneratorRunner.java)).

To execute this simple Flik program on your local machine, you can run the main method in the DefaultAnalyticsRunner.java class, and similarly you can instead run the TopVisitedProductsScenarioRunner.java class for the top products one. 

## Uploading and Running Analytics Code to Flink

Requires running Typhon Docker containers. If you haven't launched them yet, `cd` into the directory generated by using the TyphonDL plugin and containing `docker-compose.yaml` and then run `docker-compose up`. 

Once the Typhon Docker containers are up and running, reset the databases by submitting a HTTP GET request to `http://localhost:8080/api/resetdatabases` using basic authentication user `admin` and password `admin1@`. Then, wait for the reply message `true` before continuing. 

At this stage, Flink is ready to receive the **non-fat** Typhon Analytics user JAR (i.e. created in the `target` directory of your Typhon Analytics user project and by running `mvn clean package`). To submit the Typhon Analytics user application to Flink, visit http://localhost:8081/#/submit in the web browser, click on the "+ Add New" button, and select the Typhon Analytics user JAR generated by maven. Finally, make sure the correct Java class is entered in the "Entry Class" field and click on the "Submit" button.

## Uploading and Running Analytics Code to Flink (Kubernetes)

Requires running a Typhon Kubernetes cluster. To launch a Kubernetes minikube, run `minikube start --memory=8192` from your CLI. If you haven't deployed Typhon yet, `cd` into the directory generated by using the TyphonDL plugin and containing `deploy.sh` and then run `chmod +x deploy.sh` followed by `./deploy.sh`. To follow the deployment process, you may run `minikube dashboard` in a seperate CLI, and make sure to select `All namespaces` from the web UI dropdown on the left-hand side. 

Once all components of the Typhon Kubernetes cluster are up and running, setup port-forwarding in separate CLI windows/tabs:

`kubectl port-forward deployment/flink-jobmanager 8081:30539 --namespace typhon`

`kubectl port-forward deployment/polystore-ui-deployment 4200:30075 --namespace typhon`

`kubectl port-forward deployment/typhon-polystore-service-deployment 8080:30061 --namespace typhon`

`kubectl port-forward deployment/strimzi-cluster-operator 9092:9092 --namespace kafka`

`kubectl port-forward typhon-cluster-zookeeper-0 2181:2181 --namespace typhon`

`kubectl port-forward deployment/flink-jobmanager 8081:8081 --namespace typhon`

Next, reset the databases by submitting a HTTP GET request to `http://localhost:8080/api/resetdatabases` using basic authentication user `admin` and password `admin1@`. Then, wait for the reply message `true` before continuing. 

At this stage, Flink is ready to receive the **non-fat** Typhon Analytics user JAR (i.e. created in the `target` directory of your Typhon Analytics user project and by running `mvn clean package`). To submit the Typhon Analytics user application to Flink, visit http://localhost:8081/#/submit in the web browser, click on the "+ Add New" button, and select the Typhon Analytics user JAR generated by maven. Finally, make sure the correct Java class is entered in the "Entry Class" field and click on the "Submit" button.



## Write Authorisation Tasks
The new class will include two methods you need to implement called “checkCondition (Event event)” and “shouldIReject(Event event).” In the first you need to provide the logic that decides if this authorisation task is responsible for checking the event passed as parameter. For example, if this task is responsible for checking the validity of a review, then the logic included here should filter and accept only such “insert Review” events. The second, should include the rejection logic. For example, include the logic that rejects reviews that are too short (less than 10 characters long).

The auth folder of this example can be found at [https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial_authorisation](https://github.com/typhon-project/analytics/tree/master/ac.york.typhon.analytics.examples.ecommerce_tutorial_authorisation)\

![Example of an authorisation task](/HowTo/images/authTask.png)

To run the authorisation chain on your local machine, you execute the main method in the generated PreEventAuthorizer.java class found in your auth project.

**IMPORTANT!!!**
1)	Events in Typhon are created every time a _TyphonQL query is executed_. Thus, your code will produce results, _if and only if_ you start using the Polystore and execute some TyphonQL queries.
2)	The current Polystore implementation provides a default authoriser that authorises all the queries for execution. This means that if you need to include authorisation tasks as part of one of your use case scenarios then you should not run the container that authorises all the queries. This can be done by removing (or commenting out) the following service from your docker-compose file

![Default authoriser in docker-compose](/HowTo/images/defaultAuth.png)


# Current Development Status
| Task | Status | Description |
| ---- | ------ | ----------- |
| Event Publishing Integration | Done  | - |
| Authorise All Task  | Done  | A task that authorises all Pre Events if users don't require the authorisation mechanism |
| How to guide | In progress | Needs updating to include new integrations |
| VW scenarios | Almost done | Implementation of 2 analytics scenarios as described by VW |
| Alpha scenarios | Almost done | Minor fixes required to integrate some changes in the analytics mechanism |
| OTE scenarios | In progress | Weekly meeting with OTE to develop those |
| GMV scenarios | Future | Initial discussions were made but not something concrete yet |
| Run analytics using Docker | Done | - |
| Distributed analytics | Done | Needs testing with larger scenarios |
| Use of Kubrnetes | Done | Integrate analytics infrustructure with Kubernetes |
| Strongly typed objects | Almost Done | Access to strongly typed objcects when writing analytics. This includs deserialisation of result sets into Java objects |
| Change tracking mechanism (Inverted Selects) | Almost Done | Capture and store changes introduced by update/delete DML commands into Post Event objects |
| Use of latest Flink/Kafka version | Done | - |
