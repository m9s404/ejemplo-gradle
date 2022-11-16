def grdl_s
def mvn_s

pipeline {
    agent any
    tools{
        gradle 'grdl'
        maven 'maven'
    }
    parameters{
        choice(name: 'Build_Tool', choices: ['maven', 'gradle'], description: '')
        booleanParam(name: 'PushToNexus', defaultValue: false, description: '')

    }

    stages{
        stage("Load Scripts"){
            steps{
                script {
                    mvn_s = load "maven.groovy"
                    grdl_s = load "gradle.groovy"
                }
            }

        }
        stage('build & test'){
            when{
                expression {
                    params.Build_Tool == 'maven'
                }
            }
            steps{
                script{
                    mvn_s.maven_build_test()
                }
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
            when{
                expression {
                    params.Build_Tool == 'gradle'
                }
            }
            steps{
                script{
                    grdl_s.gradle_run()
                    grdl_s.gradle_test()
                }
            }
        }
        
        stage('nexus'){
            when{
                expression {
                    params.PushToNexus
                }
            }
            steps{
                nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.4']]]
            }
        }
    }
}