package com.bsav.themoviedb.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsav.themoviedb.databinding.ItemProgramBinding
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.framework.helpers.loadImageFromPathWithBaseUrl


class ProgramAdapter(
    private val dataSet: List<Program> = emptyList(),
    private val onClickProgram: OnClickProgram
) : RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProgramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(program: Program) {
            with(binding) {
                textPoster.text = program.name
                program.posterPath?.let {
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
        val program = dataSet[position]
        viewHolder.bind(program)
        viewHolder.itemView.setOnClickListener {
            when (program.type) {
                is ProgramType.Movie -> onClickProgram.navigateToMovie(program.id)
                is ProgramType.TvShow -> onClickProgram.navigateToTvShow(program.id)
            }
        }
    }

    override fun getItemCount() = dataSet.size

}