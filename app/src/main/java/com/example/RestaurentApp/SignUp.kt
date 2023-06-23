package com.example.RestaurentApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.restaurentApp.databinding.ActivitySignUpBinding
import android.graphics.Color
import android.text.Editable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import com.example.RestaurentApp.entity.user
import com.example.movieexample.retrofit.Endpoint
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            signUp.setOnClickListener(){
                val username = userName.text
                val password = password.text
                val passwordConf = passwordVerify.text
                val email = email.text
                val phone = phone.text
                val profile = profilePic.text
                val address = address.text

                if (username.isEmpty() or password.isEmpty() or passwordConf.isEmpty() or email.isEmpty() or phone.isEmpty() or profile.isEmpty() or address.isEmpty()) {
                    verifyFields(username,password,passwordConf,email,phone,address,profile)
                    Toast.makeText(this@SignUp, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    if(password.toString() == passwordConf.toString()){
                        val user = user(0,username.toString(),password.toString(),email.toString(),phone.toString(),address.toString(),profile.toString())
                        val gson = Gson()
                        val userString = gson.toJson(user)
                        Log.d("user",user.toString())
                        creatUser(userString)
                    }else{
                        Toast.makeText(this@SignUp, "please check the password confirmation", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }

    private fun creatUser(user: String) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            this.runOnUiThread {
                Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().createUser(user)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(this@SignUp, response.body().toString(), Toast.LENGTH_SHORT).show()
                    Log.d("creat order response", response.body().toString())
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(this@SignUp, "result", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SignUp, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignUp, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun verifyFields(username: Editable,Password: Editable,PasswordConf: Editable,Email: Editable,Phone: Editable,Address: Editable,ProfilePic: Editable){
        if (username.isEmpty()){
            binding.apply {
                changeHintColor(userName,Color.RED)
            }
        }
        if (Password.isEmpty()){
            binding.apply {
                changeHintColor(password,Color.RED)
            }
        }
        if (PasswordConf.isEmpty()){
            binding.apply {
                changeHintColor(passwordVerify,Color.RED)
            }
        }
        if (Email.isEmpty()){
            binding.apply {
                changeHintColor(email,Color.RED)
            }
        }
        if (Phone.isEmpty()){
            binding.apply {
                changeHintColor(phone,Color.RED)
            }
        }
        if (Address.isEmpty()){
            binding.apply {
                changeHintColor(address,Color.RED)
            }
        }
        if (ProfilePic.isEmpty()){
            binding.apply {
                changeHintColor(profilePic,Color.RED)
            }
        }
    }
    private fun changeHintColor(editText: EditText, color: Int) {
        val hint = editText.hint.toString()

        val spannableString = SpannableString(hint)
        val foregroundColorSpan = ForegroundColorSpan(color)
        spannableString.setSpan(foregroundColorSpan, 0, hint.length, 0)

        editText.hint = spannableString
    }
}