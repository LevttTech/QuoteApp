package com.levtttech.quoteapp.details.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import com.levtttech.quoteapp.quotes.presentation.QuoteUi

class DetailsFragment : BaseFragment<DetailsViewModel>() {
    override val viewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_details

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headTv = view.findViewById<TextView>(R.id.quoteTextView)
        val categoryTv = view.findViewById<TextView>(R.id.categoryTextView)
        val authorTv = view.findViewById<TextView>(R.id.authorTextView)

        val quoteUi = arguments?.getParcelable("item", QuoteUi::class.java)!!
        quoteUi.map(object : QuoteUi.Mapper<Unit> {
            override fun map(
                id: Int,
                quote: String,
                author: String,
                category: String,
            ) {
                headTv.text = quote
                categoryTv.text = category
                authorTv.text = author
            }
        })
    }
}