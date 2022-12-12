package pairmatching.view

import pairmatching.view.validator.InputValidator
import java.nio.file.Files
import java.nio.file.Paths

class InputView {

    fun chooseFeature(): String {
        return InputValidator.validateInvalidFeature()
    }

    fun chooseCourse(): List<String> {
        val filePath = Paths.get("src/main/kotlin/resources/choice-course.md")
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        return readLine()!!.split(",")
    }

    fun chooseRematch(): String {
        return InputValidator.validateInvalidRematch()
    }
}