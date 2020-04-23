package com.example.testmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testmvvm.databinding.ActivityMainBinding
import com.example.testmvvm.databinding.ActivitySubBinding
import com.example.testmvvm.viewmodel.MainViewModel
import com.example.testmvvm.viewmodel.UserViewModel

class SubActivity : AppCompatActivity() {

    lateinit var binding: ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub, CommonBindingComponent())
        binding.lifecycleOwner = this
        binding.viewModel = UserViewModel()
        binding.viewModel?.init(intent.getStringExtra("name"))
    }

    override fun onDestroy() {
        binding.viewModel?.onDispose()
        super.onDestroy()
    }
}
