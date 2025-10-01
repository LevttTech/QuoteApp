package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
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
        val progress = view.findViewById<View>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = QuotesAdapter()
        recyclerView.adapter = adapter


        viewModel.observeQuotes(
            this) {
            adapter.listQuotes = it
        }
        button.setOnClickListener {
            viewModel.fetchQuote()
        }
        viewModel.observeState(viewLifecycleOwner) {
            it.show(textView)
        }


        viewModel.observeProgress(viewLifecycleOwner) {
            Log.d("FRAGMENT","heloo")
            progress.visibility = it
        }

    }
}