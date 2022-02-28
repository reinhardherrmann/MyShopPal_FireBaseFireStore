package de.orome.myshoppal.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import de.orome.myshoppal.R
import de.orome.myshoppal.databinding.DialogProgressBinding

open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog
    private lateinit var dialogBinding: DialogProgressBinding


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

    fun showProgressDialog(text: String){
        mProgressDialog = Dialog(this)
        /* Set the screen content from a layout resource
        the resource will be inflates, adding all the top-level views  to the screen. */
        dialogBinding = DataBindingUtil.setContentView(this,R.layout.dialog_progress)
        dialogBinding.tvProgressText.text = text
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        
        // start the dialog and display it in screen
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }
}