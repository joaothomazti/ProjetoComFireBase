package com.example.projectlogin.view.formcadastro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectlogin.databinding.ActivityFormCadastroBinding
import com.example.projectlogin.view.formlogin.FormLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


class FormCadastro : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormCadastroBinding.inflate(layoutInflater)
    }
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bttVoltarLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
        binding.bttCadastrar.setOnClickListener {
            val email = binding.inputTextEmail.text.toString()
            val senha = binding.inputTextSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Prencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }else{
                cadastrarUsuario(email, senha)
            }
        }
    }

    private fun cadastrarUsuario(
        email: String,
        senha: String,
    ) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this) { cadastro ->
            if (cadastro.isSuccessful){
                Toast.makeText(this, "Conta Criada com sucesso", Toast.LENGTH_SHORT).show()
                binding.inputTextEmail.setText("")
                binding.inputTextSenha.setText("")
            }
        }.addOnFailureListener {errors ->
            val msgErro = when(errors){
                is FirebaseAuthWeakPasswordException -> "Sua senha precisa ter no minimo 6 digitos"
                is FirebaseAuthInvalidCredentialsException -> "Digite um Email valido"
                is  FirebaseAuthUserCollisionException -> "Este email ja esta Cadastrado"
                else -> "Erro ao cadastrar seu usuario"
            }
            Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show()
        }
    }


}