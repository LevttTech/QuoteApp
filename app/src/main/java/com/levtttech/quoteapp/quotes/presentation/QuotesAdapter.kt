package com.levtttech.quoteapp.quotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {
    var listQuotes: List<QuoteUi> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(
        holder: QuoteViewHolder, position: Int
    ) {
        holder.bind(listQuotes[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): QuoteViewHolder = QuoteViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.quote_item, parent, false
        )
    )


    override fun getItemCount(): Int = listQuotes.size


    class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textViewRecycler)

        val mapper = ListItemUi(textView)
        fun bind(item: QuoteUi) {
            item.map(mapper)
        }
    }
}