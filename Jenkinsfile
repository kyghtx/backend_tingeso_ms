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
                    bat "docker push kyghtx/frontend-app"
                }
            }
        }
        stage('Deploy with Docker Compose') {
    steps {
            bat 'docker-compose down || exit 0' // Detiene cualquier despliegue previo
            bat 'docker-compose pull'           // Opcional: actualiza imágenes desde Docker Hub
            bat 'docker-compose up -d --build --remove-orphans' // Inicia en segundo plano
        
    }
}
    }

stage("SonarQube Analysis") {
    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_AUTH_TOKEN = credentials('sonarqubepass') // token correcto
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
                    bat "mvn clean verify sonar:sonar -DskipTests=false -Dsonar.projectKey=${service.key} -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.login=${env.SONAR_AUTH_TOKEN}"
                }
            }
        }
    }
}
}
