package com.bsav.themoviedb.presentation.serie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bsav.themoviedb.databinding.SerieFragmentBinding
import com.bsav.themoviedb.presentation.search.calculateNoOfColumns
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerieFragment : Fragment() {

    private lateinit var binding: SerieFragmentBinding
    private val viewModel: SerieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SerieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.series.observe(viewLifecycleOwner, ::loadData)
        viewModel.getPopularSeries()
    }

    private fun loadData(serie: SerieState) {
        when (serie) {
            is SerieState.Loading -> {
            }
            is SerieState.Error -> {
            }
            is SerieState.Series -> {
                binding.recyclerProgramsSearch.run {
                    layoutManager = GridLayoutManager(requireContext(), requireContext().calculateNoOfColumns(160f))
                    adapter = SeriesAdapter(serie.list)
                }
            }
        }
    }

}