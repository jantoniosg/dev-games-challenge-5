#!groovy

// https://github.com/rh-integration/3scale-toolbox-jenkins
// Jenkins Shared Library to call the 3scale toolbox
// This Jenkins Shared Library helps pipelines writers to call the 3scale toolbox.
//
// How to use this shared library
// First, reference this library from your Jenkins pipeline by adding to the beginning of your pipeline:

library identifier: '3scale-toolbox-jenkins@master',
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: 'https://github.com/rh-integration/3scale-toolbox-jenkins.git'])
// Declare a global variable that will hold the ThreescaleService object so that you can use it from the different stages of your pipeline.

def service = null

// Create the ThreescaleService with all the relevant information:
node() {
    stage("checkout") {
        checkout scm
    }
  stage("Prepare") {
    service = toolbox.prepareThreescaleService(
        openapi: [ filename: "openapi.yaml" ],
        environment: [ baseSystemName: "my_service" ],
        toolbox: [ openshiftProject: "toolbox",
                   destination: "3scale-tenant",
                   secretName: "3scale-toolbox" ],
        service: [:],
        applications: [
            [ name: "dev-games-challenge-5", description: "This is used for Developer Games", plan: "test", account: "<CHANGE_ME>" ]
        ],
        applicationPlans: [
          [ systemName: "test", name: "Test", defaultPlan: true, published: true ],
          [ systemName: "silver", name: "Silver" ],
          [ artefactFile: "https://raw.githubusercontent.com/redhatHameed/API-Lifecycle-Mockup/master/testcase-01/plan.yaml"],
        ]
    )

    echo "toolbox version = " + service.toolbox.getToolboxVersion()
  }

// openapi.filename is the path to the file containing the OpenAPI Specification
// environment.baseSystemName is use to compute the final system_name, based on the environment name (environment.environmentName) and the API major version (from the OpenAPI Specification field info.version)
// toolbox.openshiftProject is the OpenShift project in which Kubernetes Jobs will be created
// toolbox.secretName is the name of the Kubernetes Secret containing the 3scale_toolbox configuration file
// toolbox.destination is the name of the 3scale_toolbox remote
// applicationPlans is a list of Application Plans to create, by using artefact yaml File or by providing application plan properties details.
// Create the corresponding OpenShift project:

// oc new-project toolbox

// Generate the toolbox configuration file and create the Kubernetes Secret:

// 3scale remote add 3scale-tenant https://$TOKEN@$TENANT.3scale.net/
// oc create secret generic 3scale-toolbox --from-file=$HOME/.3scalerc.yaml

// Add a stage to provision the service in 3scale:

  stage("Import OpenAPI") {
    service.importOpenAPI()
    echo "Service with system_name ${service.environment.targetSystemName} created !"
  }

// Add a stage to create the Application Plans:

  stage("Create an Application Plan") {
    service.applyApplicationPlans()
  }

// Add a global variable and a stage to create the test Application:

  stage("Create an Application") {
    service.applyApplication()
  }

// Add a stage to run your integration tests:

  stage("Run integration tests") {
    // To run the integration tests when using APIcast SaaS instances, we need
    // to fetch the proxy definition to extract the staging public url
    def proxy = service.readProxy("sandbox")
    sh """set -e +x
    curl -f -w "Hello: %{http_code}\n" -o /dev/null -s ${proxy.sandbox_endpoint}/hello -H 'api-key: ${service.applications[0].userkey}'
    curl -f -w "HelloName: %{http_code}\n" -o /dev/null -s ${proxy.sandbox_endpoint}/hello/devGames -H 'api-key: ${service.applications[0].userkey}'
    curl -f -w "Goodbye: %{http_code}\n" -o /dev/null -s ${proxy.sandbox_endpoint}/api/beer/goodbye -H 'api-key: ${service.applications[0].userkey}'
    """
  }

// Add a stage to promote your API to production:

  stage("Promote to production") {
    service.promoteToProduction()
  }
}