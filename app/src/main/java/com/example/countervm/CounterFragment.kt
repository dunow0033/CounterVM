package com.example.countervm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.countervm.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private var _binding: FragmentCounterBinding? = null
    private val binding: FragmentCounterBinding get() = _binding!!

    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CounterViewModel::class.java)
        with(binding){
            viewModel.count.observe(viewLifecycleOwner, object : Observer<Int> {
                override fun onChanged(count: Int?) {
                    counterTv.text = count.toString()

                    if(count == 12){
                        findNavController().navigate(R.id.congrats_fragment)
                    }
                }
            })

            incrementBtn.setOnClickListener{
                viewModel.incrementCount()
            }

            resetBtn.setOnClickListener {
                viewModel.resetCount()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}