class InputChecker {
    fun check(input:List<String>): Boolean {
        if (input.isEmpty()) {
            println(
                    """
                There wasn't any input. Did you remember to pass a filename as the first argument, "java -jar normalizer.jar data.csv" or send input via stdin like so:
                data.csv | java -jar normalizer.jar
                java -jar normalizer.jar < data.csv
            """.trimIndent()
            )

            return false
        } else if(input.first().length < EXPECTED_MIN_LENGTH) {
            println(
                    """
                    There were only ${input.first().length} characters of input. There should be at least ${EXPECTED_MIN_LENGTH} because there are ${EXPECTED_MIN_LENGTH} separators for the expected number of fields in the input so presumably the first line of input should have at least that many characters. Did you pipe in the file you meant to or provide the correct argument? Take a look at the path and see if there any unexpected paths. You might also want to open the file and check that it contains what you expect it to. You can get two birds with one stone if you open the path of the file you supplied in your favorite editor. Hope that clears it up!
                """.trimIndent()
            )

            return false
        } else {
            return true
        }
    }
}