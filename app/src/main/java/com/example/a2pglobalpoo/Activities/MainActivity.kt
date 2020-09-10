package com.example.a2pglobalpoo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a2pglobalpoo.R
import com.example.a2pglobalpoo.goActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //INICIO VARIABLES
    private val RC_SIGN_IN = 423
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    //FIN VARIABLES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //INICIO LLAMADO DE METODOS
        googleLogin()
        //FIN LLAMADO DE METODOS
    }

    //INICIO FUNCION PARA INICIO DE SESIÓN CON GOOGLE
    private fun googleLogin(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())
        //INICIO EVENTO ONCLICK
        buttonLogInGoogle.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(true)
                    .build(), RC_SIGN_IN)
        }
        //FIN EVENTO ONCLIC
    }
    //FIN FUNCION PARA INICIO DE SESIÓN CON GOOGLE

    //INICIO ONACTIVITYRESULT PARA EL LOGIN CON GOOGLE
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                goActivity<ChatActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            } else {
                Toast.makeText(this, "Error al iniciar sesion con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //INICIO ONACTIVITYRESULT PARA EL LOGIN CON GOOGLE
}