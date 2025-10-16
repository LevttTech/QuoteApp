package com.levtttech.quoteapp.main.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.levtttech.quoteapp.R

interface NavigationStrategy {
    fun execute(fragmentManager: FragmentManager, container : Int,fragment: Fragment)
    class Base : NavigationStrategy {
        override fun execute(
            fragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            fragmentManager.beginTransaction().replace(container,fragment)
                .commit()
        }
    }
}