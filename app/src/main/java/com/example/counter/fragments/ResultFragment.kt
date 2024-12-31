package com.example.counter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.eventsinmvvm.R
import com.example.eventsinmvvm.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val num = args.ModelValues.num

        binding.tvDisplayResult.text = num.toString()
    }
}
