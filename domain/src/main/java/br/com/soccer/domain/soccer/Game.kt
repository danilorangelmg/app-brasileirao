package br.com.soccer.domain.soccer

import android.os.Parcel
import android.os.Parcelable

data class Game(
    val turns: List<Turn>
)

data class Turn(
    val num: Int,
    val games: List<GameTurn>
)

data class GameTurn(
    val id: Long,
    val gameDate: String?,
    val gameHour: String?,
    val guestScore: Int?,
    val homeScore: Int?,
    val teams: Teams?,
    val stadium: Stadium?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readParcelable<Teams>(Teams::class.java.classLoader),
        source.readParcelable<Stadium>(Stadium::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(gameDate)
        writeString(gameHour)
        writeValue(guestScore)
        writeValue(homeScore)
        writeParcelable(teams, 0)
        writeParcelable(stadium, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GameTurn> = object : Parcelable.Creator<GameTurn> {
            override fun createFromParcel(source: Parcel): GameTurn = GameTurn(source)
            override fun newArray(size: Int): Array<GameTurn?> = arrayOfNulls(size)
        }
    }
}

data class Teams(
    val home: Team,
    val guest: Team
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<Team>(Team::class.java.classLoader),
        source.readParcelable<Team>(Team::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(home, 0)
        writeParcelable(guest, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Teams> = object : Parcelable.Creator<Teams> {
            override fun createFromParcel(source: Parcel): Teams = Teams(source)
            override fun newArray(size: Int): Array<Teams?> = arrayOfNulls(size)
        }
    }
}

data class Team(
    val id: Long,
    val name: String,
    val nickname: String,
    val shieldUlr: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(nickname)
        writeString(shieldUlr)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
            override fun createFromParcel(source: Parcel): Team = Team(source)
            override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
        }
    }
}

data class Stadium(
    val name: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Stadium> = object : Parcelable.Creator<Stadium> {
            override fun createFromParcel(source: Parcel): Stadium = Stadium(source)
            override fun newArray(size: Int): Array<Stadium?> = arrayOfNulls(size)
        }
    }
}