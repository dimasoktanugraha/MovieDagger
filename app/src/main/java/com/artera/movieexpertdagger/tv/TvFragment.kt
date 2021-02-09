package com.artera.movieexpertdagger.tv

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
import com.artera.movieexpertdagger.core.ui.TvAdapter
import com.artera.movieexpertdagger.detail.DetailActivity
import com.artera.movieexpertdagger.di.ViewModelFactory
import com.artera.movieexpertdagger.databinding.FragmentTvBinding
import javax.inject.Inject

class TvFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val tvViewModel: TvViewModel by viewModels {
        factory
    }

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val tvAdapter = TvAdapter()
            tvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.TV_EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            tvViewModel.getTvs(1).observe(viewLifecycleOwner, {tv ->
                if (tv != null){
                    when(tv) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            tvAdapter.setData(tv.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tv.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }

            binding.tvSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvAdapter.filter.filter(p0)
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