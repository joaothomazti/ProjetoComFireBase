package com.example.projectlogin.view.formconta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectlogin.R
import com.example.projectlogin.databinding.ActivityFormContaLogadaBinding

class FormContaLogada : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormContaLogadaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}