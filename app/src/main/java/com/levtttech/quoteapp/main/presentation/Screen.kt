package com.levtttech.quoteapp.main.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.quotes.presentation.QuotesFragment

sealed interface Screen {

    fun show(fragmentManager: FragmentManager, container: Int)

    object Details : Screen {
        override fun show(
            fragmentManager: FragmentManager,
            container: Int,
        ) {
            fragmentManager.beginTransaction().replace(container, DetailsFragment())
                .commit()
        }
    }

    object Quote: Screen {
        override fun show(
            fragmentManager: FragmentManager,
            container: Int,
        ) {
            fragmentManager.beginTransaction().replace(container, QuotesFragment())
                .commit()
        }
    }
}