package br.com.soccer.networkservice.soccer

import br.com.soccer.networkservice.config.NetworkException
import br.com.soccer.networkservice.config.ServiceConfiguration
import br.com.soccer.networkservice.model.GameDescriptionResponse
import br.com.soccer.networkservice.model.GameResponse
import br.com.soccer.networkservice.model.ScoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SoccerServiceImpl : SoccerService {

    private val ERROR_MESSAGE = "Erro ao buscar jogos, Tente novamente em alguns instantes!"

    private val soccerApi by lazy {
        ServiceConfiguration().configureApiInterface(SoccerApi::class.java)
    }

    @Throws(NetworkException::class)
    override suspend fun getScore(): ScoreResponse {
         try {
             return withContext(Dispatchers.IO) {
                soccerApi.getScore()
            }
        } catch (e: HttpException) {
            throw NetworkException(ERROR_MESSAGE)
        }
    }

    @Throws(NetworkException::class)
    override suspend fun getGames(): GameResponse {
        try {
            return withContext(Dispatchers.IO) {
                soccerApi.getGames()
            }
        } catch (e: HttpException) {
            throw NetworkException(ERROR_MESSAGE)
        }
    }

    @Throws(NetworkException::class)
    override suspend fun getGameDescription(gameId: Long): GameDescriptionResponse {
        try {
            return withContext(Dispatchers.IO) {
                soccerApi.getGameDescription(gameId)
            }
        } catch (e: HttpException) {
            throw NetworkException(ERROR_MESSAGE)
        }
    }
}