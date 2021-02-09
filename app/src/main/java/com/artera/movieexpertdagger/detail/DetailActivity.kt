package com.artera.movieexpertdagger.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.artera.movieexpertdagger.MyApplication
import com.artera.movieexpertdagger.R
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.utils.Constant
import com.artera.movieexpertdagger.databinding.ActivityDetailBinding
import com.artera.movieexpertdagger.di.ViewModelFactory
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_EXTRA_DATA = "MOVIE_EXTRA_DATA"
        const val TV_EXTRA_DATA = "TV_EXTRA_DATA"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailViewModel: DetailViewModel by viewModels {
        factory
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val detailMovie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA_DATA)
        if (detailMovie!=null){
            showDetailMovie(detailMovie)
        }else{
            val detailTv = intent.getParcelableExtra<Tv>(TV_EXTRA_DATA)
            showDetailTv(detailTv)
        }

        binding.detailBack.setOnClickListener {
            finish()
        }
    }

    private fun showDetailTv(detail: Tv?) {
        detail?.let {
            binding.detailTitle.text = detail.name
            binding.detailOverview.text = detail.overview
            binding.detailRate.rating = detail.vote_average ?: 0f
            binding.detailRateNumber.text = detail.vote_average!!.toString()

            setImage(detail.backdrop_path!!, binding.detailBackdrop)
            setImage(detail.poster_path, binding.detailPoster)

            var statusFavorite = detail.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteTv(detail, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

        }
    }

    private fun showDetailMovie(detail: Movie?) {
        detail?.let {
            binding.detailTitle.text = detail.title
            binding.detailOverview.text = detail.overview
            binding.detailRate.rating = detail.vote_average
            binding.detailRateNumber.text = detail.vote_average.toString()

            setImage(detail.backdrop_path, binding.detailBackdrop)
            setImage(detail.poster_path, binding.detailPoster)

            var statusFavorite = detail.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detail, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setImage(link: String, view: ImageView) {
        Glide.with(this@DetailActivity)
                .load(Constant.IMAGE_URL+link)
                .into(view)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detailFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.detailFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}