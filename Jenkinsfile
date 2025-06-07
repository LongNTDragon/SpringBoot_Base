pipeline {
    agent any

    stages {
        stage('Show Commit Info') {
            steps {
                echo '📦 Lấy thông tin commit cuối cùng:'
                sh 'git log -1 --pretty=format:"%h - %an: %s"'
            }
        }

        stage('Final Message') {
            steps {
                echo '✅ Hello from Jenkins 🎉'
            }
        }
    }
}
