package com.bsav.themoviedb.presentation.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bsav.themoviedb.databinding.ProgramsFragmentBinding
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.presentation.ProgramAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramsFragment : Fragment() {

    private val viewModel: ProgramsViewModel by viewModels()
    private lateinit var binding: ProgramsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProgramsFragmentBinding.inflate(inflater, container, false)
        viewModel.getMovies()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.run {
            popularMovies.observe(viewLifecycleOwner) {
                loadPopularMovies(it)
            }
            topRatedMovies.observe(viewLifecycleOwner) {
                loadTopRatedMovies(it)
            }
            popularTvShows.observe(viewLifecycleOwner) {
                loadPopularTvShows(it)
            }
            topRatedTvShows.observe(viewLifecycleOwner) {
                loadTopRatedTvShows(it)
            }
        }
    }

    private fun loadPopularMovies(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerPopularMovies.adapter = adapter
    }

    private fun loadTopRatedMovies(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerTopRatedMovies.adapter = adapter
    }

    private fun loadPopularTvShows(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerPopularTvShows.adapter = adapter
    }

    private fun loadTopRatedTvShows(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerTopRatedTvShows.adapter = adapter
    }

}