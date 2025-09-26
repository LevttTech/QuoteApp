package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel>() {
    override val viewModel: QuotesViewModel by viewModels<QuotesViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_quotes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}