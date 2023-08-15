package com.example.findaroomkotlinver2.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.databinding.FragmentHomeBinding
import com.example.findaroomkotlinver2.databinding.FragmentNotificationBinding

class NotificationFragment : BaseViewModelFragment<FragmentNotificationBinding>() {
    override fun initView() {
    }

    override fun initOnClickListener() {
        binding.login.setOnClickListener {
            findNavController().navigate(
                R.id.action_kingMainFragment_to_loginFragment
            )
        }
    }

    override fun observeLiveData() {
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding = FragmentNotificationBinding.inflate(inflater, container, false)

}