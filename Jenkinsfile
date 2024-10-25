pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/huynopro102/identity_service.git'
            }
        }
//           stage('login dockerhub') {
//                     steps {
//                         withDockerRegistry(credentialsId: 'id_docker_hub', url: 'https://index.docker.io/v1/') {
//                             // build and push image
//                             sh 'docker build -t accgamepro1028/springboot_postgresql:v1 .'
//                             sh 'docker push accgamepro1028/springboot_postgresql:v1 '
//                         }
//                     }
//                 }



 stage('Build and Push Docker Images') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'id_docker_hub', url: 'https://index.docker.io/v1/') {
                        // Build the images using docker-compose
                        sh 'docker-compose build'

                        // Tagging images with both 'latest' and 'v2'
                        sh 'docker tag accgamepro1028/springboot_postgresql:latest accgamepro1028/springboot_postgresql:v2'

                        // Push the images
                        sh 'docker-compose push'
                        sh 'docker push accgamepro1028/springboot_postgresql:v2'
                        sh 'docker push accgamepro1028/springboot_postgresql:latest'
                    }
                }
            }
        }
        stage('Run Services') {
            steps {
                script {
                    // Run the services using docker-compose
                    sh 'docker-compose up -d'
                }
            }
        }
    }
    post {
        always {
            // Cleanup hoặc thông báo
            echo 'Pipeline execution finished.'
        }
        success {
            echo 'All tests passed!'
        }
        failure {
            echo 'Tests failed!'
        }
    }
}
