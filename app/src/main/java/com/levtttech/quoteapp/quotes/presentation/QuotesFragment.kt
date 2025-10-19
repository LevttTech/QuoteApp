package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel>() {
    override val viewModel: QuotesViewModel by viewModels<QuotesViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_quotes

    companion object {
        private const val TAG = "QuotesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(savedInstanceState == null)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        val textView = view.findViewById<TextView>(R.id.textView)
        val button = view.findViewById<Button>(R.id.buttonLoadQuote)
        val progress = view.findViewById<View>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = QuotesAdapter(object : ClickListener {
            override fun click(item: QuoteUi) {
                viewModel.details(item)
            }
        })
        recyclerView.adapter = adapter

        viewModel.observeQuotes(viewLifecycleOwner) {
            Log.d(TAG,"adapter called")
            adapter.map(it)
        }

        button.setOnClickListener {
            viewModel.fetchQuote()
        }

        viewModel.observeState(viewLifecycleOwner) { state ->
            Log.d(TAG,"observstate called ")
            state.show(textView)
        }

        viewModel.observeProgress(viewLifecycleOwner) { visibility ->
            progress.visibility = visibility
            button.isClickable = visibility == GONE
            textView.visibility = if (visibility == GONE) VISIBLE else GONE
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored")
    }

}

