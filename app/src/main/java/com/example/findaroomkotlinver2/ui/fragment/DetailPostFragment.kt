package com.example.findaroomkotlinver2.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.databinding.FragmentDetailPostBinding


class DetailPostFragment : BaseViewModelFragment<FragmentDetailPostBinding>() {
    override fun initView() {
    }

    override fun initOnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeLiveData() {
    }

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentDetailPostBinding = FragmentDetailPostBinding.inflate(inflater, container, false)



}