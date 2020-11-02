import com.opencsv.CSVReader
import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvDate
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.processor.PreAssignmentProcessor
import com.opencsv.bean.processor.StringProcessor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.StringReader
import java.time.Year

internal class OpenCsvTest {
    val data = """
            Book, Published, Author
            The Night Watchman,2020, Louise Erdrich
            Patternmaster,1976, Octavia E. Butler
            The Heart is a Lonely Hunter,1940, Carson McCullers
        """.trimIndent()

    @Test
    fun readASimpleCsvFromAString() {
        val reader = CSVReader(StringReader(data))

        val lines = reader.readAll()

        assertEquals("The Night Watchman", lines[1][0])
    }

    @Test
    fun readASimpleCsvToABean() {
        val books: List<BookBean> = CsvToBeanBuilder<BookBean>(StringReader(data)).withType(BookBean::class.java).build().parse()

        assertEquals("The Night Watchman", books[0].book)
        assertEquals("2020", books[0].published.toString())
        assertEquals("Louise Erdrich", books[0].author)
    }
}

class BookBean {
    @CsvBindByName
    @PreAssignmentProcessor(processor = TrimColumns::class)
    var book: String = ""

    @CsvBindByName
    @CsvDate("yyyy")
    @PreAssignmentProcessor(processor = TrimColumns::class)
    var published: Year? = null

    @CsvBindByName
    @PreAssignmentProcessor(processor = TrimColumns::class)
    var author: String = ""
}

class TrimColumns: StringProcessor {
    override fun processString(value: String?): String? = value?.trim()

    override fun setParameterString(value: String?) {
        /* no-op */
    }
}