package pairmatching.view

class OutputView {

    fun matchingResult(pairMatchingResult: List<String>) {
        println("페어 매칭 결과입니다.")
        pairMatchingResult.forEach { result -> println(result) }
    }
}