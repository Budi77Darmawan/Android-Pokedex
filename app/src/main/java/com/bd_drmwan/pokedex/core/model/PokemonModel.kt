package com.bd_drmwan.pokedex.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val id: Int,
    val name: String,
    val type: MutableList<String>?,
    val stat: MutableList<Stat>?,
    val speciesId: Int
) : Parcelable

fun PokemonModel.tag(): String {
    return when (id.toString().length) {
        1 -> "#00$id"
        2 -> "#0$id"
        3 -> "#$id"
        else -> "#$id"
    }
}

fun PokemonModel.imageUri(): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}