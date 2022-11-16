def maven_build_test() {
    sh 'mvn clean install -e'
}

return this
