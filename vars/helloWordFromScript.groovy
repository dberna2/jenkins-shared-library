def call(Map config = [:]) {

    def scriptcontents = loadResource("${config.env}", "${config.name}")
    
    writeFile file: "${config.name}", text: scriptcontents
    sh "chmod a+x ./${config.name}"
}

def loadResource(String env, String  fileName) {
    if(env == 'linux') {
        echo "Hello Tux!"
        return libraryResource "com/dberna2/scripts/linux/${config.name}.sh"
    }else {
        echo "Hello Windows!"
        return libraryResource "com/dberna2/scripts/windows/${config.name}.bat"
    }
}
