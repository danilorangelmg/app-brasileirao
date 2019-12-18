package br.com.soccer.domain.soccer

enum class DescriptionPeriod(val periodValue: String) {
    FIM_DE_JOGO("Fim de Jogo"),
    SEGUNDO_TEMPO("Segundo Tempo"),
    INTERVALO("Intervalor"),
    PRIMEIRO_TEMPO("Primeiro Tempo"),
    PRE_JOGO("Pré-Jogo"),
    POS_JOGO("Pós-Jogo")
}