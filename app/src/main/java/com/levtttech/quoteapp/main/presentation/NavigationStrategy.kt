package com.levtttech.quoteapp.main.presentation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface NavigationStrategy {
    fun show(fragmentManager: FragmentManager, container: Int)
    abstract class Abstract : NavigationStrategy {
        override fun show(
            fragmentManager: FragmentManager,
            container: Int,
        ) {
            fragmentManager.beginTransaction().executeTransaction(container).commit()

        }

        abstract fun FragmentTransaction.executeTransaction(
            container: Int,
        ): FragmentTransaction

    }

    data class Replace(
        private val screen: Screen,
    ) : Abstract() {
        override fun FragmentTransaction.executeTransaction(
            container: Int,
        ): FragmentTransaction = replace(
            container, screen.fragment()
        ).addToBackStack(null)
    }

    data class Add(
        private val screen: Screen,
    ) : Abstract() {
        override fun FragmentTransaction.executeTransaction(
            container: Int,
        ): FragmentTransaction = add(
            container, screen.fragment()
        ).addToBackStack(null)
    }
}