package com.bd_drmwan.pokedex.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesResponse(
    val id: Int?,
    val name: String?,
    val color: BasicResponse?,
    val varieties: List<Varieties>?
): Parcelable

@Parcelize
data class Varieties(
    val pokemon: BasicResponse?
): Parcelable