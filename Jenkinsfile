pipeline {
    agent any
    options {
            skipStagesAfterUnstable()
        }
    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deliver') {
            steps {
                //echo 'pmp___: ready for deployment'
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}