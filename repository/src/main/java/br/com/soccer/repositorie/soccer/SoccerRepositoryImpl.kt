package br.com.soccer.repositorie.soccer

import br.com.soccer.domain.soccer.*
import br.com.soccer.networkservice.soccer.SoccerService
import br.com.soccer.networkservice.soccer.SoccerServiceImpl
import br.com.soccer.repositorie.helper.exception.BusinessException
import br.com.soccer.repositorie.soccer.mapper.toDomain
import br.com.soccer.storageservice.product.ProductStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SoccerRepositoryImpl(val soccerService: SoccerService, val storage: ProductStorage) :
    SoccerRepository {

    @Throws(Exception::class)
    override suspend fun getScore(): Score {
        try {
            return withContext(Dispatchers.IO) {
                soccerService.getScore().toDomain {
                    Score(table = it.table.map { classification ->
                        classification.toDomain {
                            Classification(
                                harnessing = classification.harnessing,
                                defeats = classification.defeats,
                                draw = classification.draw,
                                shieldUlr = classification.shieldUlr,
                                goalAgainst = classification.goalAgainst,
                                goal = classification.goal,
                                numberOfMatches = classification.numberOfMatches,
                                name = classification.name,
                                order = classification.order,
                                points = classification.points,
                                goalsDiff = classification.goalsDiff,
                                nickname = classification.nickname,
                                winNumber = classification.winNumber
                            )
                        }
                    })
                }
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override suspend fun getGames(): Game {
        try {
            return withContext(Dispatchers.IO) {
                SoccerServiceImpl().getGames().toDomain {
                    Game(
                        turns = it.turns.map { turn ->
                            turn.toDomain { value ->
                                Turn(num = value.num,
                                    games = value.games.map { game ->
                                        game.toDomain { gameValue ->
                                            GameTurn(
                                                id = gameValue.id,
                                                gameDate = gameValue.gameDate,
                                                gameHour = gameValue.gameHour,
                                                guestScore = gameValue.guestScore,
                                                homeScore = gameValue.homeScore,
                                                teams = gameValue.teams?.toDomain { teamsValue ->
                                                    Teams(
                                                        home = teamsValue.home.toDomain { team ->
                                                            Team(
                                                                id = team.id,
                                                                name = team.name,
                                                                nickname = team.nickname,
                                                                shieldUlr = team.shieldUlr
                                                            )
                                                        },
                                                        guest = teamsValue.guest.toDomain { team ->
                                                            Team(
                                                                id = team.id,
                                                                name = team.name,
                                                                nickname = team.nickname,
                                                                shieldUlr = team.shieldUlr
                                                            )
                                                        }
                                                    )
                                                },
                                                stadium = gameValue.stadium?.toDomain { stadiumValue ->
                                                    Stadium(name = stadiumValue.name)
                                                }
                                            )
                                        }
                                    })
                            }
                        }
                    )
                }
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override suspend fun getGameDescription(gameId: Long): List<GameDescription> {
        try {
            return withContext(Dispatchers.IO) {
                soccerService.getGameDescription(gameId).toDomain {
                    it.resource.bids.map { bid ->
                        GameDescription(
                            id = bid.id,
                            title = bid.title,
                            period = DescriptionPeriod.valueOf(bid.period.toString()),
                            moment = bid.moment,
                            type = bid.type,
                            text = bid.body?.blocks?.let{blocks ->
                                blocks[0].text
                            }?:""
                        )
                    }.filter { game ->
                        game.period != DescriptionPeriod.POS_JOGO && (game.text?.isNotEmpty()?:false)
                    }
                }
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

}