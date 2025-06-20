pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                
                git 'https://github.com/kyghtx/backend_tingeso_ms'
                bat "mvn clean install"
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('vehicle-ms') {
                    bat 'docker build -t kyghtx/deploy_decsecops .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dckhubpassword', variable: 'dockerpass')]) {
                    bat "docker login -u kyghtx -p ${dockerpass}"
                    bat "docker push kyghtx/deploy_decsecops"
                }
            }
        }
    }
}
