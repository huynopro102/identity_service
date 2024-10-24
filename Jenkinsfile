pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Lấy mã nguồn từ repository
                git 'https://github.com/huynopro102/identity_service'
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
