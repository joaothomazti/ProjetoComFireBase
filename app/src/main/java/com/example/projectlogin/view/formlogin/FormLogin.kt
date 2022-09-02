package com.example.projectlogin.view.formlogin

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectlogin.R
import com.example.projectlogin.databinding.ActivityFormCadastroBinding
import com.example.projectlogin.databinding.ActivityFormLoginBinding
import com.example.projectlogin.view.formcadastro.FormCadastro
import com.example.projectlogin.view.formconta.FormContaLogada
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormLogin : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormLoginBinding.inflate(layoutInflater)
    }
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bttCadastroLogin.setOnClickListener { cadastrar ->
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.bttLogin.setOnClickListener {
            val email = binding.inputTextLoginEmail.text.toString()
            val senha = binding.inputTextLoginSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Prencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }else {
                login(email, senha)
            }
        }

    }

    private fun login(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this) {login ->
            if (login.isSuccessful) {
                Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FormContaLogada::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener { errors ->
            val msgErro = when(errors){
                is FirebaseAuthInvalidUserException -> "Seu email esta incorreto ou nao existe"
                is FirebaseAuthWeakPasswordException -> "Sua senha esta incorreta"
                else -> "Sem conexao de internet"
            }
            Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show()
        }
    }
}