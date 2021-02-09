package com.artera.movieexpertdagger.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.artera.movieexpertdagger.MyApplication
import com.artera.movieexpertdagger.R
import com.artera.movieexpertdagger.core.data.Resource
import com.artera.movieexpertdagger.core.ui.MovieAdapter
import com.artera.movieexpertdagger.detail.DetailActivity
import com.artera.movieexpertdagger.di.ViewModelFactory
import com.artera.movieexpertdagger.databinding.FragmentMovieBinding
import javax.inject.Inject

class MovieFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val movieViewModel: MovieViewModel by viewModels {
        factory
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            movieViewModel.getMovie(1).observe(viewLifecycleOwner, {movie ->
                if (movie != null){
                    when(movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            binding.movieSearch.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    movieAdapter.filter.filter(p0)
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}