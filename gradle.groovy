def run() {
    sh 'gradle build'
    sh 'gradle bootRun &'
}

def test() {
    sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this
