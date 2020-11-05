import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.processor.PreAssignmentProcessor
import com.opencsv.bean.processor.StringProcessor
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

const val MILLIS_TO_SECONDS = 1000

class Visit {
    @CsvBindByName
    @PreAssignmentProcessor(processor = FormatTimestamp::class)
    var timestamp: String = ""

    @CsvBindByName
    var address: String = ""

    @CsvBindByName
    @PreAssignmentProcessor(processor = PadZip::class)
    var zip: String = ""

    @CsvBindByName
    @PreAssignmentProcessor(processor = FullNameFormatter::class)
    var fullName: String = ""

    @CsvBindByName
    @PreAssignmentProcessor(processor = ConvertDurationToSeconds::class)
    var fooDuration: String = ""

    @CsvBindByName
    @PreAssignmentProcessor(processor = ConvertDurationToSeconds::class)
    var barDuration: String = ""

    @CsvBindByName
    var totalDuration: String = ""
        get() {
            return (fooDuration.toDouble() + barDuration.toDouble()).toString()
        }

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

class PadZip: StringProcessor {
    override fun processString(value: String?): String?  {
        if (value == null) {
            return value
        }
        return value.padStart(5, '0')
    }

    override fun setParameterString(value: String?) {
        /* no-op */
    }
}

class FullNameFormatter: StringProcessor {
    override fun processString(value: String?): String?  {
        if (value == null) {
            return value
        }

        return value.toUpperCase()
    }

    override fun setParameterString(value: String?) {
        /* no-op */
    }
}

class ConvertDurationToSeconds: StringProcessor {
    override fun processString(value: String?): String?  {
        if (value == null) {
            return value
        }

        val segments = value.split(":")
        val hours = segments[0]
        val minutes = segments[1]
        val seconds = segments[2].split(".")[0]
        val millis = segments[2].split(".")[1]

        return Duration.ofHours(hours.toLong())
                .plusMinutes(minutes.toLong())
                .plusSeconds(seconds.toLong())
                .plusMillis((millis.toLong()))
                .toMillis().toDouble().div(MILLIS_TO_SECONDS).toString()
    }

    override fun setParameterString(value: String?) {
        /* no-op */
    }
}

