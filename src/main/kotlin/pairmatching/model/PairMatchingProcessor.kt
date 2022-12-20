package pairmatching.model

import camp.nextstep.edu.missionutils.Randoms
import pairmatching.domain.Course
import pairmatching.domain.Course.Companion.convertCourse
import pairmatching.domain.Level
import pairmatching.domain.Level.Companion.convertLevel
import pairmatching.utils.Crew.Companion.makeCrew

object PairMatchingProcessor {

    private val matchedByLevel: HashMap<Course, HashMap<Level, HashMap<String, List<String>>>> =
        HashMap()
    private val matchedByMission: HashMap<Course, HashMap<String, List<List<String>>>> = HashMap()

    // 매치 크루를 불러옴 (코스, 미션 값의 value 없으면 빈 리스트 반환)
    fun getMatchedCrews(course: Course, mission: String): List<List<String>> {
        return matchedByMission[course]?.get(mission) ?: emptyList()
    }

    fun matchCrews(course: String, level: String, mission: String): List<List<String>> {
        val shuffleCrews = getShuffledCrew(convertCourse(course))
        val matchedCrews = match(shuffleCrews)

        repeat(3) {
            if (checkDuplication(matchedCrews, convertCourse(course), convertLevel(level))) {
                return@repeat
            }
        addCrewByLevel(convertCourse(course), convertLevel(level), matchedCrews)
        addCrewByMission(course, mission, matchedCrews)
        return matchedCrews
        }
        throw IllegalArgumentException("3회 이상 매칭 불가능합니다.")
    }


    private fun match(crews: List<String>): List<List<String>> {
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

    // 레벨에 따른 (레벨 스트링과 미션을 넣는 기능 )
    private fun addCrewByLevel(course: Course, level: Level, matchedCrew: List<List<String>>) {
        matchedCrew.forEach { pair ->
            pair.forEach { crew ->
                matchedByLevel.getOrPut(course) { hashMapOf(level to hashMapOf()) }
                matchedByLevel[course]!![level]?.getOrPut(crew) { pair }
            }
        }
    }

    // 코스에 따른 미션으로 크루들을 넣는 기능
    private fun addCrewByMission(course: String, mission: String, matchedCrew: List<List<String>>) {
        matchedByMission.getOrPut(convertCourse(course)) { hashMapOf(mission to matchedCrew) }

    }

    // 매칭 되었는지 확인
    fun checkMatched(course: Course, mission: String): Boolean {
        if (matchedByMission[course]?.get(mission).isNullOrEmpty()) {
            return false
        }
        return true
    }

    // 중복 매칭 확인 (union)
    private fun checkDuplication(matchedCrew: List<List<String>>, course: Course, level: Level): Boolean {
        // 존재하지 않으면 false 반환
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

    private fun getShuffledCrew(course: Course): List<String> =
        Randoms.shuffle(makeCrew(course.name))
}