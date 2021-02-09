package com.artera.movieexpertdagger.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.artera.movieexpertdagger.core.R
import com.artera.movieexpertdagger.core.databinding.ItemMovieBinding
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.utils.Constant
import com.bumptech.glide.Glide
import java.util.*

class TvAdapter : RecyclerView.Adapter<TvAdapter.MovieViewHolder>(), Filterable {

    private var listData = ArrayList<Tv>()
    private var listDataAll = ArrayList<Tv>()
    var onItemClick: ((Tv) -> Unit)? = null

    fun setData(newListData: List<Tv>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
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
        fun bind(tv: Tv) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.IMAGE_URL+tv.poster_path)
                    .into(itemMovieImage)
                itemMovieTitle.text = tv.name
                itemMovieRate.text = tv.vote_average.toString()
                itemMovieDate.text = tv.first_air_date
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
            val filteredData = ArrayList<Tv>()
            if (constraint.toString().isEmpty()){
                filteredData.addAll(listDataAll)
            }else{
                for (product in listDataAll){
                    if (product.name.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(
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
            listData.clear()
            listData.addAll(results!!.values as Collection<Tv>)
            notifyDataSetChanged()
        }
    }
}