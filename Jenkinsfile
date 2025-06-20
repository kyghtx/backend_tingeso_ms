pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                
                git 'https://github.com/kyghtx/backend_tingeso_ms'
                sh "mvn clean install"
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('vehicle-ms') {
                    sh 'docker build -t kyghtx/deploy_decsecops .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dckhubpassword', variable: 'dockerpass')]) {
                    sh "docker login -u kyghtx -p ${dockerpass}"
                    sh "docker push kyghtx/deploy_decsecops"
                }
            }
        }
    }
}
