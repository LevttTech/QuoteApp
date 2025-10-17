package com.levtttech.quoteapp.main.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.quotes.presentation.QuotesFragment

sealed interface Screen {

    fun show(fragmentManager: FragmentManager, container: Int)
    abstract class Abstract : Screen {
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

    data class Details(
        private val args: Bundle,
    ) : Abstract() {
        override fun FragmentTransaction.executeTransaction(
            container: Int,
        ): FragmentTransaction = replace(
            container, DetailsFragment().apply { arguments = args })
    }

    data class Quotes(
        private val args: Bundle,
    ) : Abstract() {
        override fun FragmentTransaction.executeTransaction(
            container: Int,
        ): FragmentTransaction = replace(
            container, QuotesFragment().apply { arguments = args })
    }
}