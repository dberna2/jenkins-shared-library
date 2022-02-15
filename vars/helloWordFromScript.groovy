def call(Map config = [:]) {
    def scriptcontents = libraryResource "com/dberna2/scripts/${config.env}/${config.name}"
    writeFile file: "${config.name}", text: scriptcontents
    sh "chmod a+x ./${config.name}"
    if(config.env = "linux") {
        echo "Hello Tux!"
    }else {
        echo "Hello Windown!"
    }
}