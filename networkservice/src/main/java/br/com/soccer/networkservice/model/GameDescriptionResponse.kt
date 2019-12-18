package br.com.soccer.networkservice.model

import com.google.gson.annotations.SerializedName

data class GameDescriptionResponse(
    @SerializedName("resource")
    val resource: Resource
)

data class Resource(
    @SerializedName("lances")
    val bids: List<Bid>
)

data class Bid(
    @SerializedName("id")
    val id: String?,
    @SerializedName("corpo")
    val body: Body?,
    @SerializedName("titulo")
    val title: String?,
    @SerializedName("periodo")
    val period: String?,
    @SerializedName("momento")
    val moment: String?,
    @SerializedName("tipoLance")
    val type: String?
)

data class Body(
    @SerializedName("blocks")
    val blocks: List<Block>
)

data class Block(
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String

)