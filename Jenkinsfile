pipeline {
    agent any
    tools{
        gradle 'grdl'
        maven 'maven'
    }

    stages{
        stage('build & test'){
            steps{
                sh 'mvn clean install -e'
            }
        }

        stage('sonar'){
            steps{
                withSonarQubeEnv(credentialsId: 'sonar_tkn', installationName: 'sonarserver') {
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
                    -Dsonar.target=sonar.java.binaries \
                    -Dsonar.projectKey=ejemplo-gradle \
                    -Dsonar.java.binaries=build'
                }     
            }
        }

        stage('run & Test'){
            steps{
                // echo 'Running...'
                sh '''
                gradle build
                gradle bootRun &
                curl -X GET "http://localhost:8081/rest/mscovid/test?msg=testing"
                '''
            }
        }
        
        stage('nexus'){
            steps{
                nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]], tagName: '0.0.2'
            }
        }
    }
}