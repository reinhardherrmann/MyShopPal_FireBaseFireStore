package de.orome.myshoppal.activities

import android.graphics.Color.green
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import de.orome.myshoppal.R

open class BaseActivity : AppCompatActivity() {


        fun showErrorSnackbar(message: String,errorMessage: Boolean){
            val snackbar =
                Snackbar.make(findViewById(android.R.id.content), message,Snackbar.LENGTH_LONG)
            val snackBarView = snackbar.view

            if (errorMessage){
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarError
                    )
                )
            } else{
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarSuccess
                    )
                )
            }
            snackbar.show()
        }

}