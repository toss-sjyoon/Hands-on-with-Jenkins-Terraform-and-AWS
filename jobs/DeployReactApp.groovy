pipelineJob("Deploy-React-App"){
    description("Deploys a React web application to AWS")
    logRotator {
        daysToKeep(5)
        numToKeep(20)
    }
    concurrentBuild(allowConcurrentBuild = false)
    triggers {
        scm("* * * * *"){
            ignorePostCommitHooks(ignorePostCommitHooks = false)
        }
    }
    parameters {
      stringParam("UNIQUE_ANIMAL_IDENTIFIER", defaultValue = "pepe", description = "Your unique animal identifier for this playground!")
    }
    definition {
    cpsScm {
      scm {
        git {
          branch("master")
          remote {
            credentials("${GIT_USER}")
            url("${GIT_URL}")
          }
        }
      }
      scriptPath('Jenkinsfile')
    }
  }
}
pipelineJob("Destroy-React-App"){
    description("Destroys a React web application to AWS")
    logRotator {
        daysToKeep(5)
        numToKeep(20)
    }
    concurrentBuild(allowConcurrentBuild = false)
    parameters {
      stringParam("UNIQUE_ANIMAL_IDENTIFIER", defaultValue = "pepe", description = "Your unique animal identifier for this playground!")
    }
    definition {
    cpsScm {
      scm {
        git {
          branch("master")
          remote {
            credentials("${GIT_USER}")
            url("${GIT_URL}")
          }
        }
      }
      scriptPath('destroy.Jenkinsfile')
    }
  }
}

