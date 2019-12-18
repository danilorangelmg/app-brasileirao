package br.com.soccer.repositorie.soccer

import br.com.soccer.domain.soccer.Game
import br.com.soccer.domain.soccer.GameDescription
import br.com.soccer.domain.soccer.Score
import br.com.soccer.repositorie.helper.exception.BusinessException

interface SoccerRepository {

    @Throws(BusinessException::class)
    suspend fun getScore(): Score

    @Throws(BusinessException::class)
    suspend fun getGames(): Game

    @Throws(BusinessException::class)
    suspend fun getGameDescription(gameId: Long): List<GameDescription>
}