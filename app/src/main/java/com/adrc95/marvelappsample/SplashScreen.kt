package com.adrc95.marvelappsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adrc95.marvelappsample.ui.common.startActivity
import com.adrc95.marvelappsample.ui.navhost.NavHostActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<NavHostActivity> {}
    }
}
