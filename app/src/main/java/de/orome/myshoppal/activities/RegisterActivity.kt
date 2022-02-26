package de.orome.myshoppal.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import de.orome.myshoppal.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // set RegisterScreen to fullScreen for all Android versions
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController ?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        tvLogin = findViewById(R.id.tv_login)
        tvLogin.setOnClickListener {
            intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }

    }
}