package pairmatching

import pairmatching.app.PairMatching
import pairmatching.view.InputView
import pairmatching.view.OutputView

fun main() {
    val pairMatching = PairMatching(InputView(), OutputView())
    try {
        pairMatching.process()
    } catch (error: NullPointerException) {
        println("[ERROR]")
    }
}