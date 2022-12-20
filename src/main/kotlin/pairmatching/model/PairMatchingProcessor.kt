package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms
import pairmatching.domain.Course
import pairmatching.domain.Course.Companion.convertCourse
import pairmatching.domain.Level
import pairmatching.domain.Level.Companion.convertLevel
import pairmatching.utils.Crew.Companion.makeCrew
import pairmatching.utils.MAX_MATCHING_MESSAGE
import pairmatching.utils.MAX_MATCHING_TRIAL
import pairmatching.utils.PAIR_MATCHING_ODD_SIZE
import pairmatching.utils.PAIR_MATCHING_SIZE

object PairMatchingProcessor {

    private val matchedByLevel: HashMap<Course, HashMap<Level, HashMap<String, List<String>>>> = HashMap()
    private val matchedByMission: HashMap<Course, HashMap<String, List<List<String>>>> = HashMap()

    fun getMatchedCrews(course: Course, mission: String): List<List<String>> {
        return matchedByMission[course]?.get(mission) ?: emptyList()
    }

    fun matchCrews(course: String, level: String, mission: String): List<List<String>> {
        val shuffleCrews = getShuffledCrew(convertCourse(course))
        val matchedCrews = match(shuffleCrews)

        repeat(MAX_MATCHING_TRIAL) { trial ->
            require(trial < MAX_MATCHING_TRIAL) { MAX_MATCHING_MESSAGE }
            if (checkDuplication(matchedCrews, convertCourse(course), convertLevel(level))) {
                return@repeat
            }
            addCrewByLevel(convertCourse(course), convertLevel(level), matchedCrews)
            addCrewByMission(course, mission, matchedCrews)
        }
        return matchedCrews
    }


    private fun match(crews: List<String>): List<List<String>> {
        val mutableCrews = crews.toMutableList()
        val matchedCrew: MutableList<List<String>> = mutableListOf()

        while (mutableCrews.isNotEmpty()) {
            if (mutableCrews.size == PAIR_MATCHING_ODD_SIZE) {
                matchedCrew.add(mutableCrews.slice(0 until PAIR_MATCHING_ODD_SIZE))
                mutableCrews.subList(0, PAIR_MATCHING_ODD_SIZE).clear()
                break
            }
            matchedCrew.add(mutableCrews.slice(0 until PAIR_MATCHING_SIZE))
            mutableCrews.subList(0, PAIR_MATCHING_SIZE).clear()

        }
        return matchedCrew
    }

    private fun addCrewByLevel(course: Course, level: Level, matchedCrew: List<List<String>>) {
        matchedCrew.forEach { pair ->
            pair.forEach { crew ->
                matchedByLevel.getOrPut(course) { hashMapOf(level to hashMapOf()) }
                matchedByLevel[course]!![level]?.getOrPut(crew) { pair }
            }
        }
    }

    private fun addCrewByMission(course: String, mission: String, matchedCrew: List<List<String>>) {
        matchedByMission.getOrPut(convertCourse(course)) { hashMapOf(mission to matchedCrew) }

    }

    fun checkMatched(course: Course, mission: String): Boolean {
        if (matchedByMission[course]?.get(mission).isNullOrEmpty()) {
            return false
        }
        return true
    }


    private fun checkDuplication(matchedCrew: List<List<String>>, course: Course, level: Level): Boolean {
        val matchedByLevel = matchedByLevel[course]?.get(level) ?: return false
        matchedCrew.forEach { pair ->
            pair.forEach { crew ->
                val expectedSize = pair.size + (matchedByLevel[crew]?.size ?: 0)
                val unionSize = pair.union(matchedByLevel[crew] ?: emptyList()).size
                if (expectedSize != unionSize) {
                    return true
                }
            }
        }
        return false
    }

    fun clearPairMatching() {
        matchedByLevel.clear()
        matchedByMission.clear()
    }

    private fun getShuffledCrew(course: Course): List<String> = Randoms.shuffle(makeCrew(course.name))
}