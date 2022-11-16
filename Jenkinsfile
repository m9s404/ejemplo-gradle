import groovy.json.JsonSlurperClassic

def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}
pipeline {
    agent any
    tools {
    maven 'Maven-1' 
    }

    
    stages {
        stage('SCM') {
            steps {
                git branch: 'sonar-feature', changelog: false, poll: false, url: 'https://github.com/m9s404/ejemplo-maven.git'
            }
        }

    
        stage ('Build') {
        steps {
            sh 'mvn clean package'
            }
        }
    

        stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv(credentialsId: 'SoniSecret', installationName: 'Sonita') {
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
                -Dsonar.target=sonar.java.binaries'
            }
            }
        
        } 
}

}