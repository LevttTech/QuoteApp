package com.levtttech.quoteapp.quotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>(),
    Mapper.Unit<List<QuoteUi>> {
    var listQuotes = mutableListOf<QuoteUi>()

    override fun onBindViewHolder(
        holder: QuoteViewHolder, position: Int,
    ) {
        holder.bind(listQuotes[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.quote_item, parent, false
            )
        )
    }

    override fun map(source: List<QuoteUi>) {
        val diff = DiffUtilCallBack(listQuotes, source)
        val result = DiffUtil.calculateDiff(diff)
        listQuotes.clear()
        listQuotes.addAll(source)
        result.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int = listQuotes.size


    class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textViewRecycler)

        val mapper = ListItemUi(textView)
        fun bind(item: QuoteUi) {
            item.map(mapper)
        }
    }
}