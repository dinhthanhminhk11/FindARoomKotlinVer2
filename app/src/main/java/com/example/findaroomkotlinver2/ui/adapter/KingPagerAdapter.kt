package com.example.findaroomkotlinver2.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.findaroomkotlinver2.ui.fragment.home.ChatFragment
import com.example.findaroomkotlinver2.ui.fragment.home.HomeFragment
import com.example.findaroomkotlinver2.ui.fragment.home.NotificationFragment
import com.example.findaroomkotlinver2.ui.fragment.home.UserFragment

class KingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> NotificationFragment()
            2 -> ChatFragment()
            3 -> ChatFragment()
            4 -> UserFragment()
            else -> HomeFragment()
        }
    }
}