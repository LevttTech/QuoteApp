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

        val detailsUi = arguments?.getParcelable(ARG_PARAM1, DetailsUi::class.java)!!

        viewModel.observe(viewLifecycleOwner) { state ->
            state.show(headTv, categoryTv, authorTv)
        }

        viewModel.init(savedInstanceState == null, detailsUi)

    }

    companion object {
        private const val TAG = "DetailsFragment"
        private const val ARG_PARAM1 = "param1"

        fun createFragment(item: DetailsUi): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, item)
                }
            }
        }
    }
}