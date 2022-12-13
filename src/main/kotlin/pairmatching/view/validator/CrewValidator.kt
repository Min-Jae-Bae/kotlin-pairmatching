package pairmatching.view.validator

object CrewValidator {

    fun validateIsNotEmptyMatching(pairMatchingResult: List<List<String>>) {
        require(pairMatchingResult.isNotEmpty())
    }
}