package com.bd_drmwan.pokedex.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ListPokemonResponse (
    val count: Long?,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>?
): Parcelable

@Parcelize
data class Pokemon (
    val name: String?,
    val url: String?
): Parcelable

@Parcelize
data class PokemonResponse (
    val abilities: List<Ability>?,
    @SerializedName("base_experience")
    val baseExperience: Long?,
    val forms: List<Species?>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>?,
    val height: Long?,
    val id: Long?,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,
    val moves: List<Move>?,
    val name: String?,
    val order: Long?,
    val species: Species?,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long?
): Parcelable

@Parcelize
data class Ability (
    val ability: Species?,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Long?
): Parcelable

@Parcelize
data class Species (
    val name: String?,
    val url: String?
): Parcelable

@Parcelize
data class GameIndex (
    @SerializedName("game_index")
    val gameIndex: Long?,
    val version: Species?
): Parcelable

@Parcelize
data class Move (
    val move: Species?,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
): Parcelable

@Parcelize
data class VersionGroupDetail (
    @SerializedName("level_learned_at")
    val levelLearnedAt: Long?,
    @SerializedName("move_learn_method")
    val moveLearnMethod: Species?,
    @SerializedName("version_group")
    val versionGroup: Species?
): Parcelable

@Parcelize
data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: Sprites?
): Parcelable

@Parcelize
data class GenerationIv (
    @SerializedName("diamond-pearl")
    val diamondPearl: Sprites?,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: Sprites?,
    val platinum: Sprites?
): Parcelable

@Parcelize
data class Versions (
    @SerializedName("generation-i")
    val generationI: GenerationI?,
    @SerializedName("generation-ii")
    val generationII: GenerationIi?,
    @SerializedName("generation-iii")
    val generationIII: GenerationIii,
    @SerializedName("generation-iv")
    val generationIV: GenerationIv,
    @SerializedName("generation-v")
    val generationV: GenerationV,
    @SerializedName("generation-vi")
    val generationVI: Map<String?, Home>,
    @SerializedName("generation-vii")
    val generationVII: GenerationVii,
    @SerializedName("generation-viii")
    val generationVIII: GenerationViii
): Parcelable

@Parcelize
data class Sprites (
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_female")
    val backFemale: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?,
    val other: Other? = null,
    val versions: Versions? = null,
    val animated: Sprites? = null
): Parcelable

@Parcelize
data class GenerationI (
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    val yellow: RedBlue
): Parcelable

@Parcelize
data class RedBlue (
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_gray")
    val backGray: String?,
    @SerializedName("back_transparent")
    val backTransparent: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_gray")
    val frontGray: String?,
    @SerializedName("front_transparent")
    val frontTransparent: String?
): Parcelable

@Parcelize
data class GenerationIi (
    val crystal: Crystal,
    val gold: Gold,
    val silver: Gold
): Parcelable

@Parcelize
data class Crystal (
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("back_shiny_transparent")
    val backShinyTransparent: String?,
    @SerializedName("back_transparent")
    val backTransparent: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_transparent")
    val frontShinyTransparent: String?,
    @SerializedName("front_transparent")
    val frontTransparent: String?
): Parcelable

@Parcelize
data class Gold (
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_transparent")
    val frontTransparent: String?
): Parcelable

@Parcelize
data class GenerationIii (
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: Gold,
    @SerializedName("ruby-sapphire")
    val rubySapphire: Gold
): Parcelable

@Parcelize
data class Emerald (
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
): Parcelable

@Parcelize
data class Home (
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?
): Parcelable

@Parcelize
data class GenerationVii (
    val icons: DreamWorld,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: Home
): Parcelable

@Parcelize
data class DreamWorld (
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?
): Parcelable

@Parcelize
data class GenerationViii (
    val icons: DreamWorld
): Parcelable

@Parcelize
data class Other (
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
): Parcelable

@Parcelize
data class OfficialArtwork (
    @SerializedName("front_default")
    val frontDefault: String?
): Parcelable

@Parcelize
data class Stat (
    @SerializedName("base_stat")
    val baseStat: Long?,
    val effort: Long?,
    val stat: Species?
): Parcelable

@Parcelize
data class Type (
    val slot: Long?,
    val type: Species?
): Parcelable
