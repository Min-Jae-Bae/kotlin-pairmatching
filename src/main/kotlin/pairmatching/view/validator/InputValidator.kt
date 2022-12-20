package pairmatching.view.validator

import pairmatching.domain.Course.Companion.courseList
import pairmatching.domain.Level.Companion.convertLevel
import pairmatching.domain.Level.Companion.levelList
import pairmatching.domain.Mission.Companion.provideMissionList
import pairmatching.utils.*

object InputValidator {

    fun validateInvalidFeature(): String {
        val featureString = readLine()!!
        val featureList = listOf(PAIR_MATCHING_COMMAND, LOOKING_UP_COMMAND, PAIR_RESETTING_COMMAND, QUIT_COMMAND)
        require(featureString in featureList) { ERROR_INVALID_FEATURE_MESSAGE }
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
        require(course in courseList()) { ERROR_INVALID_COURSE_MESSAGE }
    }

    private fun validateLevel(level: String) {
        require(level in levelList()) { ERROR_INVALID_LEVEL_MESSAGE }
    }

    private fun validateMission(level: String, mission: String) {
        require(mission in provideMissionList(convertLevel(level))) { ERROR_INVALID_MISSION_MESSAGE }
    }


    fun validateInvalidRematch(): String {
        val rematchString = readLine()!!
        val rematchingList = listOf(REMATCHING_COMMAND, NOT_REMATCHING_COMMAND)
        require(rematchString in rematchingList) { ERROR_INVALID_REMATCHING_MESSAGE }
        return rematchString
    }
}