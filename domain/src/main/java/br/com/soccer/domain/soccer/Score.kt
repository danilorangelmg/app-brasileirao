package br.com.soccer.domain.soccer

import br.com.soccer.common.annotation.Testable

@Testable
data class Score(
    val table: List<Classification>
)

@Testable
data class Classification(
    val harnessing: Double,
    val defeats: Int,
    val draw: Int,
    val shieldUlr: String,
    val goalAgainst: Int,
    val goal: Int,
    val numberOfMatches: Int,
    val name: String,
    val order: Int,
    val points: Int,
    val goalsDiff: Int,
    val nickname: String,
    val winNumber: Int
)
