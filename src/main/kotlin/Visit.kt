import com.opencsv.bean.CsvBindByName

class Visit {
    @CsvBindByName
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
