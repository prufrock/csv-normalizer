import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsvBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.io.StringReader
import kotlin.system.exitProcess

const val EXPECTED_MIN_LENGTH = 7

fun main(args: Array<String>) {

    try {
        val input = run {
            if (args.count() >= 1) {
                return@run File(args[0])
                        .readText(Charsets.UTF_8)
            } else {
                return@run generateSequence(::readLine).toList().joinToString("\n")
            }
        }

        if (InputChecker().check(input) == false) {
            exitProcess(1)
        }

        Normalizer(BufferedWriter(OutputStreamWriter(System.out))).run(input)

        exitProcess(0)

    } catch (exception:Exception) {
        println("""
            It looks like something went wrong when running normalizer. Did you pass in a CSV with commas as the
            delimiter? Did it have the 8 columns: Timestamp, Address, ZIP, FullName, FooDuration, BarDuration,TotalDuration, and notes? Was each element in each column formatted correctly and uniformly like were all of the timestamps the same format? If that all checks out it may be a bug so it may be time to peak at the code.
        """.trimIndent())
    }
}