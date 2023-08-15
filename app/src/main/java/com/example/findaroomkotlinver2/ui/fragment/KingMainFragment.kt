package com.example.findaroomkotlinver2.ui.fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.constant.AppConstant
import com.example.findaroomkotlinver2.databinding.FragmentKingMainBinding
import com.example.findaroomkotlinver2.ui.adapter.KingPagerAdapter


class KingMainFragment : BaseViewModelFragment<FragmentKingMainBinding>() {
    private var backPressedTime: Long = 0
    private val BACK_PRESS_INTERVAL: Long = 2000
    override fun initView() {
        duplicationBack()

        val viewPager: ViewPager2 = binding.viewPager
        val pagerAdapter = KingPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter

        setStatusBarStyle(AppConstant.TYPE_LIGHT, Color.WHITE)

        viewPager.isUserInputEnabled =
            false // Không cho phép cuộn viewpager để ngăn việc swipe qua tab khác

        binding.navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> viewPager.setCurrentItem(0, false)
                R.id.nav_notification -> viewPager.setCurrentItem(1, false)
                R.id.nav_chat -> viewPager.setCurrentItem(2, false)
                R.id.nav_user -> viewPager.setCurrentItem(3, false)
            }
            true
        }
    }

    override fun initOnClickListener() {
    }

    override fun observeLiveData() {
    }

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentKingMainBinding {
        return FragmentKingMainBinding.inflate(inflater, container, false)
    }

    private fun duplicationBack() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime > BACK_PRESS_INTERVAL) {
                    Toast.makeText(
                        requireContext(), getString(R.string.dup_back), Toast.LENGTH_SHORT
                    ).show()
                    backPressedTime = currentTime
                } else {
                    isEnabled = false
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )
    }

}