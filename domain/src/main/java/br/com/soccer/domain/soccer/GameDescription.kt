package br.com.soccer.domain.soccer

data class GameDescription(
    val id: String?,
    val title: String?,
    val period: DescriptionPeriod?,
    val moment: String?,
    val type: String?,
    val text: String?
)