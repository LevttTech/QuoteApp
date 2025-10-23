package com.levtttech.quoteapp.details.data

import com.levtttech.quoteapp.details.presentation.DetailsUi
import javax.inject.Inject

interface QuoteDetailsRepository {


    interface Save {
        fun save(item: DetailsUi)
    }

    interface Read {
        fun read(): DetailsUi
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(): Mutable {
        lateinit var data: DetailsUi

        override fun read(): DetailsUi = data

        override fun save(item: DetailsUi) {
            data = item.copy()
        }
    }
}