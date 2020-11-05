import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.HeaderColumnNameMappingStrategy
import com.opencsv.bean.StatefulBeanToCsvBuilder
import com.opencsv.bean.comparator.LiteralComparator
import java.io.BufferedWriter
import java.io.StringReader

class Normalizer(val writer: BufferedWriter) {

    fun run(input: String) {
        val visits: List<Visit> = CsvToBeanBuilder<Visit>(StringReader(input)).withType(Visit::class.java).build().parse()

        visits.forEachIndexed { index, visit ->
            if(visit.timestamp.isEmpty()) {
                System.err.println("WARN: Row $index had some bad data in it and couldn't be normalized. You should take a look and see if anything is funny about it.")
            }
        }

        val filteredVisits = visits.filter { visit ->
            return@filter visit.timestamp.isNotEmpty()
        }

        val mappingStrategy = HeaderColumnNameMappingStrategy<Visit>().also {
            it.type = Visit::class.java
            it.setColumnOrderOnWrite(LiteralComparator(arrayOf("TIMESTAMP","ADDRESS","ZIP","FULLNAME","FOODURATION","BARDURATION","TOTALDURATION")))
        }

        val csvWriter = StatefulBeanToCsvBuilder<Visit>(writer)
                .withMappingStrategy(mappingStrategy)
                .withApplyQuotesToAll(false)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(true)
                .build()

        csvWriter.write(filteredVisits)

        writer.close()
    }
}