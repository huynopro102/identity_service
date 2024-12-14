pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/huynopro102/identity_service.git'
            }
        }
          stage('login dockerhub') {
                    steps {
                        withDockerRegistry(credentialsId: 'id_docker_hub', url: 'https://index.docker.io/v1/') {
                            // build and push image
                            sh 'docker build -t accgamepro1028/springboot_postgresql:v2 .'
                            sh 'docker push accgamepro1028/springboot_postgresql:v2 '
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
