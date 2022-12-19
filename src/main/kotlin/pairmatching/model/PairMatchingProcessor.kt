package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms
import pairmatching.domain.Course
import pairmatching.domain.Level
import pairmatching.utils.Crew.Companion.makeCrew

object PairMatchingProcessor {

    private val matchedByLevel: HashMap<Course, HashMap<Level, HashMap<String, List<String>>>> = HashMap()
    private val matchedByMission: HashMap<Course, HashMap<String, List<List<String>>>> = HashMap()

    fun matchCrews(course: String, level: String, mission: String): List<String> {
        val shuffleCrews = getShuffledCrew(Course.convertCourse(course))
        return emptyList()
    }

    fun match(crews: List<String>): List<List<String>> {
        val mutableCrews = crews.toMutableList()
        val matchedCrew: MutableList<List<String>> = mutableListOf()

        while (mutableCrews.isNotEmpty()) {
            if (mutableCrews.size == 3) {
                matchedCrew.add(mutableCrews.slice(0 until 3))
                mutableCrews.subList(0, 3).clear()
                break
            }
            matchedCrew.add(mutableCrews.slice(0 until 2))
            mutableCrews.subList(0, 2).clear()

        }
            return matchedCrew
    }


    fun clearPairMatching() {
        matchedByLevel.clear()
        matchedByMission.clear()
    }

    fun getShuffledCrew(course: Course): List<String> = Randoms.shuffle(makeCrew(course))
}