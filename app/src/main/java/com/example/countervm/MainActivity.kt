package com.example.countervm

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countervm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.count.observe(this){

        }

        viewModel.seconds.observe(this, Observer {
            binding.tvCountDownNumber.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Count is over!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnStart.setOnClickListener{
            if(binding.etEnterNumber.text.isEmpty() || binding.etEnterNumber.text.length < 5) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            } else {
                val number = binding.etEnterNumber.text.toString().toLong()
                viewModel._timerValue.value = number
                viewModel.startTimer()
            }
        }

        binding.btnStop.setOnClickListener{
            viewModel.stopTimer()
        }
    }
}