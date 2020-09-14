[![Build Status](http://typhon.clmsuk.com:8080/buildStatus/icon?job=TyphonAnalytics)](http://typhon.clmsuk.com:8080/job/TyphonAnalytics/)

# TYPHON Analytics
This is the repository of the TYPHON Analytics (WP 5). User are able to define analytics based on queries executed against TYPHON Polystores. In addition, the architecture offers a mechanism for approval/rejection of the execution of queries.


# Current Status
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
