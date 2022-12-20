package pairmatching.view.validator

import pairmatching.domain.Course.Companion.courseList
import pairmatching.domain.Level.Companion.convertLevel
import pairmatching.domain.Level.Companion.levelList
import pairmatching.domain.Mission.Companion.provideMissionList

object InputValidator {

    fun validateInvalidFeature(): String {
        val featureString = readLine()!!
        require(featureString == "1" || featureString == "2" || featureString == "3" || featureString == "Q") {
            "[ERROR]"
        }
        return featureString
    }

    fun validateInvalidCourse(): List<String> {
        val (course, level, mission) = readLine()!!.split(", ")
        validateCourse(course)
        validateLevel(level)
        validateMission(level, mission)

        return listOf(course, level, mission)
    }

    private fun validateCourse(course: String) {
        require(course in courseList()) { "[ERROR] 유효하지 않은 코스입니다." }
    }

    private fun validateLevel(level: String) {
        require(level in levelList()) { "[ERROR] 유효하지 않은 레벨입니다." }
    }

    private fun validateMission(level: String, mission: String) {
        require(mission in provideMissionList(convertLevel(level))) { "[ERROR] 유효하지 않은 미션입니다." }
    }


    fun validateInvalidRematch(): String {
        val rematchString = readLine()!!
        require(rematchString == "네" || rematchString == "아니오") { "[ERROR]" }
        return rematchString
    }
}
