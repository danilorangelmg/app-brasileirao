package br.com.soccer.networkservice.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class GameResponse(
    @SerializedName("rodadas")
    val turns: List<Turn>
)

data class Turn(
    @SerializedName("num")
    val num: Int,
    @SerializedName("value")
    val games: List<Game>
)

data class Game(
    @SerializedName("id")
    val id: Long,
    @SerializedName("data_realizacao")
    val gameDate: String?,
    @SerializedName("hora_realizacao")
    val gameHour: String?,
    @SerializedName("placar_oficial_visitante")
    val guestScore: Int?,
    @SerializedName("placar_oficial_mandante")
    val homeScore: Int?,
    @SerializedName("equipes")
    val teams: Teams?,
    @SerializedName("sede")
    val stadium: Stadium?

)

data class Teams(
    @SerializedName("mandante")
    val home: Team,
    @SerializedName("visitante")
    val guest: Team
)

data class Team(
    @SerializedName("id")
    val id: Long,
    @SerializedName("nome_popular")
    val name: String,
    @SerializedName("sigla")
    val nickname: String,
    @SerializedName("escudo")
    val shieldUlr: String
)

data class Stadium(
    @SerializedName("nome_popular")
    val name: String
)