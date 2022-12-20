package pairmatching.app

import pairmatching.domain.Course.Companion.convertCourse
import pairmatching.model.PairMatchingProcessor.checkMatched
import pairmatching.model.PairMatchingProcessor.clearPairMatching
import pairmatching.model.PairMatchingProcessor.getMatchedCrews
import pairmatching.model.PairMatchingProcessor.matchCrews
import pairmatching.utils.*
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
            PAIR_MATCHING_COMMAND -> matchPair()
            LOOKING_UP_COMMAND -> lookUpPair()
            PAIR_RESETTING_COMMAND -> resetPair()
            QUIT_COMMAND -> isProcessing = false
        }
    }


    private fun matchPair() {
        val (course, level, mission) = inputView.chooseCourse()
        if (checkMatched(convertCourse(course), mission)) {
            when (inputView.chooseRematch()) {
                REMATCHING_COMMAND -> outputView.matchingResult(matchCrews(course, level, mission))
                NOT_REMATCHING_COMMAND -> return
            }
            return
        }
        outputView.matchingResult(matchCrews(course, level, mission))
    }

    private fun lookUpPair() {
        val (course, _, mission) = inputView.chooseCourse()
        outputView.matchingResult(getMatchedCrews(convertCourse(course), mission))
    }

    private fun resetPair() {
        clearPairMatching()
        outputView.printInitializedMessage()
    }
}