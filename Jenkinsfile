pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Lấy mã nguồn từ repository
                git 'https://github.com/huynopro102/identity_service'
            }
        }

        stage('Build Project') {
            steps {
                // Build ứng dụng Spring Boot
                sh './mvnw clean package'  // Hoặc 'mvn clean package' nếu không sử dụng Maven Wrapper
            }
        }

        stage('Run Application') {
            steps {
                // Chạy ứng dụng Spring Boot trong background
                sh 'java -jar target/your-app.jar &'
                sleep 10  // Đợi một chút để ứng dụng khởi động
            }
        }

        stage('Run API Tests with Newman') {
            steps {
                // Chạy kiểm thử với Newman
                sh 'newman run path/to/your_collection.json'
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
