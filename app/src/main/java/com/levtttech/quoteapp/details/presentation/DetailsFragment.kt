package com.levtttech.quoteapp.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.databinding.FragmentDetailsBinding
import com.levtttech.quoteapp.main.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {
    override val viewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(
        inflater,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner) { state ->
            state.show(
                binding.quoteTextView,
                binding.categoryTextView,
                binding.authorTextView
            )
        }
        viewModel.init(savedInstanceState == null)
    }

}