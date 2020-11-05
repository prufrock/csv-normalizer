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

internal class FormatTimestampTest {

    @Test
    fun testItFormatsDatesCorrectly() {
        val formatter = FormatTimestamp()
        assertEquals("2011-04-01T14:00:00-04:00", formatter.processString("4/1/11 11:00:00 AM"))
    }
}
