pipeline {
    agent any

    stages {
        stage('Checkout branch main') {
            steps {
                git branch: 'main', url: 'https://github.com/huynopro102/identity_service.git'
            }
        }

        stage('ssh to server with user tuanhuy') {
            steps {
                sshagent(['ssh-remote-user-ubuntu']) {
                    sh '''
                     ssh -o StrictHostKeyChecking=no -l ubuntu 15.235.197.40 << 'EOF'
                            cd /home/ubuntu
                            ./deploy_identity_service.sh
                            EOF
                    '''
                }
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
