pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs() 
                git url: 'https://github.com/kyghtx/backend_tingeso_ms',
                branch: 'main',
                changelog: false,
                poll: false
            }
        }
       
        stage('Build frontend') {
            steps {
                dir('frontend-tingeso-ms') {
                    bat 'npm install'
                    bat 'npm run build'
                    bat 'docker build -t kyghtx/frontend-app .'
                }
            }
        }
        stage("SonarQube Analysis") {
            environment {
                SONAR_HOST_URL = 'http://sonarqube:9000'
                SONAR_AUTH_TOKEN = credentials('sonarqubepass')
            }
            steps {
                script {
                    def services = [
                        [dir: 'vehicle.ms', key: 'vehicle-ms'],
                        [dir: 'config-server.ms', key: 'config-server-ms'],
                        [dir: 'eureka-server', key: 'eureka-server-ms'],
                        [dir: 'gateway-server', key: 'gateway-server-ms'],
                        [dir: 'repairs-list.ms', key: 'repairs-list-ms'],
                        [dir: 'repairs-vehicle.ms', key: 'repairs-vehicle-ms'],
                        [dir: 'reports-uh.ms', key: 'reports_uh-ms']
                    ]

                    for (service in services) {
                        dir(service.dir) {
                            bat "mvn clean install -DskipTests"
                            bat "mvn sonar:sonar -Dsonar.projectKey=${service.key} -Dsonar.java.binaries=target/classes -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.login=${env.SONAR_AUTH_TOKEN}"
                        }
                    }
                }
            }
        }

        stage("Build Docker Images") {
            steps {
                script {
                    def services = [
                        [dir: 'vehicle.ms', image: 'kyghtx/vehicle-ms'],
                        [dir: 'config-server.ms', image: 'kyghtx/config-server-ms'],
                        [dir: 'eureka-server', image: 'kyghtx/eureka-server-ms'],
                        [dir: 'gateway-server', image: 'kyghtx/gateway-server-ms'],
                        [dir: 'repairs-list.ms', image: 'kyghtx/repairs-list-ms'],
                        [dir: 'repairs-vehicle.ms', image: 'kyghtx/repairs-vehicle-ms'],
                        [dir: 'reports-uh.ms', image: 'kyghtx/reports_uh-ms']
                    ]

                    for (service in services) {
                        dir(service.dir) {
                            bat "docker build -t ${service.image} ."
                        }
                    }
                }
            }
        }

        stage('Build frontend Docker Image') {
            steps {
                dir('frontend-tingeso-ms') {
                    bat 'docker build -t kyghtx/frontend-app .'
                }
            }
        }

        stage('Push Docker Images') {
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
                    bat "docker push kyghtx/frontend-app"
                }
            }
        }
            


        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose down || exit 0'
                bat 'docker-compose pull'
                bat 'docker-compose up -d --build --remove-orphans'
            }
        }

    }
}
