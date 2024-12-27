pipeline {
    agent any

    stages {
        stage('Checkout branch main') {
            steps {
                git branch: 'main', url: 'https://github.com/huynopro102/identity_service.git'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
        }
        success {
            echo 'Code checkout completed successfully!'
        }
        failure {
            echo 'Code checkout failed!'
        }
    }
}
