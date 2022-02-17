def call(Map config=[:]) {
    def rawBody = libraryResource 'com/dberna2/api/person/createPerson.json'
    echo rawBody
    node {
        withCredentials([string(credentialsId: 'PERSON_API_ACCESS_TOKEN', variable: 'TOKEN')]) {

            def binding = [
                name: "${config.name}",
                gender: "${config.gender}",
                email: "${config.email}",
                status: "${config.status}"
            ]
            echo "binding"
            echo binding.toString()
            echo "binding end"
            def render = renderTemplate(rawBody, binding)
        
            sh('curl -D- -X POST --data "'+render+'" -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" $PERSON_API_URL/public/v2/users')
        }
    }
}