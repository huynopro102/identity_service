pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'accgamepro1028/springboot_postgresql:v2'
        GITHUB_REPO = 'https://github.com/huynopro102/identity_service.git'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                script {
                    // Thêm logging để debug
                    sh 'git --version'
                    sh 'pwd'

                    checkout([$class: 'GitSCM',
                        branches: [[name: '*/main']],
                        extensions: [],
                        userRemoteConfigs: [[
                            credentialsId: 'id_docker_hub',
                            url: env.GITHUB_REPO
                        ]]
                    ])
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'id_docker_hub', url: 'https://index.docker.io/v1/') {
                        sh """
                            docker build -t ${env.DOCKER_IMAGE} .
                            docker push ${env.DOCKER_IMAGE}
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
            sh 'docker logout'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}