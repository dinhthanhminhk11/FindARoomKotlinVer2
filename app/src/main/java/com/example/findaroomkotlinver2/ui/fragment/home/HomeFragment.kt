package com.example.findaroomkotlinver2.ui.fragment.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findaroomkotlinver2.MainViewModel
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.data.model.post.Post
import com.example.findaroomkotlinver2.data.util.Resource
import com.example.findaroomkotlinver2.databinding.FragmentHomeBinding
import com.example.findaroomkotlinver2.ui.adapter.PostAdapter
import com.example.findaroomkotlinver2.ui.adapter.PostTrendAdapter
import com.example.findaroomkotlinver2.ui.adapter.ShimmerAdsAdapter
import com.example.findaroomkotlinver2.ui.adapter.ShimmerPostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<FragmentHomeBinding>(), PostAdapter.Callback {
    private val viewModel: MainViewModel by viewModels()
    private var isDataLoaded = false
    override fun initView() {
        binding.listItemTrend.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rcvPost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.reLoad.setOnRefreshListener {
            viewModel.getAllListPostAdsHome()
            viewModel.getAllListPostHome()
        }
    }

    override fun initOnClickListener() {
        if (!isDataLoaded) {
            Log.i("MYTAG", "=========================")
            viewModel.getAllListPostAdsHome()
            viewModel.getAllListPostHome()
        }
    }

    override fun observeLiveData() {
        viewModel.allListPostAdsHome.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        isDataLoaded = true
                        binding.reLoad.isRefreshing = false
                        Log.i("MYTAG", "came here ${it.data.size}")
                        val postList = it.data
                        val adapter = PostTrendAdapter()
                        binding.listItemTrend.adapter = adapter
                        adapter.submitList(postList)
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                        val shimmerAdsAdapter = ShimmerAdsAdapter(5)
                        binding.listItemTrend.adapter = shimmerAdsAdapter
                    }
                }

                is Resource.Loading -> {
                    val shimmerAdsAdapter = ShimmerAdsAdapter(5)
                    binding.listItemTrend.adapter = shimmerAdsAdapter
                }

            }
        }

        viewModel.allListPostHome.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        isDataLoaded = true
                        binding.reLoad.isRefreshing = false
                        Log.i("MYTAG", "came here ${it.data.size}")
//                        val postList = it.data
                        val adapter = PostAdapter(it.data, this)
                        binding.rcvPost.adapter = adapter
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                        val shimmerAdapter = ShimmerPostAdapter(2)
                        binding.rcvPost.adapter = shimmerAdapter
                    }
                }

                is Resource.Loading -> {
                    val shimmerAdapter = ShimmerPostAdapter(2)
                    binding.rcvPost.adapter = shimmerAdapter
                }
            }
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onClickItem(item: Post) {
        findNavController().navigate(R.id.action_kingMainFragment_to_detailPostFragment)
    }

    override fun onClickMore(item: Post) {

    }

    override fun onClickComment(item: Post) {

    }

    override fun onClickProfile(item: Post) {

    }
}