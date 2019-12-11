pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh 'mvn -B clean package'
        archiveArtifacts(allowEmptyArchive: true, artifacts: '**/*.war')
      }
    }

  }
}