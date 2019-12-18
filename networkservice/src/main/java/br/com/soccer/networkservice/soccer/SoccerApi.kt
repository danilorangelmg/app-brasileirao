package br.com.soccer.networkservice.soccer

import br.com.soccer.networkservice.model.GameDescriptionResponse
import br.com.soccer.networkservice.model.GameResponse
import br.com.soccer.networkservice.model.ScoreResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SoccerApi {

    @GET("/score")
    suspend fun getScore(): ScoreResponse

    @GET("/games")
    suspend fun getGames(): GameResponse

    @GET("description/{gameId}")
    suspend fun getGameDescription(@Path("gameId") gameId: Long): GameDescriptionResponse
}