package com.bsav.themoviedb.presentation.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bsav.themoviedb.databinding.TvShowFragmentBinding
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import com.bsav.themoviedb.framework.helpers.loadImageFromPathWithBaseUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var binding: TvShowFragmentBinding
    private val args: TvShowFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tvShow.observe(viewLifecycleOwner, this::loadTvShow)
        viewModel.getTvShow(args.tvShowId)
    }

    private fun loadTvShow(tvShow: TvShow) {
        with(binding) {
            tvShow.backdropPath?.let { imgHeader.loadImageFromPathWithBaseUrl(it) }
            textTvShowTitle.text = tvShow.name
            textTvShowDescription.text = tvShow.overview
            textReleaseDate.text = tvShow.firstAirDate
            textUserScore.text = tvShow.voteAverage.toString()
        }
    }


}