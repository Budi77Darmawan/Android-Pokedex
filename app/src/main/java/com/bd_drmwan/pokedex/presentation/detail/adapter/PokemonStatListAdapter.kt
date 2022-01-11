package com.bd_drmwan.pokedex.presentation.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.commonextension.extensions.capitalize
import com.bd_drmwan.pokedex.core.model.Stat
import com.bd_drmwan.pokedex.databinding.ContainerListStatisticBinding

class PokemonStatListAdapter : RecyclerView.Adapter<PokemonStatListAdapter.ViewHolder>() {
    private var listStat = mutableListOf<Stat>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Stat>) {
        listStat.clear()
        listStat.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ContainerListStatisticBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stat: Stat) {
            binding.apply {
                val statValue = "(${stat.baseStat ?: 5})"
                tvStatisticValue.text = statValue
                tvStatistic.text = stat.stat?.name.toString().capitalize()
                indicatorStatistic.progress = stat.baseStat ?: 5
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContainerListStatisticBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stat = listStat[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = listStat.size
}