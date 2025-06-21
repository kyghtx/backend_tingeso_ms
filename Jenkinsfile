pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                
                git 'https://github.com/kyghtx/backend_tingeso_ms'
            }
        }

        stage('Build vehicle-ms Docker Image') {
            steps {
                dir('vehicle.ms') {
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/vehicle-ms .'
                }
            }
        }
        stage("Build config-server.ms Docker Image"){
            steps{
                dir('config-server.ms'){
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/config-server-ms .'
                }
            }
        }
        stage("Build eureka-server.ms Docker Image"){
            steps{
                dir('eureka-server'){
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/eureka-server-ms .'
                }
            }
        }
        stage("Build gateway-server.ms Docker Image"){
            steps{
                dir('gateway-server'){
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/gateway-server-ms .'
                }
            }

        }
       
        stage("Build repairs-vehicle.ms"){
            steps{
                dir('repairs-vehicle.ms'){
                    
                   bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/repairs-vehicle-ms .'
                }
            }
        }
        stage("Build reports_uh.ms"){
            steps{
                dir('reports-uh.ms'){
                
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/reports_uh-ms .'
                }
            }
        }
         stage("Build repairs-list.ms"){
            steps{
                dir('repairs-list.ms'){
                    bat "mvn clean install -DskipTests"
                    bat 'docker build -t kyghtx/repairs-list-ms .'
            }
        
            }

        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dckhubpassword', variable: 'dockerpass')]) {
                    bat "docker login -u kyghtx -p ${dockerpass}"
                    bat "docker push kyghtx/vehicle-ms"
                    bat "docker push kyghtx/config-server-ms"
                    bat "docker push kyghtx/eureka-server-ms"
                    bat "docker push kyghtx/gateway-server-ms"
                    bat "docker push kyghtx/repairs-list-ms"
                    bat "docker push kyghtx/repairs-vehicle-ms"
                    bat "docker push kyghtx/reports_uh-ms"
                }
            }
        }
    }
}
