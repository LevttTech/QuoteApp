package com.levtttech.quoteapp.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.details.presentation.DetailsFragment
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : BaseFragment<QuotesViewModel>() {
    override val viewModel: QuotesViewModel by viewModels<QuotesViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_quotes

    companion object {
        private const val TAG = "QuotesFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализация компонентов, которые не зависят от UI
        // Восстановление состояния фрагмента
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
                viewModel.details()
            }
        })
        recyclerView.adapter = adapter

        viewModel.observeQuotes(viewLifecycleOwner) {
            adapter.map(it)
        }

        button.setOnClickListener {
            viewModel.fetchQuote()
        }

        viewModel.observeState(viewLifecycleOwner) { state ->
            state.show(textView)
        }

        viewModel.observeProgress(viewLifecycleOwner) { visibility ->
            progress.visibility = visibility
            button.isClickable = visibility == GONE
            textView.visibility = if (visibility == GONE) VISIBLE else GONE
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onStart() {
        super.onStart()
        // Фрагмент становится видимым для пользователя
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        // Фрагмент начинает взаимодействие с пользователем
        // Восстановление анимаций, обновление данных и т.д.
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        // Фрагмент теряет фокус, но остается видимым
        // Сохранение данных, приостановка анимаций
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        // Фрагмент больше не виден пользователю
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // UI уничтожается, но фрагмент продолжает существовать
        // Очистка ссылок на View для избежания утечек памяти
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Фрагмент уничтожается
        // Очистка ресурсов
        Log.d(TAG, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохранение состояния для восстановления при повороте экрана
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Восстановление состояния UI после поворота экрана
        Log.d(TAG, "onViewStateRestored")
    }
}

