package com.levtttech.quoteapp.quotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.databinding.FragmentQuotesBinding
import com.levtttech.quoteapp.databinding.QuoteItemBinding

class QuotesAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>(), Mapper.Unit<List<QuoteUi>> {
    private var listQuotes = mutableListOf<QuoteUi>()

    override fun onBindViewHolder(
        holder: QuoteViewHolder, position: Int,
    ) {
        holder.bind(listQuotes[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): QuoteViewHolder {
        val binding = QuoteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return QuoteViewHolder(
            binding, clickListener
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


    class QuoteViewHolder(
        private val binding: QuoteItemBinding,
        private val clickListener: ClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        val mapper = ListItemUi(binding.textViewRecycler)


        fun bind(item: QuoteUi) {
            item.map(mapper)
            binding.textViewRecycler.setOnClickListener {
                clickListener.click(item)
            }
        }
    }
}

interface ClickListener {
    fun click(item: QuoteUi)
}