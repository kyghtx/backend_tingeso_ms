pipeline{
    agent any {
    stages{
        stage('Checkout'){
            steps{
                git 'https://github.com/kyghtx/backend_tingeso_ms'
            }
        stage('Build vehicle-ms'){
            steps{
                dir('vehicle-ms'){
                    sh 'mvn clean install'
                }
                }

        }

        }



    }
    }
}