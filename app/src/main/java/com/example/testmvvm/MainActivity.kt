package com.example.testmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testmvvm.databinding.ActivityMainBinding
import com.example.testmvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main, CommonBindingComponent())
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel()
        binding.viewModel?.init()

        binding.subActivity.setOnClickListener{

            val intent = Intent(this@MainActivity, SubActivity::class.java)
            if(binding.viewModel?.mainTextData?.value != null) {
                val stringArr = binding.viewModel?.mainTextData?.value!!.split(" ")
                intent.putExtra("name", "sub " + stringArr[stringArr.lastIndex])
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        binding.viewModel?.onDispose()
        super.onDestroy()
    }
}
