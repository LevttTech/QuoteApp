package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.databinding.FragmentQuotesBinding
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel, FragmentQuotesBinding>() {
    override val viewModel: QuotesViewModel by viewModels<QuotesViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentQuotesBinding = FragmentQuotesBinding.inflate(
        inflater,
        container,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(savedInstanceState == null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = QuotesAdapter(object : ClickListener {
            override fun click(item: QuoteUi) {
                viewModel.details(item)
            }
        })
        with(binding) {
            recyclerView.adapter = adapter
            buttonLoadQuote.setOnClickListener {
                viewModel.fetchQuote()
            }
        }

        viewModel.observeQuotes(viewLifecycleOwner) {
            adapter.map(it)
        }


        viewModel.observeState(viewLifecycleOwner) { state ->
            state.show(binding.textView)
        }

        viewModel.observeProgress(viewLifecycleOwner) { visibility ->
            with(binding) {
                progressBar.visibility = visibility
                buttonLoadQuote.isClickable = visibility == GONE
                textView.visibility = if (visibility == GONE) VISIBLE else GONE
            }
        }
    }
}

