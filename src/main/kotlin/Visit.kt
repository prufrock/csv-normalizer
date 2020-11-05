import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.processor.PreAssignmentProcessor
import com.opencsv.bean.processor.StringProcessor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Visit {
    @CsvBindByName
    @PreAssignmentProcessor(processor = FormatTimestamp::class)
    var timestamp: String = ""

    @CsvBindByName
    var address: String = ""

    @CsvBindByName
    var zip: String = ""

    @CsvBindByName
    var fullName: String = ""

    @CsvBindByName
    var fooDuration: String = ""

    @CsvBindByName
    var barDuration: String = ""

    @CsvBindByName
    var totalDuration: String = ""

    @CsvBindByName
    var notes: String = ""
}

class FormatTimestamp: StringProcessor {
    override fun processString(value: String?): String?  {
        val local = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("M/d/yy h:m:s a"))
        val pacificTime = local.atZone(ZoneId.of("US/Pacific"))
        return pacificTime.withZoneSameInstant(ZoneId.of("US/Eastern")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
    }

    override fun setParameterString(value: String?) {
        /* no-op */
    }
}

