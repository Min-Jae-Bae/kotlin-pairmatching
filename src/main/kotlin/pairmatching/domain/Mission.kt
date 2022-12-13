package pairmatching.domain

class Mission {
/*    - 레벨1: 자동차경주 | 로또 | 숫자야구게임
    - 레벨2: 장바구니 | 결제 | 지하철노선도
    - 레벨3:
    - 레벨4: 성능개선 | 배포
    - 레벨5:*/

    companion object {
        private val missionList = hashMapOf(
            Level.LEVEL1 to listOf("자동차경주", "로또", "숫자야구게임"),
            Level.LEVEL2 to listOf("장바구니", "결제", "지하철노선도"),
            Level.LEVEL3 to emptyList(),
            Level.LEVEL4 to listOf("성능개선", "배포"),
            Level.LEVEL5 to emptyList()
        )

        fun provideMissionList(level: Level): List<String> = missionList[level]!!
    }
}