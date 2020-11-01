import java.io.File
import kotlin.system.exitProcess

const val EXPECTED_MIN_LENGTH = 7

fun main(args: Array<String>) {

    val lines = run {
        if (args.count() >= 1) {
            return@run File(args[0])
                .readText(Charsets.UTF_8)
                .split("\n")
        } else {
            return@run generateSequence(::readLine).toList()
        }
    }

    if(InputChecker().check(lines) == false) {
        exitProcess(1)
    }

    lines.forEach {
        println(it)
    }

    exitProcess(0)
}