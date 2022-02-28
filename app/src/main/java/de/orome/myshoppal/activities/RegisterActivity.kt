package de.orome.myshoppal.activities

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import de.orome.myshoppal.R
import de.orome.myshoppal.databinding.ActivityRegisterBinding
import de.orome.myshoppal.databinding.DialogProgressBinding

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var tvLogin: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var auth: FirebaseAuth

    private lateinit var mProgressDialog: Dialog
    private lateinit var dialogBinding: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)


        toolbar = binding.toolbarRegisterActivity
        // set RegisterScreen to fullScreen for all Android versions
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setupActionBar()

        binding.btnRegister.setOnClickListener {
            registerUser()
            // finish()}
        }

        binding.tvLogin.setOnClickListener {
            intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    /**
     * Function do validate inputvalues and to display snackbar, if error occurs or data are valid
     */
    private fun validateRegisterValues(): Boolean {
        binding.apply {
            return when {
                TextUtils.isEmpty(etFirstName.text.toString().trim { it <= ' ' }) -> {
                    showErrorSnackbar(
                        resources.getString(R.string.err_messsage_first_name),
                        true
                    )
                    false
                }

                TextUtils.isEmpty(etLastName.text.toString().trim { it <= ' ' }) -> {
                    showErrorSnackbar(
                        resources.getString(R.string.err_messsage_last_name),
                        true
                    )
                    false
                }

                TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' ' }) -> {
                    showErrorSnackbar(resources.getString(R.string.err_messsage_email), true)
                    false
                }

                TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' }) -> {
                    showErrorSnackbar(resources.getString(R.string.err_messsage_password), true)
                    false
                }

                TextUtils.isEmpty(etConfirmPassword.text.toString().trim { it <= ' ' }) -> {
                    showErrorSnackbar(
                        resources.getString(R.string.err_messsage_confirm_password),
                        true
                    )
                    false
                }

                !cbTermsAndCondition.isChecked -> {
                    showErrorSnackbar(
                        resources.getString(R.string.err_messsage_agree_terms_and_conditions),
                        true
                    )
                    false
                }
                else -> {
                    //showErrorSnackbar("Your data are valid", false)
                    true
                }
            }
        }
    }

    private fun registerUser(){
        // check first, if given values are valid
        if(validateRegisterValues()){
            // show Dialog
            //showProgressDialog(resources.getString(R.string.please_wait))
            val email = binding.etEmail.text.toString().trim { it <= ' '}
            val password = binding.etPassword.text.toString().trim { it <= ' '}

            // create an instance of fireAuth and register user
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        //hideProgressDialog()
                        if (task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            showErrorSnackbar(
                                "Your are registered successfully! The " +
                                        "User id is ${firebaseUser.uid}",
                                false
                            )
                        } else{
                            // registration went wrong
                            showErrorSnackbar(
                                task.exception!!.message.toString(),
                                true
                            )
                        }
                    }
                )
        }
    }

//    fun showProgressDialog(text: String){
//        mProgressDialog = Dialog(this)
//        /* Set the screen content from a layout resource
//        the resource will be inflates, adding all the top-level views  to the screen. */
//        dialogBinding = DataBindingUtil.setContentView(this,R.layout.dialog_progress)
//        dialogBinding.tvProgressText.text = text
//        mProgressDialog.setCancelable(false)
//        mProgressDialog.setCanceledOnTouchOutside(false)
//
//        // start the dialog and display it in screen
//        mProgressDialog.show()
//    }
//
//    fun hideProgressDialog() {
//        mProgressDialog.dismiss()
//    }
}