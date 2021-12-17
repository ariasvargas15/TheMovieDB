package com.bsav.themoviedb.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bsav.themoviedb.databinding.MoviesFragmentBinding
import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.presentation.ProgramAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: MoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        viewModel.getPopularMovies()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner) {
            loadMovies(it)
        }
    }

    private fun loadMovies(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerPopularMovies.adapter = adapter
    }

}