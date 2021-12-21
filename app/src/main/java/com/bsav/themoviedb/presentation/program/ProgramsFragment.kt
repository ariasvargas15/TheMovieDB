package com.bsav.themoviedb.presentation.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bsav.themoviedb.databinding.ProgramsFragmentBinding
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.presentation.OnClickProgram
import com.bsav.themoviedb.presentation.ProgramAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramsFragment : Fragment(), OnClickProgram {

    private val viewModel: ProgramsViewModel by viewModels()
    private lateinit var binding: ProgramsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProgramsFragmentBinding.inflate(inflater, container, false)
        viewModel.getMovies()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies()
            binding.swipeRefresh.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.run {
            popularMovies.observe(viewLifecycleOwner, this@ProgramsFragment::loadPopularMovies)
            topRatedMovies.observe(viewLifecycleOwner, this@ProgramsFragment::loadTopRatedMovies)
            popularTvShows.observe(viewLifecycleOwner, this@ProgramsFragment::loadPopularTvShows)
            topRatedTvShows.observe(viewLifecycleOwner, this@ProgramsFragment::loadTopRatedTvShows)
        }
    }

    private fun loadPopularMovies(movies: List<Program>) {
        val adapter = ProgramAdapter(movies, this)
        binding.recyclerPopularMovies.adapter = adapter
    }

    private fun loadTopRatedMovies(movies: List<Program>) {
        val adapter = ProgramAdapter(movies, this)
        binding.recyclerTopRatedMovies.adapter = adapter
    }

    private fun loadPopularTvShows(movies: List<Program>) {
        val adapter = ProgramAdapter(movies, this)
        binding.recyclerPopularTvShows.adapter = adapter
    }

    private fun loadTopRatedTvShows(movies: List<Program>) {
        val adapter = ProgramAdapter(movies, this)
        binding.recyclerTopRatedTvShows.adapter = adapter
    }

    override fun navigateToMovie(id: Int) {
        val action = ProgramsFragmentDirections.actionProgramsFragmentToMovieFragment(id)
        findNavController().navigate(action)
    }

    override fun navigateToTvShow(id: Int) {
        TODO("Not yet implemented")
    }

}