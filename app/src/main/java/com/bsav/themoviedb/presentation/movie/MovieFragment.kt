package com.bsav.themoviedb.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bsav.themoviedb.databinding.MovieFragmentBinding
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.framework.helpers.loadImageFromPathWithBaseUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: MovieFragmentBinding
    private val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, this::loadMovie)
        viewModel.getMovie(args.movieId)
    }

    private fun loadMovie(movie: Movie) {
        with(binding){
            movie.backdropPath?.let { imgHeader.loadImageFromPathWithBaseUrl(it) }
            textMovieTitle.text = movie.title
            textMovieDescription.text = movie.overview
            textReleaseDate.text = movie.releaseDate
            textUserScore.text = movie.voteAverage.toString()
        }
    }
}