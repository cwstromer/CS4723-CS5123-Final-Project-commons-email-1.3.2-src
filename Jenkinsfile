pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deliver') {
            steps {
                echo 'pmp___: ready for deployment'
            }
        }
    }
}