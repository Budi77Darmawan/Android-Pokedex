package com.bd_drmwan.pokedex.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ListPokemonResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<BasicResponse>?
) : Parcelable

@Parcelize
data class BasicResponse(
    val name: String?,
    val url: String?
) : Parcelable

val BasicResponse.id
    get() = run {
        val id = this.url?.split("/")
            ?.filter { url -> url.isNotBlank() } ?: listOf("0")
        id.last().toInt()
    }

fun BasicResponse.imageUri(): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}

@Parcelize
data class PokemonResponse(
    val abilities: List<Ability>?,
    val id: Int?,
    val name: String?,
    val species: BasicResponse?,
    val stats: List<Stat>,
    val types: List<Type>,
    val height: Int?,
    val weight: Int?
) : Parcelable

@Parcelize
data class Ability(
    val ability: BasicResponse?,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int?
) : Parcelable

@Parcelize
data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int?,
    val effort: Int?,
    val stat: BasicResponse?
) : Parcelable

@Parcelize
data class Type(
    val slot: Int?,
    val type: BasicResponse?
) : Parcelable
