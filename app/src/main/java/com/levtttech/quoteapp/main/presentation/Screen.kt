package com.levtttech.quoteapp.main.presentation

import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.details.presentation.DetailsUi
import com.levtttech.quoteapp.quotes.presentation.QuotesFragment

sealed class Screen {

    abstract fun fragment(): BaseFragment<*>

    class Details(private val item: DetailsUi) : Screen() {
        override fun fragment(): BaseFragment<*> {
            return DetailsFragment.createFragment(item)
        }
    }

    class Quotes : Screen() {
        override fun fragment(): BaseFragment<*> {
            return QuotesFragment()
        }
    }
}