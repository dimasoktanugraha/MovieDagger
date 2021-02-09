package com.artera.movieexpertdagger.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.artera.movieexpertdagger.core.R
import com.artera.movieexpertdagger.core.databinding.ItemMovieBinding
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.utils.Constant
import com.bumptech.glide.Glide
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), Filterable {

    private var listData = ArrayList<Movie>()
    private var listDataAll = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listDataAll.clear()
        listData.addAll(newListData)
        listDataAll.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder  =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int  = listData.size

    override fun getFilter(): Filter = filter

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.IMAGE_URL+movie.poster_path)
                    .into(itemMovieImage)
                itemMovieTitle.text = movie.title
                itemMovieRate.text = movie.vote_average.toString()
                itemMovieDate.text = movie.release_date
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    private val filter: Filter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredData = ArrayList<Movie>()
            if (constraint.toString().isEmpty()){
                filteredData.addAll(listDataAll)
            }else{
                for (product in listDataAll){
                    if (product.title.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(
                            Locale.ROOT))){
                        filteredData.add(product)
                    }
                }
            }
            val filteredResults = FilterResults()
            filteredResults.values = filteredData

            return filteredResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            listData.clear()
//            listData.addAll(results!!.values as Collection<Movie>)
//            notifyDataSetChanged()
            val data = (results?.values as List<*>).filterIsInstance(Movie::class.java)
            listData.clear()
            listData.addAll(data)
            notifyDataSetChanged()
        }
    }
}