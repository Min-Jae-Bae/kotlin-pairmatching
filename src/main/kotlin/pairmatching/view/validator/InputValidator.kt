package pairmatching.view.validator

import java.nio.file.Files
import java.nio.file.Paths

object InputValidator {

    fun validateInvalidFeature(): String {
        val filePath = Paths.get("src/main/kotlin/resources/choice-function.md")
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        val featureString = readLine()!!
        require(featureString == "1" || featureString == "2" || featureString == "3" || featureString == "Q") {
            "[ERROR]"
        }
        return featureString
    }

    fun validateInvalidRematch(): String {
        val filePath = Paths.get("src/main/kotlin/resources/choice-rematch.md")
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        val rematchString = readLine()!!
        require(rematchString == "네" || rematchString == "아니오") { "[ERROR]" }
        return  rematchString
    }
}