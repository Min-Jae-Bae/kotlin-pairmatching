package pairmatching.app

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
    }

    private fun lookUpPair() {

    }

    private fun resetPair() {
    }
}