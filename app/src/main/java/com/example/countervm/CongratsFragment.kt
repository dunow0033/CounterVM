package com.example.countervm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.countervm.databinding.FragmentCongratsBinding

class CongratsFragment : Fragment() {

    private var _binding: FragmentCongratsBinding? = null
    private val binding: FragmentCongratsBinding get() = _binding!!

    private val viewModel: CounterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCongratsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        viewModel.count.observe(viewLifecycleOwner) {
            binding.congratsTv.text = getString(R.string.congrats).format(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}