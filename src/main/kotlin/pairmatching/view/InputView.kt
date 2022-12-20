package pairmatching.view

import pairmatching.view.validator.InputValidator
import java.nio.file.Files
import java.nio.file.Paths

class InputView {

    fun chooseFeature(): String {
        val filePath = Paths.get(CHOICE_FUNCTION_PATH)
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        return try {
            InputValidator.validateInvalidFeature()
        } catch (error: IllegalArgumentException) {
            println(error)
            chooseFeature()
        }

    }

    fun chooseCourse(): List<String> {
        val filePath = Paths.get(CHOICE_COURSE_PATH)
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        return try {
            InputValidator.validateInvalidCourse()
        } catch (error: IllegalArgumentException) {
            println(error)
            chooseCourse()
        }
    }

    fun chooseRematch(): String {
        val filePath = Paths.get(CHOICE_REMATCH_PATH)
        Files.readAllLines(filePath, Charsets.UTF_8).forEach { println(it) }
        return try {
            InputValidator.validateInvalidRematch()
        } catch (error: IllegalArgumentException) {
            println(error)
            chooseRematch()
        }

    }

    companion object {
        const val CHOICE_FUNCTION_PATH = "src/main/kotlin/resources/choice-function.md"
        const val CHOICE_COURSE_PATH = "src/main/kotlin/resources/choice-course.md"
        const val CHOICE_REMATCH_PATH = "src/main/kotlin/resources/choice-rematch.md"
    }
}