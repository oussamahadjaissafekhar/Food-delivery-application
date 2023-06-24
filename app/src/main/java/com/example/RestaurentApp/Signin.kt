package com.example.RestaurentApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.RestaurentApp.adapters.MenuAdapter
import com.example.movieexample.databinding.ActivitySigninBinding
import com.example.movieexample.retrofit.Endpoint
import kotlinx.coroutines.*

class SignIn : AppCompatActivity() {
    lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySigninBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginErrorTextView.visibility = View.GONE

        binding.apply {
            signup.setOnClickListener(){
                val intent = Intent(this@SignIn, SignUp::class.java)
                startActivity(intent)

            }
            signIn.setOnClickListener(){
                val username = userName.text
                val password = password.text
                if (username!!.isEmpty() or password!!.isEmpty()) {
                    Toast.makeText(this@SignIn, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    //Log.d("username", username.toString())
                    //Log.d("password", password.toString())
                    //Toast.makeText(this@SignIn, "username : "+username.toString()+"pass : "+password.toString() , Toast.LENGTH_SHORT).show()
                    authenticate(username.toString(),password.toString())
                }

            }

        }
    }
    fun authenticate(username : String,password : String) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            this.runOnUiThread {
                Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().authentification(username,password)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    if(response.body()!!.userId == 0){
                        binding.loginErrorTextView.visibility = View.VISIBLE

                    }else{
                        binding.password.setText("")
                        binding.userName.setText("")
                        val intent = Intent(this@SignIn, MainActivity::class.java)
                        intent.putExtra("userId", response.body()!!.userId.toString())
                        startActivity(intent)
                        finish()

                    }
                } else {
                    Toast.makeText(this@SignIn, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}