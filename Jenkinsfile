pipeline {
    agent any

    stages {
        stage('ssh to server with user tuanhuy') {
            steps {
                sshagent(['ssh-remote-user-ubuntu']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no -l ubuntu 15.235.197.40 "cd /home/ubuntu && ./deploy_identity_service.sh"
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