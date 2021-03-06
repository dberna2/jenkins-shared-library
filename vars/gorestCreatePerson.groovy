import net.sf.json.groovy.JsonSlurper

def call(Map config=[:]) {
    def rawBody = libraryResource 'com/dberna2/api/person/createPerson.json'

    def binding = [
        name: "${config.name}",
        gender: "${config.gender}",
        email: "${config.email}",
        status: "${config.status}"
    ]

    node {
        withCredentials([string(credentialsId: 'PERSON_API_ACCESS_TOKEN', variable: 'API_TOKEN')]) {
            def render = renderTemplate(rawBody, binding)

            def response = sh(
                script: "curl -X POST --data '${render}' -H \"Content-Type: application/json\" -H \"Authorization: Bearer $API_TOKEN\" $PERSON_API_URL/public/v2/users", 
                returnStdout: true
            ).trim()

            def jsonArray = new JsonSlurper().parseText(response)
            
            def element = jsonArray.getJSONObject(0)
            echo  element.get("message") + 22222
            
        }
    }
}
