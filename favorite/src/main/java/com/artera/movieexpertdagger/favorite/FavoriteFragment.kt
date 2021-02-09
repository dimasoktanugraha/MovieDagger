package com.artera.movieexpertdagger.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artera.movieexpertdagger.core.di.CoreComponent
import com.artera.movieexpertdagger.core.di.DaggerCoreComponent
import com.artera.movieexpertdagger.databinding.FragmentFavoriteBinding
import com.artera.movieexpertdagger.favorite.adapter.FavoritePagerAdapter
import com.artera.movieexpertdagger.favorite.di.DaggerFavComponent

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            DaggerFavComponent.builder().coreComponent(coreComponent).build().inject(this)

            val tabAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
            binding.favViewpager.adapter = tabAdapter
            binding.favTab.setupWithViewPager(binding.favViewpager)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}