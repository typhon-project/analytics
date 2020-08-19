[![Build Status](http://typhon.clmsuk.com:8080/buildStatus/icon?job=TyphonAnalytics)](http://typhon.clmsuk.com:8080/job/TyphonAnalytics/)


| Task | Status | Description |
| ---- | ------ | ----------- |
| Event Publishing Integration | In progress  | Two issues were identified and assigned to CLMS to fix |
| Authorise All Task  | Future  | A task that will allow to authorise all Pre Events if users don't require the authorisation mechanism |
| How to guide | In progress | Relies on finishing integration |
| VW scenarios | Almost done | Implementation of 2 analytics scenarios as described by VW |
| Alpha scenarios | Almost done | Minor fixes required to integrate some changes in the analytics mechanism |
| OTE scenarios | In progress | OTE informed that they will try to implement those on their own. Support will be provided if needed|
| GMV scenarios | Future | Initial discussions were made but not something concrete yet |
| Run analytics using Docker | Almost done | Some fixes required regarding ip addresses of advertised listeners. Working on it at the moment. Action from DL team will be required and this will be communicated. |
| Distributed analytics | In progress - early stages | Run and test analytics in a Flink cluster |
| Use of Kubrnetes | Future | Integrate analytics infrustructure with Kubernetes |
| Strongly typed objects | In progress | Access to strongly typed objcects when writing analytics. This includs deserialisation of result sets into Java objects |
| Change tracking mechanism (Inverted Selects) | In progress | Capture and store changes introduced by update/delete DML commands into Post Event objects |
| Use of latest Flink/Kafka version | Future | Flink/Kafka hav mad some changes in their latest version which affect the analytics engine. Update the methods used to support the latest version |


