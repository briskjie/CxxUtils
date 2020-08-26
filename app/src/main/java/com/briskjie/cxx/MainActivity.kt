package com.briskjie.cxx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goOverScroller(view: View) {
        val intent = Intent(this, BouncedRecyclerViewActivity::class.java)
        startActivity(intent)
    }
}