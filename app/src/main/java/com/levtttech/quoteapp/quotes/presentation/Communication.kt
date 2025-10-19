package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.levtttech.quoteapp.main.presentation.SingleLiveEvent

interface Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutate<T> : Mapper.Unit<T>
    interface Mutable<T> : Observe<T>, Mutate<T>

    abstract class Abstract<T> (
           protected val liveData: MutableLiveData<T> = MutableLiveData()
    ): Mutable<T> {
        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<T>
        ) {
            liveData.observe(owner, observer)
        }
    }

    abstract class Ui<T>(
         liveData: MutableLiveData<T> = MutableLiveData()
    ): Abstract<T>(liveData) {
        override fun map(source: T) {
            Log.d("Details","map value")
            liveData.value = source
        }
    }

    abstract class Post<T>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {
        override fun map(source: T) {
            liveData.postValue(source)
        }

    }

    abstract class SingleUi<T> :Ui<T>(SingleLiveEvent())
}