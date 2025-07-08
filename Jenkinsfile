pipeline {
    agent any

    tools {
        maven "maven"
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_AUTH_TOKEN = credentials('sonarqubepass')
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
                            bat "mvn clean install -DskipTests"
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
                bat 'docker-compose down --remove-orphans || exit 0'
                bat 'docker-compose pull'
                bat 'docker-compose up -d --build --remove-orphans'
            }
        }

     stage('Iniciar SonarQube') {
    steps {
        echo 'Iniciando SonarQube...'
        // Iniciar SonarQube en un proceso separado para que no bloquee el paso de Jenkins.
        // El 'start ""' es crucial aquí para que el script batch se ejecute en segundo plano
        // y el paso de Jenkins pueda continuar.
        bat 'start "" "C:\\Users\\nicol\\Desktop\\sonarqube-25.6.0.109173\\bin\\windows-x86-64\\StartSonar.bat"'

        echo 'Esperando que SonarQube esté listo y accesible en http://localhost:9000...'
        // Este bloque de PowerShell espera hasta que SonarQube responda.
        // Es la parte más importante para asegurar que la siguiente etapa no falle.
        powershell '''
            $maxRetries = 30 # Número máximo de intentos
            $retryCount = 0
            $sonarQubeUrl = "http://localhost:9000"
            $sleepSeconds = 10 # Tiempo de espera entre intentos

            while ($true) {
                try {
                    $res = Invoke-WebRequest -Uri $sonarQubeUrl -UseBasicParsing -TimeoutSec 10 # Tiempo máximo para la solicitud
                    if ($res.StatusCode -eq 200) {
                        Write-Host "SonarQube está listo y accesible."
                        break # Salir del bucle si SonarQube responde
                    }
                } catch {
                    Write-Host "SonarQube aún no está listo. Reintentando... (Intento $($retryCount + 1)/$maxRetries)"
                }

                Start-Sleep -Seconds $sleepSeconds # Esperar antes del próximo intento
                $retryCount++

                if ($retryCount -ge $maxRetries) {
                    Write-Host "Tiempo de espera agotado. SonarQube no se inició a tiempo. Fallando el pipeline."
                    exit 1 # Terminar el script de PowerShell con un error, lo que fallará el paso de Jenkins
                }
            }
        '''
        echo 'SonarQube ha sido iniciado y está completamente listo para usar.'
    }
}


        stage("SonarQube Analysis") {
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
                            bat "mvn sonar:sonar -Dsonar.projectKey=${service.key} -Dsonar.java.binaries=target/classes -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.login=${env.SONAR_AUTH_TOKEN}"
                        }
                    }
                }
            }
        }

        
    }
}
