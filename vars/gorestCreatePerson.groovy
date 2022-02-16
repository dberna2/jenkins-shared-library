def call(Map config=[:]) {
    def rawBody = libraryResource 'com/dberna2/api/person/createPerson.json'
    
    def binding = [
        name: "${config.name}",
        gender: "${config.gender}",
        email: "${config.email}",
        status: "${config.status}"
    ]

    def render = renderTemplate(rawBody, binding)
    
    sh('curl -D- -u $JIRA_CREDENTIALS -X POST --data "'+ render +'" -H "Content-Type: application/json" $JIRA_URL/public/v2/users')
}