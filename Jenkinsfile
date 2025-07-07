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
                // Iniciar SonarQube
                bat 'start "" "C:\\Users\\nicol\\Desktop\\sonarqube-25.6.0.109173\\bin\\windows-x86-64\\StartSonar.bat"'

                // Crear un script PowerShell temporal para esperar a que esté disponible
                bat '''
                    echo $maxRetries = 30 > wait_sonar.ps1
                    echo $retryCount = 0 >> wait_sonar.ps1
                    echo while ($true) { >> wait_sonar.ps1
                    echo ^    try { >> wait_sonar.ps1
                    echo ^        $response = Invoke-WebRequest -Uri http://localhost:9000 -UseBasicParsing -TimeoutSec 5 >> wait_sonar.ps1
                    echo ^        if ($response.StatusCode -eq 200) { >> wait_sonar.ps1
                    echo ^            Write-Host "SonarQube esta listo." >> wait_sonar.ps1
                    echo ^            break >> wait_sonar.ps1
                    echo ^        } >> wait_sonar.ps1
                    echo ^    } catch { >> wait_sonar.ps1
                    echo ^        Write-Host "Esperando..." >> wait_sonar.ps1
                    echo ^    } >> wait_sonar.ps1
                    echo ^    Start-Sleep -Seconds 5 >> wait_sonar.ps1
                    echo ^    $retryCount++ >> wait_sonar.ps1
                    echo ^    if ($retryCount -ge $maxRetries) { >> wait_sonar.ps1
                    echo ^        Write-Host "Tiempo de espera agotado." >> wait_sonar.ps1
                    echo ^        exit 1 >> wait_sonar.ps1
                    echo ^    } >> wait_sonar.ps1
                    echo } >> wait_sonar.ps1

                    powershell -ExecutionPolicy Bypass -File wait_sonar.ps1
                    del wait_sonar.ps1
                '''
            }
        }

        stage("SonarQube Analysis") {
            environment {
                SONAR_HOST_URL = 'http://localhost:9000'
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
                            bat "mvn sonar:sonar -Dsonar.projectKey=${service.key} -Dsonar.java.binaries=target/classes -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.login=${env.SONAR_AUTH_TOKEN}"
                        }
                    }
                }
            }
        }
    }
}
