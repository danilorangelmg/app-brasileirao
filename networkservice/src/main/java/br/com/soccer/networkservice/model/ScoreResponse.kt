package br.com.soccer.networkservice.model

import com.google.gson.annotations.SerializedName

data class ScoreResponse(
    @SerializedName("classificacao")
    val table: List<Classification>
)

data class Classification(
    @SerializedName("aproveitamento")
    val harnessing: Double,
    @SerializedName("derrotas")
    val defeats: Int,
    @SerializedName("empates")
    val draw: Int,
    @SerializedName("escudo")
    val shieldUlr: String,
    @SerializedName("gols_contra")
    val goalAgainst: Int,
    @SerializedName("gols_pro")
    val goal: Int,
    @SerializedName("jogos")
    val numberOfMatches: Int,
    @SerializedName("nome_popular")
    val name: String,
    @SerializedName("ordem")
    val order: Int,
    @SerializedName("pontos")
    val points: Int,
    @SerializedName("saldo_gols")
    val goalsDiff: Int,
    @SerializedName("sigla")
    val nickname: String,
    @SerializedName("vitorias")
    val winNumber: Int
)
