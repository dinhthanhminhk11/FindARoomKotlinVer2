package com.example.findaroomkotlinver2.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.findaroomkotlinver2.constant.AppConstant
import com.google.android.material.snackbar.Snackbar


abstract class BaseViewModelFragment<Binding : ViewBinding> : BaseFragment<Binding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupContentWindow()
        initView()
        initOnClickListener()
        observeLiveData()
    }

    abstract fun initView()
    abstract fun initOnClickListener()
    abstract fun observeLiveData()
    private fun setupContentWindow() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        requireActivity().window.statusBarColor = Color.TRANSPARENT
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    fun setStatusBarStyle(type: Int, statusBarColor: Int) {
        requireActivity().window.statusBarColor = statusBarColor
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = requireActivity().window.decorView
            decorView.systemUiVisibility = if (type == AppConstant.TYPE_LIGHT) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                0
            }
        }
    }

    private fun getColorFromRes(barColor: Int): Int {
        return ContextCompat.getColor(requireActivity(), barColor)
    }

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}