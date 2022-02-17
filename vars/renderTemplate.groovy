def call(input, binding) {
    def engine = new groovy.text.GStringTemplateEngine()
    def template = engine.createTemplate(input).make(binding)
    echo template.toString()
    return template.toString()
}