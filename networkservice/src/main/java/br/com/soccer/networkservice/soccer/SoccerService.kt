package br.com.soccer.networkservice.soccer

import br.com.soccer.networkservice.model.GameDescriptionResponse
import br.com.soccer.networkservice.model.GameResponse
import br.com.soccer.networkservice.model.ScoreResponse

interface SoccerService {

    suspend fun getScore(): ScoreResponse

    suspend fun getGames(): GameResponse

    suspend fun getGameDescription(gameId: Long): GameDescriptionResponse

}