pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                //bat 'mvn test'
                bat 'mvn -Dtest="TestSquare,TestCi*le" test'
            }
        }

        stage('Deliver') {
            steps {
                echo 'pmp___: ready for deployment'
            }
        }
    }
}