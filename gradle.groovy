def gradle_run() {
    sh 'gradle build'
    sh 'gradle bootRun &'
    sh 'sleep 10'
}

def gradle_test() {
    sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this
