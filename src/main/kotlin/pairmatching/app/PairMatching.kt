package pairmatching.app

import pairmatching.domain.Course
import pairmatching.model.PairMatchingProcessor
import pairmatching.view.InputView
import pairmatching.view.OutputView

class PairMatching(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private var isProcessing = true

    fun process() {
        do {
            val option = inputView.chooseFeature()
            choiceOption(option)
        } while (isProcessing)
    }


    private fun choiceOption(option: String) {
        when (option) {
            "1" -> matchPair()
            "2" -> lookUpPair()
            "3" -> resetPair()
            "Q" -> isProcessing = false
        }
    }


    private fun matchPair() {
        val (course, level, mission) = inputView.chooseCourse()
        if (PairMatchingProcessor.checkMatched(Course.convertCourse(course), mission)) {
            when (inputView.chooseRematch()) {
                "네" -> outputView.matchingResult(PairMatchingProcessor.matchCrews(course, level, mission))
                "아니오" -> return
            }
            return
        }
        outputView.matchingResult(PairMatchingProcessor.matchCrews(course, level, mission))
    }

    private fun lookUpPair() {
        /*페어 매칭 정보 출력*/
        val (course, _, mission) = inputView.chooseCourse()
        outputView.matchingResult(PairMatchingProcessor.getMatchedCrews(Course.convertCourse(course),
            mission))
    }

    private fun resetPair() {
        PairMatchingProcessor.clearPairMatching()
        outputView.printInitializedMessage()
    }
}