def call(Map config = [:]) {

    def scriptcontents = loadResource("${config.env}", "${config.name}")
    
    writeFile file: "${config.name}", text: scriptcontents
    sh "chmod a+x ./${config.name}"
}

def loadResource(String env, String  fileName) {
    if(env == 'linux') {
        echo "Hello Tux!"
        def scriptcontents = libraryResource "com/dberna2/scripts/linux/${config.name}.sh"
        return scriptcontents
    }else {
        echo "Hello Windows!"
        def scriptcontents = libraryResource "com/dberna2/scripts/windows/${config.name}.bat"
        return scriptcontents
    }
}
