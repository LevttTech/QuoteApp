package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel>() {
    override val viewModel: QuotesViewModel by viewModels<QuotesViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_quotes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textView)
        val button = view.findViewById<Button>(R.id.buttonLoadQuote)
        viewModel.liveData.observe(
            viewLifecycleOwner) {
            it.show(textView)
        }

        button.setOnClickListener {
            viewModel.loadQuote()
        }

    }
}