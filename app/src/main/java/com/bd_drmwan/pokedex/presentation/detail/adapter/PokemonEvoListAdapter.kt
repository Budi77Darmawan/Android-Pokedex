package com.bd_drmwan.pokedex.presentation.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.commonextension.extensions.capitalize
import com.bd_drmwan.commonextension.extensions.loadImage
import com.bd_drmwan.pokedex.core.model.Varieties
import com.bd_drmwan.pokedex.core.model.imageUri
import com.bd_drmwan.pokedex.databinding.ContainerListEvolutionBinding

class PokemonEvoListAdapter: RecyclerView.Adapter<PokemonEvoListAdapter.ViewHolder>() {
    private var listEvolution = mutableListOf<Varieties>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Varieties>) {
        listEvolution.clear()
        listEvolution.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ContainerListEvolutionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(evo: Varieties) {
            binding.apply {
                tvPokemonName.text = evo.pokemon?.name.toString().capitalize()
                imgPokemon.loadImage(evo.pokemon?.imageUri().toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvoListAdapter.ViewHolder {
        val binding =
            ContainerListEvolutionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonEvoListAdapter.ViewHolder, position: Int) {
        val evo = listEvolution[position]
        holder.bind(evo)
    }

    override fun getItemCount(): Int = listEvolution.size
}