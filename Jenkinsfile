pipeline {
    agent any
    tools{
        gradle 'grdl'
    }

    stages{
        stage('build & test'){
            steps{
                sh 'mvn clean install -e'
            }
        }

        stage('sonar'){
            steps{
                withSonarQubeEnv(credentialsId: 'SoniSecret', installationName: 'Sonita') {
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
                    -Dsonar.target=sonar.java.binaries \
                    -Dsonar.projectKey=ejemplo-gradle \
                    -Dsonar.java.binaries=build'
                }     
            }
        }

        stage('run & Test'){
            steps{
                echo 'Running...'
                // sh 'gradle build'
                // sh 'gradle bootRun &'
                // sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
            }
        }
        }
        stage('nexus'){
            steps{
                echo 'Nexing'
            }
        }
    }
}