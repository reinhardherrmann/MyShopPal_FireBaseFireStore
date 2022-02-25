package de.orome.myshoppal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // set SplashScreen to fullScreen for all Android versions
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        // nach 2,5 Sekunden zur nächsten Activity wechseln
        @Suppress("DEPRECATION")
        // TODO Durch neue SpalshscreenAPI ersetzen
        Handler().postDelayed(
            {
                // Launch the MainActivity
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            },
            2500
        )
    }
}