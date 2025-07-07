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
                bat 'start "" "C:\\Users\\nicol\\Desktop\\sonarqube-25.6.0.109173\\bin\\windows-x86-64\\StartSonar.bat"'
                bat '''
                    echo Esperando a que SonarQube esté disponible...
                    powershell -Command "& {
                        $maxRetries = 30
                        $retryCount = 0
                        while ($true) {
                            try {
                                $res = Invoke-WebRequest -Uri http://localhost:9000 -UseBasicParsing -TimeoutSec 5
                                if ($res.StatusCode -eq 200) {
                                    Write-Host 'SonarQube está listo.'
                                    break
                                }
                            } catch {
                                Write-Host 'Esperando...'
                            }
                            Start-Sleep -Seconds 5
                            $retryCount++
                            if ($retryCount -ge $maxRetries) {
                                Write-Host 'Tiempo de espera agotado.'
                                exit 1
                            }
                        }
                    }"
                '''
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

        stage('ZAP Scan') {
            steps {
                bat '''
                    echo Esperando que ZAP esté disponible...
                    powershell -Command "& {
                        $maxRetries = 30
                        $retryCount = 0
                        while ($true) {
                            try {
                                $res = Invoke-WebRequest -Uri http://localhost:8099 -UseBasicParsing -TimeoutSec 3
                                if ($res.StatusCode -eq 200) { break }
                            } catch {}
                            Start-Sleep -Seconds 5
                            $retryCount++
                            if ($retryCount -ge $maxRetries) { exit 1 }
                        }
                    }"

                    echo Iniciando escaneo ZAP...
                    curl "http://localhost:8099/JSON/ascan/action/scan/?url=http://localhost:8080&recurse=true&inScopeOnly=false"

                    echo Esperando resultados del escaneo...
                    powershell -Command "& {
                        do {
                            $status = (Invoke-RestMethod http://localhost:8099/JSON/ascan/view/status/).status
                            Write-Host ('Progreso: ' + $status + '%%')
                            Start-Sleep -Seconds 5
                        } while ($status -ne '100')
                    }"

                    echo Exportando reporte JSON...
                    curl "http://localhost:8099/OTHER/core/other/jsonreport/" -o zap-report.json

                    echo Exportando reporte HTML...
                    curl "http://localhost:8099/OTHER/core/other/htmlreport/" -o zap-report.html
                '''
                archiveArtifacts artifacts: 'zap-report.*', fingerprint: true
            }
        }

        
    }
}
