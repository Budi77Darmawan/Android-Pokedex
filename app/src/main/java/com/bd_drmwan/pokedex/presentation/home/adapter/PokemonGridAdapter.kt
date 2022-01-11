package com.bd_drmwan.pokedex.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.commonextension.extensions.capitalize
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.loadImage
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.R
import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.core.model.imageUri
import com.bd_drmwan.pokedex.core.model.tag
import com.bd_drmwan.pokedex.databinding.ContainerPokemonGridBinding

class PokemonGridAdapter : RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder>() {
    private var listPokemon = mutableListOf<PokemonModel>()
    private var callback: ((PokemonModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<PokemonModel>) {
        listPokemon.clear()
        listPokemon.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<PokemonModel>) {
        listPokemon.addAll(data)
        notifyDataSetChanged()
    }

    fun onDetailClicked(callback: (PokemonModel) -> Unit) {
        this.callback = callback
    }

    inner class PokemonViewHolder(
        private val binding: ContainerPokemonGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonModel) {
            binding.apply {
                tvPokemonName.text = pokemon.name.capitalize()
                tvPokemonType1.text = pokemon.type?.getOrNull(0).toString().capitalize()
                tvPokemonId.text = pokemon.tag()

                if ((pokemon.type?.size ?: 1) > 1) {
                    tvPokemonType2.visible()
                    tvPokemonType2.text = pokemon.type?.getOrNull(1).toString().capitalize()
                } else {
                    tvPokemonType2.gone()
                }
                imgPokemon.loadImage(pokemon.imageUri(), R.color.white)

                root.setOnClickListener { callback?.invoke(pokemon) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            ContainerPokemonGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = listPokemon[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = listPokemon.size
}