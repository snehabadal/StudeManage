
pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    options {   
        timestamps()
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Check Tools') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Package Application') {
            steps {
                bat 'mvn -B package'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: false

            archiveArtifacts artifacts: 'target/*.jar',
                             allowEmptyArchive: false
        }

        success {
            echo 'BUILD SUCCESS: Application tested and packaged.'
        }

        failure {
            echo 'BUILD FAILED: Check the Console Output.'
        }
    }
}