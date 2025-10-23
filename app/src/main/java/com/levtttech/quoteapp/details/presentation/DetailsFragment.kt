package com.levtttech.quoteapp.details.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel>() {
    override val viewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headTv = view.findViewById<TextView>(R.id.quoteTextView)
        val categoryTv = view.findViewById<TextView>(R.id.categoryTextView)
        val authorTv = view.findViewById<TextView>(R.id.authorTextView)

        viewModel.observe(viewLifecycleOwner) { state ->
            state.show(headTv, categoryTv, authorTv)
        }

        viewModel.init(savedInstanceState == null)

    }


}