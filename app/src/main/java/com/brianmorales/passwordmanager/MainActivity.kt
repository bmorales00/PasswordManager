package com.brianmorales.passwordmanager

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    //NOTES FOR 07/27
    //Fix the custom actionbar in which the changes will pertain to that in the activity_card_view

    private lateinit var fpImageView: ImageView
    private lateinit var fpAuth2: Button

    //BIoMetricsManager
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo:PromptInfo



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fpImageView = findViewById(R.id.fpImage)
        fpAuth2 = findViewById(R.id.btnFPAuth2)


        biometricPrompt = createBiometricPrompt()
        promptInfo = createPromptInfo()


        loggingIn()


    }

    private fun createBiometricPrompt():BiometricPrompt{
        this.executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(this@MainActivity, executor, object:BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                Toast.makeText(this@MainActivity, errString, Toast.LENGTH_LONG).show()

            }

            @SuppressLint("SetTextI18n")
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity, "Successfully Logged In", Toast.LENGTH_LONG).show()

                val intent = Intent(this@MainActivity, MenuActivity::class.java)
                startActivity(intent)
            }

            @SuppressLint("SetTextI18n")
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@MainActivity, "Failed to Log In", Toast.LENGTH_LONG).show()
            }
        })
        return biometricPrompt
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Log in using FingerPrint or Face")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun loggingIn() {
        fpAuth2.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)
        }
    }

}