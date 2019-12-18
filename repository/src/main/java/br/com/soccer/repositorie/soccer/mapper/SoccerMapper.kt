package br.com.soccer.repositorie.soccer.mapper

import br.com.soccer.domain.soccer.Game
import br.com.soccer.domain.soccer.GameDescription
import br.com.soccer.domain.soccer.GameTurn
import br.com.soccer.domain.soccer.Score
import br.com.soccer.networkservice.model.*

fun ScoreResponse.toDomain(parser: (ScoreResponse) -> Score) = parser(this)

fun Classification.toDomain(parser: (Classification) -> br.com.soccer.domain.soccer.Classification) =
    parser(this)

fun GameResponse.toDomain(parser: (GameResponse) -> Game) = parser(this)

fun Turn.toDomain(parser: (Turn) -> br.com.soccer.domain.soccer.Turn) = parser(this)

fun br.com.soccer.networkservice.model.Game.toDomain(parser: (br.com.soccer.networkservice.model.Game) -> GameTurn) =
    parser(this)

fun Teams.toDomain(parser: (Teams) -> br.com.soccer.domain.soccer.Teams) = parser(this)

fun Team.toDomain(parser: (Team) -> br.com.soccer.domain.soccer.Team) = parser(this)

fun Stadium.toDomain(parser: (Stadium) -> br.com.soccer.domain.soccer.Stadium) = parser(this)

fun GameDescriptionResponse.toDomain(parser: (GameDescriptionResponse) -> List<GameDescription>) =
    parser(this)
