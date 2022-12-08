package com.example.loginpage_materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var tvRedirectSignUp: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var forgotpass:TextView


    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Binding
        tvRedirectSignUp = findViewById(R.id.gotoRegister)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.inputEmail)
        etPass = findViewById(R.id.inputPassword)
        forgotpass=findViewById(R.id.forgot_Password)


        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }


        tvRedirectSignUp.setOnClickListener {
//            val intent = Intent(this, Signup_page::class.java)
//            startActivity(intent)
            val sign=Intent(this,SignupActivity ::class.java)
            startActivity(sign)
            // Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
            // using finish() to end the activity
            finish()
        }
        forgotpass.setOnClickListener {
            val pswd = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(pswd)
            finish()
        }
    }

      private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                startActivity(Intent(this,WelcomeActivity::class.java).apply {

                })
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

}

