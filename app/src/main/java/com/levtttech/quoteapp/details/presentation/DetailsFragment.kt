package com.levtttech.quoteapp.details.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.main.presentation.BaseFragment
import com.levtttech.quoteapp.quotes.presentation.QuoteUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel>() {
    override val viewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    override val layoutId: Int
        get() = R.layout.fragment_details

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

        fun createFragment(item: DetailsUi) : DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, item)
                }
            }
        }
    }
}