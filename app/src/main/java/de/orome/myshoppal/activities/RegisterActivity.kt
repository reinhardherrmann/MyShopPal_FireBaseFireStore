package de.orome.myshoppal.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import de.orome.myshoppal.R
import de.orome.myshoppal.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var tvLogin: TextView
    private lateinit var toolbar: Toolbar

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
            validateRegisterValues()
            // finish()}

            binding.tvLogin.setOnClickListener {
                intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }

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
                    showErrorSnackbar("Your data are valid", false)
                    true
                }
            }
        }
    }
}