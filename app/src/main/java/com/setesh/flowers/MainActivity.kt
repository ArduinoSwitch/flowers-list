package com.setesh.flowers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setesh.commons.navigation.NavHostHolder

class MainActivity : AppCompatActivity(), NavHostHolder {
    override val navHostId: Int = R.id.nav_host
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}