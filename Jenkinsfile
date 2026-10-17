pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Check Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn -B -DskipTests compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Package Application') {
            steps {
                bat 'mvn -B -DskipTests package'
            }
        }
    }

    post {
        always {
            junit(
                testResults: '**/target/surefire-reports/TEST-*.xml',
                allowEmptyResults: true
            )
        }

        success {
            archiveArtifacts(
                artifacts: 'target/*.jar',
                fingerprint: true
            )
            echo 'SUCCESS: Build and tests completed.'
        }

        failure {
            echo 'FAILED: Check the Console Output and test reports.'
        }
    }
}
