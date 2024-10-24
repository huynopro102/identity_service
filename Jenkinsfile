pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/huynopro102/identity_service.git'
            }
        }
           stage('Run Tests') {
                    steps {
                        // Chạy kiểm thử với Newman
                        sh 'newman run identity.postman_collection.json'
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
