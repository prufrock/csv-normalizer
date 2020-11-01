import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InputCheckerTest {
    @Test
    fun itReturnsFalseIfThereAreNoLinesOfInput() {
        assertFalse(InputChecker().check(listOf<String>()))
    }

    @Test
    fun itReturnsTrueIfThereAreEnoughLinesOfInput() {
        assertTrue(InputChecker().check(listOf<String>("longer than expected")))
    }

    @Test
    fun itReturnsFalseIfThereIsntEnoughInput() {
        assertFalse(InputChecker().check(listOf<String>("input")))
    }

    @Test
    fun itReturnTrueIfThereIsEnoughInput() {
        assertTrue(InputChecker().check(listOf<String>("enough!")))
    }
}