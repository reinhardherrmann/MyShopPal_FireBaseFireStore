package de.orome.myshoppal.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import de.orome.myshoppal.R

class SplashActivity : AppCompatActivity() {
    private lateinit var tvAppName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        tvAppName = findViewById(R.id.tv_app_name)
        // set SplashScreen to fullScreen for all Android versions
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController ?.hide(WindowInsets.Type.statusBars())
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
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish() // kein Zurück
            },
            2500
        )

        // Font für die TextView festlegen
        val typeface: Typeface = Typeface.createFromAsset(assets,"Montserrat-Bold.ttf")
        tvAppName.typeface = typeface
    }
}