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


internal class PadZipTest {

    @Test
    fun testItFormatsZipsCorrectly() {
        val formatter = PadZip()
        assertEquals("00000", formatter.processString(""))
        assertEquals("00001", formatter.processString("1"))
        assertEquals("00021", formatter.processString("21"))
        assertEquals("00321", formatter.processString("321"))
        assertEquals("04321", formatter.processString("4321"))
        assertEquals("54321", formatter.processString("54321"))
    }
}

internal class FormatFullNameTest {
    @Test
    fun testItFormatsFullNames() {
        val formatter = FullNameFormatter()
        assertEquals("MONKEY ALBERTO", formatter.processString("Monkey Alberto"))
        assertEquals("SUPERMAN ÜBERTAN", formatter.processString("Superman übertan"))
        assertEquals("株式会社スタジオジブリ", formatter.processString("株式会社スタジオジブリ"))
    }
}
