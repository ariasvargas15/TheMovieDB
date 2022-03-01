package com.bsav.themoviedb.presentation.serie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsav.themoviedb.databinding.ItemProgramBinding
import com.bsav.themoviedb.domain.serie.model.Serie
import com.bsav.themoviedb.framework.helpers.loadImageFromPathWithBaseUrl

class SeriesAdapter(
    private val dataSet: List<Serie> = emptyList(),
) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProgramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(serie: Serie) {
            with(binding) {
                textPoster.text = serie.name
                serie.posterPath?.let {
                    imgPoster.loadImageFromPathWithBaseUrl(it)
                } ?: {
                    imgPoster.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val serie = dataSet[position]
        viewHolder.bind(serie)
    }

    override fun getItemCount() = dataSet.size

}