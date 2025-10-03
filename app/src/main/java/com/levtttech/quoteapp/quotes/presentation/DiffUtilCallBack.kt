package com.levtttech.quoteapp.quotes.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallBack(private val oldList: List<QuoteUi>,
                       private val newList: List<QuoteUi>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition].map(newList[newItemPosition])


    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition].equals(newList[newItemPosition])

}