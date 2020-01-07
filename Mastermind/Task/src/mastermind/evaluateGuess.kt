package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var numRightPosition = 0
    var numWrongPosition = 0
    var secretWithoutRightPositionChars = ""
    var guessWithoutRightPosititionChars = ""
    val evaluatedChars = mutableListOf<Char>()

    fun countCharInString(char: Char, str: String): Int {
        var count = 0
        for (c in str) {
            if (c == char) { count ++ }
        }
        return count
    }

    for (i in 0 until secret.length) {
        if (secret[i] == guess[i]) {
            numRightPosition++
        }
        else {
            secretWithoutRightPositionChars += secret[i]
            guessWithoutRightPosititionChars += guess[i]
        }
    }

    if(secretWithoutRightPositionChars.isNotEmpty()) {
        for (i in 0 until guess.length) {
            val currentGuessChar = guess[i]
            if (!evaluatedChars.contains(currentGuessChar)) {
                val charCountInSecret = countCharInString(currentGuessChar, secretWithoutRightPositionChars)
                val charCountInGuess = countCharInString(currentGuessChar, guessWithoutRightPosititionChars)
                numWrongPosition += if (charCountInSecret >= charCountInGuess) charCountInGuess else charCountInSecret
                evaluatedChars.add(currentGuessChar)
            }
        }
    }

    return Evaluation(numRightPosition, numWrongPosition)
}
