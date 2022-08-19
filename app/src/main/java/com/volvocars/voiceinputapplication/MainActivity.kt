package com.volvocars.voiceinputapplication

import android.app.assist.AssistContent
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

const val LOCK_ACTION = "lock"
const val UNLOCK_ACTION = "unlock"

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logIntent(intent = intent)
        intent?.handleIntent()
    }

    private fun logIntent(intent: Intent) {
        Log.e("mainActivity", intent.action.toString())
        val bundle: Bundle = intent.extras ?: return

        Log.e(TAG, "======= logIntent ========= %s")
        Log.e(TAG, "Logging intent data start")

        bundle.keySet().forEach { key ->
            Log.e(TAG, "[$key=${bundle.get(key)}]")
        }

        Log.e(TAG, "Logging intent data complete")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.handleIntent()
    }

    private fun Intent.handleIntent() {
        when (action) {
            Intent.ACTION_VIEW -> doAction()
        }
    }

    private fun doAction() {
        val param = intent?.extras?.getString("featureParam")
        param.let {
            when (it?.lowercase(Locale.ROOT)) {
                LOCK_ACTION -> navigateToLock()
                UNLOCK_ACTION -> navigateToUnlock()
            }
        }
    }

    private fun navigateToLock() {
        startActivity(Intent(this, LockCarActivity::class.java))
    }

    private fun navigateToUnlock() {
        startActivity(Intent(this, UnlockActivity::class.java))
    }
}