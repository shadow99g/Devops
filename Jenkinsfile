pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn -B clean package'
        archiveArtifacts(allowEmptyArchive: true, artifacts: '**/*.war')
      }
    }

    stage('test') {
      steps {
        sh 'mvn -B test'
      }
    }

    stage('Deploy') {
      steps {
        sh 'cp /root/.jenkins/workspace/DevOps_master/target/Legajos-1.0.war /opt/wildfly/standalone/deployments'
      }
    }

  }
}
