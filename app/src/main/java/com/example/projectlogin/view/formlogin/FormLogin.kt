package com.example.projectlogin.view.formlogin

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectlogin.R
import com.example.projectlogin.databinding.ActivityFormCadastroBinding
import com.example.projectlogin.databinding.ActivityFormLoginBinding
import com.example.projectlogin.view.formcadastro.FormCadastro

class FormLogin : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bttCadastroLogin.setOnClickListener { cadastrar ->
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)

        }

    }
}