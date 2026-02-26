package com.example.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginPage.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }

        binding.signupBtn.setOnClickListener {

            val userName = binding.signupUserName.text.toString()
            val email = binding.signupMail.text.toString()
            val password = binding.signupPassword.text.toString()

            if (userName.trim().isEmpty()){
                binding.signupMail.error="Enter full name"
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.signupMail.error = "Enter valid email"
            }
            else if (password.length < 6){
                binding.signupPassword.error="Password must be 6+ characters"
            }
            else{
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(userName)
                                .build()
                            firebaseAuth.currentUser?.updateProfile(profileUpdates)

                            Toast.makeText(this@SignUpActivity,"Signup Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,"Signup Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}