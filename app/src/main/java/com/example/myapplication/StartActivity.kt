package com.example.myapplication

import android.os.Bundle
import android.widget.Button

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}