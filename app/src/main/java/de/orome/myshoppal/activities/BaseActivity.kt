package de.orome.myshoppal.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import de.orome.myshoppal.R
import de.orome.myshoppal.databinding.DialogProgressBinding

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