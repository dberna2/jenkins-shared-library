def call(Map config = [:]) {
    echo "Hello ${config.name}! today is: ${config.dayOfTheWeek}"
}
