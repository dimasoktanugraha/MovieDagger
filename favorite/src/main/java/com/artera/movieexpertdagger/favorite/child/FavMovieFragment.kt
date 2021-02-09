package com.artera.movieexpertdagger.favorite.child

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.artera.movieexpertdagger.core.di.CoreComponent
import com.artera.movieexpertdagger.core.di.DaggerCoreComponent
import com.artera.movieexpertdagger.core.ui.MovieAdapter
import com.artera.movieexpertdagger.databinding.FragmentFavMovieBinding
import com.artera.movieexpertdagger.detail.DetailActivity
import com.artera.movieexpertdagger.di.ViewModelFactory
import com.artera.movieexpertdagger.favorite.FavoriteViewModel
import com.artera.movieexpertdagger.favorite.di.DaggerFavComponent
import javax.inject.Inject

class FavMovieFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavComponent.builder().coreComponent(coreComponent).build().inject(this)
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

            binding!!.progressBar.visibility = View.VISIBLE

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { movie ->
                movieAdapter.setData(movie)
                binding!!.progressBar.visibility = View.GONE
                binding!!.viewEmpty.root.visibility = if (movie.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding!!.rvFavMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}