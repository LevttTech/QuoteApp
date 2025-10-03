package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel>() {
    override val viewModel: QuotesViewModel by lazy {  viewModels<QuotesViewModel>().value}
    override val layoutId: Int
        get() = R.layout.fragment_quotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textView)
        val button = view.findViewById<Button>(R.id.buttonLoadQuote)
        val progress = view.findViewById<View>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = QuotesAdapter()
        recyclerView.adapter = adapter


        viewModel.observeQuotes(this) {
            adapter.listQuotes = it
        }

        button.setOnClickListener {
            viewModel.fetchQuote()
        }

        viewModel.observeState(viewLifecycleOwner) { state ->
            state.show(textView)
        }

        viewModel.observeProgress(viewLifecycleOwner) { visibility ->
            progress.visibility = visibility
            button.isClickable = visibility == GONE
            textView.visibility = if (visibility == GONE) VISIBLE else GONE
        }

        viewModel.init(savedInstanceState == null)
    }


}