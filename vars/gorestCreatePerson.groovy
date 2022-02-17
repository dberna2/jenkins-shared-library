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
            String url = "$PERSON_API_URL/public/v2/users"
            String contentType = 'Content-Type: application/json'
            String authorization = "Authorization: Bearer $API_TOKEN"
            def render = renderTemplate(rawBody, binding)
            def response = sh(script: "curl -D- -X POST --data '${render}' -H $contentType -H $authorization $url", returnStdout: true).trim()
            echo response
        }
    }
}
