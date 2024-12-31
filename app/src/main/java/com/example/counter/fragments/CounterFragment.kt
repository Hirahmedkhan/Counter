package com.example.counter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.counter.model.ModelValues
import com.example.counter.viewmodel.CounterViewModel
import com.example.eventsinmvvm.R
import com.example.eventsinmvvm.databinding.FragmentCounterBinding
import kotlinx.coroutines.launch

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private lateinit var binding: FragmentCounterBinding
    private val viewModel: CounterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterBinding.bind(view)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.counter.collect {counter ->
                binding.tvScoreDisplay.text = counter.num.toString()
            }
        }

        binding.apply {
            btnIncrement.setOnClickListener{
                viewModel.onEvent(CounterViewModel.CounterEvent.Increment)
            }
            btnDecrement.setOnClickListener{
                viewModel.onEvent(CounterViewModel.CounterEvent.Decrement)
            }
        }

        binding.btnNext.setOnClickListener {
            val num = ModelValues(num = viewModel.counter.value.num)
            val action = CounterFragmentDirections.actionCounterFragmentToResultFragment2(num)
            findNavController().navigate(action)
        }
    }
}

