package com.briskjie.cxx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.briskjie.cxx.utils.CommonUtil

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CommonUtil.setNoTitlebar(this)
        CommonUtil.clearFullsreen(this) // HomeActivity作为启动页，主题设置了全屏模式，create后需要回置成非全屏
        setContentView(R.layout.activity_main)
    }

    fun goOverScroller(view: View) {
        val intent = Intent(this, OverScrollRecyclerViewActivity::class.java)
        startActivity(intent)
    }

    fun goGestureTextActivity(view: View) {
        val intent = Intent(this, GestureTestActivity::class.java);
        startActivity(intent)
    }
}