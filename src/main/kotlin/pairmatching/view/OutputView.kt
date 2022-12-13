package pairmatching.view

import pairmatching.view.validator.CrewValidator

class OutputView {

    fun matchingResult(pairMatchingResult: List<List<String>>) {
        runCatching {
            CrewValidator.validateIsNotEmptyMatching(pairMatchingResult)
        }.onSuccess {
            pairMatchingResult.forEach { crew ->
                val crewPairList = crew.map { it }
                println(crewPairList.joinToString(" : "))
            }
        }.onFailure {
            println(ERROR_NO_MATCHING_HISTORY)
        }
    }

    companion object {
        const val ERROR_NO_MATCHING_HISTORY = "[ERROR] 매칭 이력이 없습니다."
    }
}