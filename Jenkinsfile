pipeline {
    agent any
    tools{
        gradle 'grdl'
    }

    stages{
        stage('build & test'){
            steps{
                sh 'gradle build'
                sh 'gradle bootRun &'
                sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
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

        stage('run'){
            steps{
                echo 'Running...'
                
            }
        }
        stage('test'){
            steps{
                echo 'Testing...'
            }
        }
        stage('nexus'){
            steps{
                echo 'Nexing'
            }
        }
    }
}