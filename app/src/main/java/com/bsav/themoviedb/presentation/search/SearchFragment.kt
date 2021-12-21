package com.bsav.themoviedb.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bsav.themoviedb.databinding.SearchFragmentBinding
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.presentation.ProgramAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    override fun onResume() {
        super.onResume()
        setSearchBar()
    }

    private fun setSearchBar() {
        binding.searchBar.run {
            queryHint = "Search a movie or a tv show"
            post {
                requestFocus()
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchProgram(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotBlank()) {
                        viewModel.searchProgram(newText)
                    } else {
                        binding.recyclerProgramsSearch.adapter = null
                    }
                    return false
                }
            })
        }
    }

    private fun initializeObservers() {
        viewModel.programs.observe(viewLifecycleOwner) {
            loadPrograms(it)
        }
    }

    private fun loadPrograms(movies: List<Program>) {
        val adapter = ProgramAdapter(movies)
        binding.recyclerProgramsSearch.layoutManager = GridLayoutManager(requireContext(), requireContext().calculateNoOfColumns(160f))
        binding.recyclerProgramsSearch.adapter = adapter
    }

}

fun Context.calculateNoOfColumns(columnWidthDp: Float): Int {
    val displayMetrics = resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt()
}