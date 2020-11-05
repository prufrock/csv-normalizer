import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.BufferedWriter
import java.io.StringWriter

internal class NormalizerTest {
    @Test
    fun itWritesWhatItReceives() {
        val input = """
            Timestamp,Address,ZIP,FullName,FooDuration,BarDuration,TotalDuration,Notes
            4/1/11 11:00:00 AM,"123 4th St, Anywhere, AA",94121,Monkey Alberto,1:23:32.123,1:32:33.123,zzsasdfa,I am the very model of a modern major general
            
        """.trimIndent()


        val expected = """
            TIMESTAMP,ADDRESS,ZIP,FULLNAME,FOODURATION,BARDURATION,TOTALDURATION,NOTES
            2011-04-01T14:00:00-04:00,"123 4th St, Anywhere, AA",94121,MONKEY ALBERTO,1:23:32.123,1:32:33.123,zzsasdfa,I am the very model of a modern major general
            
        """.trimIndent()

        val stringWriter = StringWriter()

        Normalizer(BufferedWriter(stringWriter)).run(input)

        assertEquals(expected, stringWriter.toString())
    }
}