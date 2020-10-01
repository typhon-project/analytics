pipeline {
    agent { dockerfile true }
node {
  env.JAVA_HOME="${tool 'adopt-openjdk8'}"
  env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
  if (env.BRANCH_NAME == "master") {
    env.MAVEN_OPTS= "-Dmaven.repo.local=/var/jenkins_home/workspace/.m2-analytics-master -Djava.awt.headless=true"
  }
    try{
    notifyBuild()
    
    stage('Clone') {
        checkout scm
    }

    stage('Build typhon-analytics') {
	    configFileProvider(
        	[configFile(fileId: 'c262b5dc-6fc6-40eb-a271-885950d8cf70', variable: 'MAVEN_SETTINGS')]) {
            sh 'cd ac.york.typhon.analytics && mvn -U -B -gs $MAVEN_SETTINGS clean install'
	    }
    }

    stage('Deploying') {
        if (env.BRANCH_NAME == "feature/jenkinsfile-and-dockerfile") {
            configFileProvider(
                [configFile(fileId: 'c262b5dc-6fc6-40eb-a271-885950d8cf70', variable: 'MAVEN_SETTINGS')]) {
                sh 'docker build -t universityofyork/typhon-analytics .'
                sh 'docker login --username jsnemo'
                sh 'docker push universityofyork/typhon-analytics:latest'
            }
        }

    }
    
    }catch (e){
        currentBuild.result = "FAILED"
        throw e
    } finally {
        notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus ='STARTED'){
    buildStatus = buildStatus ?: 'SUCCESS'

    def color

    if (buildStatus == 'STARTED') {
        color = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

    slackSend(color: color, message: msg)
}