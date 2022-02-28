package de.orome.myshoppal.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import de.orome.myshoppal.R
import de.orome.myshoppal.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var tvRegister: TextView
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // setLoginScreen to fullScreen for all Android versions
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController ?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        // onClickListener implementieren
        binding.apply{
            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            tvForgotPassword.setOnClickListener {
                Toast.makeText(this@LoginActivity, "noch nicht realisiert", Toast.LENGTH_LONG).show()
            }
            btnLogin.setOnClickListener{
                loginUser()
            }
        }

    }

    private fun validateLoginValues(): Boolean {
        binding.apply{
            return when {
                TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' '}) -> {
                    showErrorSnackbar(
                        R.string.err_messsage_email.toString(),
                        true
                    )
                    false
                }

                TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' '}) -> {
                    showErrorSnackbar(
                        R.string.err_messsage_password.toString(),
                        true
                    )
                    false
                }
                else -> {
                    true
                }
            }

        }
    }

    private fun loginUser(){
        if (validateLoginValues()){
           val email = binding.etEmail.text.toString().trim { it <= ' '}
           val password = binding.etPassword.text.toString().trim { it <= ' '}
           // create an instance an log user in
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        val result = task.result
                        val user = result.user
                        Log.d("MyTag",user!!.uid)
                        showErrorSnackbar(
                            "you are logged in",
                            false
                        )
                        // TODO MainActivity wählen
                        intent = Intent(this@LoginActivity,MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        finish()
                        startActivity(intent)
                    }else {
                        showErrorSnackbar(
                            task.exception!!.message.toString(),
                            true
                        )
                    }
            }
        }
    }
}