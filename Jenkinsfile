pipeline{
    agent any {
    tools {
        maven "maven"
    }
    stages{
    /*checkout del repositorio.*/
        stage('Checkout'){
            steps{
                git 'https://github.com/kyghtx/backend_tingeso_ms'
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kyghtx/backend_tingeso_ms']])
                sh "mvn clean install"
            }
        }

          stage('Build vehicle-ms'){
            steps{
                dir('vehicle-ms'){
                    sh 'docker build -t kyghtx/deploy_decsecops . ' /*Se genera la imagen de docker*/
                    stage("Push Docker Image"){
                    withCredentials([string(credentialsId: 'dckhubpassword', variable: 'dockerpass')]) {
                        sh "docker login -u kyghtx -p ${dockerpass}"

                    }
                    sh "docker push kyghtx kyghtx/deploy_decsecops"
                    }

                     }

                }
                }

        }

        }



    }
    }
}