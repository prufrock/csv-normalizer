fun main(args: Array<String>) {
    println("Hello World!")
    val input = generateSequence(::readLine)
    val lines = input.toList()
    lines.forEach {
        println(it)
    }
}