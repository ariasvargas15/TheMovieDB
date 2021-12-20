package com.bsav.themoviedb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsav.themoviedb.databinding.ItemProgramBinding
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.framework.helpers.loadImageFromPathWithBaseUrl


class ProgramAdapter(private val dataSet: List<Program> = emptyList()) : RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProgramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(program: Program) {
            program.posterPath?.let {
                binding.imgPoster.loadImageFromPathWithBaseUrl(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val program = dataSet[position]
        viewHolder.bind(program)
    }

    override fun getItemCount() = dataSet.size

}