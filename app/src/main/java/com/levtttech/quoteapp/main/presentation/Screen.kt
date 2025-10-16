package com.levtttech.quoteapp.main.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.quotes.presentation.QuotesFragment

sealed interface Screen {

    fun show(fragmentManager: FragmentManager, container: Int)

    data class Details(
        private val args: Bundle
    ) : Screen {
        override fun show(
            fragmentManager: FragmentManager,
            container: Int,
        ) {
            fragmentManager.beginTransaction().replace(container, DetailsFragment().apply { arguments=args })
                .commit()
        }
    }

    data class Quote(
        private val args: Bundle
    ): Screen {
        override fun show(
            fragmentManager: FragmentManager,
            container: Int,
        ) {
            fragmentManager.beginTransaction().replace(container, QuotesFragment().apply { arguments = args})
                .commit()
        }
    }
}