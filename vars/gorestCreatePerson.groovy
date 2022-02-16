def call(Map config=[:]) {
    def rawBody = libraryResource 'com/dberna2/api/person/createPerson.json'
    
    def binding = [
        name: "${config.name}",
        gender: "${config.gender}",
        email: "${config.email}",
        status: "${config.status}"
    ]

    def render = renderTemplate(rawBody, binding)
    
    sh('curl -i -H "Authorization: Bearer $PERSON_API_ACCESS_TOKEN" -H "Content-Type: application/json" -X POST $PERSON_API_URL/public/v2/users --data "'+ render +'" ')
}