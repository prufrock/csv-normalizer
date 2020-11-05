import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InputCheckerTest {
    @Test
    fun itReturnsFalseIfThereAreNoLinesOfInput() {
        assertFalse(InputChecker().check(""))
    }

    @Test
    fun itReturnsTrueIfThereAreEnoughLinesOfInput() {
        assertTrue(InputChecker().check("longer than expected"))
    }

    @Test
    fun itReturnsFalseIfThereIsntEnoughInput() {
        assertFalse(InputChecker().check("input"))
    }

    @Test
    fun itReturnTrueIfThereIsEnoughInput() {
        assertTrue(InputChecker().check("enough!"))
    }
}